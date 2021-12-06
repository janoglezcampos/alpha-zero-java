package psiC;

import java.util.ArrayList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.csp_group.utils.GameMatrix;

public class Game {

	

public  ArrayList<players> players;
//private ArrayList<Piece> enable_Pieces;
private int number_players;
private board board;




public  ArrayList<players> getPlayers(){
	return players; 
}

public Game(int players, int person,board board) {
		this.number_players=players;
		this.board=board;
		setPlayer(number_players,person);
		
	}

private void setPlayer(int number,int person ) { 
	board.setRest(Piece.getPool());
	players= new ArrayList<players>();
	if (person==1) {
		for (int i=0; i<number-1; i++) {
			players.add(new random(i,dealer()));
			System.out.println(board.getRestSize2());
		}	
			players.add(new person(number-1,dealer()));
			//System.out.println(board.getRestSize2());
	}else {
		
		for (int i=0; i<number; i++) {
			players.add(new random(i,dealer()));
			//System.out.println(board.getRestSize2());
		}	
		
	}

}


public  boolean endGame (players player) {
	if(player.get_number_of_Pieces()==0 || board.getRestSize()==0) {
		return true;
	}else {
		return false;
	}
}



    public GameMatrix  getInitBoard ( ){
        /*
        Returns:
            startBoard: a representation of the board (ideally this is the form
                        that will be the input to your neural network)
        */
        return null;
        
     } 

    public int  getBoardSize( ) {
        /*
        return nulls:
            (x,y): a tuple of board dimensions
        */
        return 0;
    }

    public int getActionSize( ) {
        /*
        return nulls:
            actionSize: number of all possible actions
        */
        return 0;
	}

    public GameMatrix  getNextState(int board, int player, int action) {
        /*
        Input:
            board: current board
            player: current player (1 or -1)
            action: action taken by current player
        return nulls:
            nextBoard: board after applying action
            nextPlayer: player who plays in the next turn (should be -player)
        */
        return null; 
    }
    public int[] getValidMoves(GameMatrix board, int player) {
        /*
        Input:
            board: current board
            player: current player
        return nulls:
            validMoves: a binary vector of length self.getActionSize(), 1 for
                        moves that are valid from the current board and player,
                        0 for invalid moves
        */
        return null; 
    }

    public int getGameEnded(GameMatrix  board, int player) {
        /*
        Input:
            board: current board
            player: current player (1 or -1)
        return nulls:
            r: 0 if game has not ended. 1 if player won, -1 if player lost,
               small non-zero value for draw.
               
        */
        return 0;
    }
    public GameMatrix  getCanonicalForm(GameMatrix next_s,  int player){
        /*
        Input:
            board: current board
            player: current player (1 or -1)
        return nulls:
            canonicalBoard: return nulls canonical form of board. The canonical form
                            should be independent of player. For e.g. in chess,
                            the canonical form can be chosen to be from the pov
                            of white. When the player is white, we can return null
                            board as is. When the player is black, we can invert
                            the colors and return null the board.
        */
       return null;
    }

    public List<GameMatrix> getSymmetries(GameMatrix board, int pi) {
        /*
        Input:
            board: current board
            pi: policy vector of size self.getActionSize()
        return nulls:
            symmForms: a list of [(board,pi)] where each tuple is a symmetrical
                       form of the board and the corresponding pi vector. This
                       is used when training the neural network from examples.
        */
        return null;
    }

    public String  stringRepresentation(GameMatrix board) {
        /*
        Input:
            board: current board
        return nulls:
            boardString: a quick conversion of board to a string format.
                         Required by MCTS for hashing.
        */
        return null;
    }
	
	private short [] [] dealer() {
	short [] []random= new short [4] [13];
	short [][]enable_Pieces= new short [4][13];
	enable_Pieces= board.getRest().clone();         
	//Collections.shuffle(enable_Pieces);
	boolean ok=false;
	ArrayList<String>used= new ArrayList<>();
	String result;
	
	for (short i=0; i<4; i++) {
		for (short j=0;j<13;j++) {
			if (enable_Pieces[i][j]==1) {
				result= String.valueOf(j)+String.valueOf(i);
				used.add(result);
			}
		}
	}
	for (int i =0; i<6; i++) {
		while (ok==false) {
			//int numero = (int)(Math.random()*(X-Y+1)+Y;
			//int numero = (int)(Math.random()*(75-25+1)+25);
			
			short valor = (short)Math.floor(Math.random()*(12-0+1)+0);
			short valor2 = (short)Math.floor(Math.random()*(3-0+1)+0);
			result= String.valueOf(valor)+String.valueOf(valor2);
			if (used.contains(result)) {
				used.remove(result);
				ok=true; 
				random [valor2][valor]=1;
				enable_Pieces [valor2] [valor]=0;
			}
		}
		ok=false;
	}
	board.setRest(enable_Pieces); // las pieces que faltar�an por salir 
	return random;	
}



	
	
}
