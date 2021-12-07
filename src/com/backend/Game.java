package com.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.csp_group.utils.GameMatrix;
import com.csp_group.utils.Symetry;

public class Game {

	/*This class specifies the base Game class. To define your own game, subclass
    this class and implement the functions below. This works when the game is
    two-player, adversarial and turn-based.
	Use 1 for player1 and -1 for player2.
    See othello/OthelloGame.py for an example implementation.
	*/
	
	public Game() {
			
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

    public List<Symetry> getSymmetries(GameMatrix board, Double[] pi) {
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
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
