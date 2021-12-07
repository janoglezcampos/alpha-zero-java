package com.mcts;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.backend.Game;
import com.csp_group.utils.Arguments;
import com.csp_group.utils.DistributedRandomNumberGenerator;
import com.csp_group.utils.GameMatrix;
import com.mcts.Nnet.Prediction;

public class MCTS {
    /*
    This class handles the MCTS tree.
    */
	private final double EPS = Math.pow(10, -8);

	Map<String, Double> Qsa;  // stores Q values for s,a (as defined in the paper)
	Map<String, Integer>  Nsa = new HashMap<String, Integer>();;  // stores #times edge s,a was visited
	Map<String, Integer>  Ns;  // stores #times board s was visited
    Map<String, Double[]> Ps = new HashMap<String, Double[]>();  // stores initial policy (returned by neural net)
    Map<String, Integer> Es = new HashMap<String, Integer>();
    Map<String, int[]>  Vs;  // stores game.getValidMoves for board s
    
    Game game;
    Nnet nnet;
    Arguments args;
	
    public MCTS(Game game, Nnet nnet, Arguments args) {
        this.game = game;
        this.nnet = nnet;
        this.args = args;
    }
    
    Double[] getActionProb(GameMatrix canonicalBoard){
    	return getActionProb(canonicalBoard, 1);
    }
    
    Double[] getActionProb(GameMatrix canonicalBoard, int temp){
        /*
        This function performs numMCTSSims simulations of MCTS starting from
        canonicalBoard.
        Returns:
            probs: a policy vector where the probability of the ith action is
                   proportional to Nsa[(s,a)]**(1./temp)
        */
    	for(int i = 0; i < this.args.numMCTSSims; i++) {
    		this.search(canonicalBoard);
    	}

        String s = this.game.stringRepresentation(canonicalBoard);
        
        int actionSize = this.game.getActionSize();
        int[] counts = new int[actionSize];
        
        for(int a = 0; a < actionSize; a++){
        	String s_a = stateActionRepresentation(s,a);
        	if(this.Nsa.containsKey(s_a)){
        		counts[a] = this.Nsa.get(s_a);
        	}else {
        		counts[a]  = 0;
        	}
        }
        
        Double[] probs = new Double [actionSize];
        if(temp == 0) {
            int	bestAction = DistributedRandomNumberGenerator.randomValue(counts);
            probs[bestAction] = (double) 1;
            return probs;
        }
        //TODO
        //counts = [x ** (1. / temp) for x in counts];
        probs = normalizeArray(counts);
        return probs;
        		
    }
    
    private static float sumArray(int[] array){
    	float total = 0;
    	for(int val : array) {
    		total += val;
    	}
    	return total;
    }
    private static float sumArray(Double[] array){
    	float total = 0;
    	for(Double val : array) {
    		total += val;
    	}
    	return total;
    }
    
    private Double[] normalizeArray(int[] array) {
    	Double[] probs = new Double[array.length];
    	float counts_sum = sumArray(array);
    	for(int i = 0; i < array.length;i++) {
    		probs[i] = (double) (array[i]/counts_sum);
    	}
    	return probs;
    }
    
    private Double[] normalizeArray(Double[] array) {
    	Double[] probs = new Double[array.length];
    	float counts_sum = sumArray(array);
    	for(int i = 0; i < array.length;i++) {
    		probs[i] = array[i]/counts_sum;
    	}
    	return probs;
    }

    private Double search(GameMatrix canonicalBoard) {
        /*
        This function performs one iteration of MCTS. It is recursively called
        till a leaf node is found. The action chosen at each node is one that
        has the maximum upper confidence bound as in the paper.
        Once a leaf node is found, the neural network is called to return an
        initial policy P and a value v for the state. This value is propagated
        up the search path. In case the leaf node is a terminal state, the
        outcome is propagated up the search path. The values of Ns, Nsa, Qsa are
        updated.
        NOTE: the return values are the negative of the value of the current
        state. This is done since v is in [-1,1] and if v is the value of a
        state for the current player, then its value is -v for the other player.
        Returns:
            v: the negative of the value of the current canonicalBoard
        */

        String s = this.game.stringRepresentation(canonicalBoard);

    	if(!this.Es.containsKey(s)) {
    		this.Es.put(s, this.game.getGameEnded(canonicalBoard, 1));
    	}
    	
        if (this.Es.get(s) != 0) {
            // terminal node
            return (double) (-1*this.Es.get(s));
        }
        
        if(!this.Ps.containsKey(s)) {
            // leaf node
        	Double v;
        	float sum_Ps_s;
            Prediction pred = this.nnet.predict(canonicalBoard);
            int[] valids = this.game.getValidMoves(canonicalBoard, 1);
            
            this.Ps.put(s,maskValids(this.Ps.get(s),valids));   // masking invalid moves
            v = pred.v;
            sum_Ps_s = sumArray(this.Ps.get(s));
            if (sum_Ps_s > 0){
            	this.Ps.put(s,normalizeArray(this.Ps.get(s)));
            }else {
                // if all valid moves were masked make all valid moves equally probable

                // NB! All valid moves may be masked if either your NNet architecture is insufficient or you've get overfitting or something else.
                // If you have got dozens or hundreds of these messages you should pay attention to your NNet and/or training process.   
                System.out.println("All valid moves were masked, doing a workaround.");
                this.Ps.put(s,normalizeArray(valids));
            }
            this.Vs.put(s,valids);
            this.Ns.put(s,0);
            return -v;
        }

        int[] valids = this.Vs.get(s);
        Double cur_best = Double.NEGATIVE_INFINITY;
        int best_act = -1;

        // pick the action with the highest upper confidence bound
        for(int a = 0; a < this.game.getActionSize(); a++) {
            if(valids[a] != 0) {
            	String s_a = stateActionRepresentation(s,a);
            	Double u;
                if (this.Qsa.containsKey(s_a)){
                    u = this.Qsa.get(s_a) + this.args.cpuct * this.Ps.get(s)[a] * Math.sqrt(this.Ns.get(s)) / (
                            1 + this.Nsa.get(s_a));
                } else {
                    u = this.args.cpuct * this.Ps.get(s)[a] * Math.sqrt(this.Ns.get(s) + EPS);  // Q = 0 ?
                }

                if(u > cur_best){
                    cur_best = u;
                    best_act = a;
                }
            }
        }

        int a = best_act;
        GameMatrix next_s = null;
        //TODO
		//next_s, next_player = this.game.getNextState(canonicalBoard, 1, a);
        //next_s = this.game.getCanonicalForm(next_s, next_player);

        Double v = this.search(next_s);
        String s_a = stateActionRepresentation(s,a);
        if (this.Qsa.containsKey(s_a)){
        	this.Qsa.put(s_a, (this.Nsa.get(s_a) * this.Qsa.get(s_a) + v) / (this.Nsa.get(s_a)+ 1));
            this.Nsa.put(s_a, this.Nsa.get(s_a) + 1);

        }else {
          	this.Qsa.put(s_a, v);
            this.Nsa.put(s_a, 1);
        }
        this.Ns.put(s, this.Ns.get(s) + 1);
        return -v;
        		
    }
    
    private String stateActionRepresentation(String state, int action) {
    	byte[] bytes = ByteBuffer.allocate(4).putInt(action).array();
    	String encoded = new String(bytes, StandardCharsets.UTF_8);
    	return state+encoded;
    }
    
    private Double[] maskValids(Double[] doubles, int[] valids) {
    	for(int i = 0; i > doubles.length; i++) {
    		doubles[i] = valids[i] * doubles[i];
    	}
    	return doubles;
    }
        		
}