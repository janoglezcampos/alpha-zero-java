package com.backend;

import java.util.logging.Logger;
import me.tongfei.progressbar.*;

import com.csp_group.utils.GameMatrix;

public class Arena {
	/*
	 * An Arena class where any 2 agents can be pit against each other.
	 */

	private Player player1;
	private Player player2;
	private Game game;
	private Logger log = Logger.getLogger(Arena.class.getName());

	public Arena(Player player_1, Player player_2, Game game) {
		/*
		 * Input: player 1,2: two functions that takes board as input, return action
		 * game: Game object display: a function that takes board as input and prints it
		 * (e.g. display in othello/OthelloGame). Is necessary for verbose mode.
		 * 
		 * see othello/OthelloPlayers.py for an example. See pit.py for pitting human
		 * players/other baselines with each other.
		 */
		this.player1 = player_1;
		this.player2 = player_2;
		this.game = game;
	}

	private int playGame(boolean verbose) {
		/*
		 * Executes one episode of a game.
		 * 
		 * Returns: either winner: player who won the game (1 if player1, -1 if player2)
		 * or draw result returned from the game that is neither 1, -1, nor 0.
		 */
		Player[] players = { this.player2, null, this.player1 };
		int curPlayer = 1;
		GameMatrix board = this.game.getInitBoard();
		int it = 0;
		while (this.game.getGameEnded(board, curPlayer) == 0) {
			it += 1;
			if (verbose) {
				log.info("Turn " + it + "Player " + curPlayer);
			}
			int action = players[curPlayer + 1].getAction(this.game.getCanonicalForm(board, curPlayer));

			int[] valids = this.game.getValidMoves(this.game.getCanonicalForm(board, curPlayer), 1);

			if (valids[action] == 0) {
				log.severe("Action {action} is not valid!");
				log.info("valids = " + valids);
				assert valids[action] > 0;
			}
			// TODO
			// board, curPlayer = this.game.getNextState(board, curPlayer, action);
		}
		if (verbose) {
			log.info("Game over: Turn " + it + "Result " + this.game.getGameEnded(board, 1));
		}
		return curPlayer * this.game.getGameEnded(board, curPlayer);

		// return curPlayer * this.game.getGameEnded(board, curPlayer)
	}

	public int[] playGames(int num, boolean verbose) {
		
        /*
        Plays num games in which player1 starts num/2 games and player2 starts
        num/2 games.

        Returns:
            oneWon: games won by player1
            twoWon: games won by player2
            draws:  games won by nobody
        */

        num = num / 2;
        int oneWon = 0, twoWon = 0, draws = 0;
        
        ProgressBar pb = new ProgressBar("Arena.playGames (1)", num);
		for (int i = 0; i < num; i++) {
			int gameResult = this.playGame(verbose);
			if (gameResult == 1){
                oneWon += 1;
			}else if( gameResult == -1) {
                twoWon += 1;
			}else {
                draws += 1;
			}
			pb.step();
		}
        
		pb.close();
		
	    Player temp = this.player1;
	    this.player1 = this.player2;
	    this.player2 = temp;
	    
        pb = new ProgressBar("Arena.playGames (2)", num);
        for (int i = 0; i < num; i++) {
			int gameResult = this.playGame(verbose);
			if (gameResult == -1){
                oneWon += 1;
			}else if( gameResult == 1) {
                twoWon += 1;
			}else {
                draws += 1;
			}
			pb.step();
		}
        
		pb.close();
		
		int[] returnList = {oneWon, twoWon, draws};
		return returnList;
	}

}
