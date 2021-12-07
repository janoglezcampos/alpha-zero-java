package com.mcts;

import java.util.LinkedList;

import com.backend.Game;
import com.csp_group.utils.GameMatrix;
import com.csp_group.utils.TrainExample;

public class Nnet {
	public class Prediction {
		public Double[] Ps;
		public Double v;
	}
	
	public Nnet(Game game) {
		
	}
	
	public Prediction predict(GameMatrix state) {
		return null;
	}

	public void save_checkpoint(String checkpoint, String string) {
		// TODO Auto-generated method stub
		
	}

	public void load_checkpoint(String checkpoint, String string) {
		// TODO Auto-generated method stub
		
	}

	public void train(LinkedList<TrainExample> trainExamples) {
		// TODO Auto-generated method stub
		
	}
}
