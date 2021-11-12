package backend;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

	/*This class specifies the base Game class. To define your own game, subclass
    this class and implement the functions below. This works when the game is
    two-player, adversarial and turn-based.
	Use 1 for player1 and -1 for player2.
    See othello/OthelloGame.py for an example implementation.
	*/
	
private ArrayList<players> players;
private ArrayList<token> enable_tokens;
private int number_players;//// la interfaz gráfica deberá inicilizarlo.


public Game() {
		setPlayer(number_players);
		
	}

private void setPlayer(int number ) {  // se llamra desde la interfaz 
	for (int i=0; i<number; i++) {
		
		players.add(new players(i,dealer()));
		
	}	
}

        
private ArrayList<token> dealer() {
	
	ArrayList<token> random= new ArrayList<>();
	enable_tokens= token.getPull();	
	Collections.shuffle(enable_tokens);
	for (int i =0; i<14; i++) {
		random.add(enable_tokens.get(i));	
	}
	for (int i =0; i<14; i++) {
		enable_tokens.remove(i);	
	}
	
	return random;	
}






    public void  getInitBoard ( int self){
        
    	
    	
    	
        return;
        
     } 

    public void  getBoardSize( int self) {
        /*
        Returns:
            (x,y): a tuple of board dimensions
        */
        return;
    }

    public void  getActionSize( int self) {
        /*
        Returns:
            actionSize: number of all possible actions
        */
        return;
	}

    public void  getNextState(int self, int board, int player, int action) {
        /*
        Input:
            board: current board
            player: current player (1 or -1)
            action: action taken by current player
        Returns:
            nextBoard: board after applying action
            nextPlayer: player who plays in the next turn (should be -player)
        */
        return; 
    }
    public void  getValidMoves(int self,int  board, int player) {
        /*
        Input:
            board: current board
            player: current player
        Returns:
            validMoves: a binary vector of length self.getActionSize(), 1 for
                        moves that are valid from the current board and player,
                        0 for invalid moves
        */
        return; 
    }

    public void getGameEnded(int self,int  board, int player) {
        /*
        Input:
            board: current board
            player: current player (1 or -1)
        Returns:
            r: 0 if game has not ended. 1 if player won, -1 if player lost,
               small non-zero value for draw.
               
        */
        return;
    }
    public void  getCanonicalForm(int self,  int board,  int player){
        /*
        Input:
            board: current board
            player: current player (1 or -1)
        Returns:
            canonicalBoard: returns canonical form of board. The canonical form
                            should be independent of player. For e.g. in chess,
                            the canonical form can be chosen to be from the pov
                            of white. When the player is white, we can return
                            board as is. When the player is black, we can invert
                            the colors and return the board.
        */
       return;
    }

    public void getSymmetries(int self,int  board, int pi) {
        /*
        Input:
            board: current board
            pi: policy vector of size self.getActionSize()
        Returns:
            symmForms: a list of [(board,pi)] where each tuple is a symmetrical
                       form of the board and the corresponding pi vector. This
                       is used when training the neural network from examples.
        */
        return;
    }

    public void  stringRepresentation(int self, int board) {
        /*
        Input:
            board: current board
        Returns:
            boardString: a quick conversion of board to a string format.
                         Required by MCTS for hashing.
        */
        return;
    }
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
