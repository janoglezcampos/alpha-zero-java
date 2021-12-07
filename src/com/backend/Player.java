package com.backend;

import com.csp_group.utils.Arguments;
import com.csp_group.utils.GameMatrix;
import com.mcts.MCTS;
import com.mcts.Nnet;

public class Player {
	private MCTS mcts;

	public Player(Game game, Nnet nnet, Arguments args) {
		this.mcts = new MCTS(game, nnet, args);
	}

	public Player(MCTS mcts) {
		this.mcts = mcts;
	}

	public int getAction(GameMatrix board) {
		return argMax(this.mcts.getActionProb(board));
	}

	private static int argMax(Double... values) {
		return argMax(values, 0, values.length);
	}

	private static int argMax(Double[] values, int from, int to) {
		int argMax = from;
		for (int i = from + 1; i < to; i++)
			if (values[argMax] < values[i])
				argMax = i;

		return argMax;
	}

}
