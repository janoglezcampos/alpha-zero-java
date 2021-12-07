package com.backend;

public class Arena {
	/*
    An Arena class where any 2 agents can be pit against each other.
    */

	public Arena() {
        /*
        Input:
            player 1,2: two functions that takes board as input, return action
            game: Game object
            display: a function that takes board as input and prints it (e.g.
                     display in othello/OthelloGame). Is necessary for verbose
                     mode.

        see othello/OthelloPlayers.py for an example. See pit.py for pitting
        human players/other baselines with each other.
        */
	}
        

	public void playGame(boolean verbose) {
		/*
		 * Executes one episode of a game.
		 * 
		 * Returns: either winner: player who won the game (1 if player1, -1 if player2)
		 * or draw result returned from the game that is neither 1, -1, nor 0.
		 */

		// return curPlayer * self.game.getGameEnded(board, curPlayer)
	}

	public void playGames(int num, boolean False) {
		// return oneWon, twoWon, draws
	}

}
