package com;

import java.util.logging.Logger;

import com.csp_group.utils.Arguments;
import com.mcts.Coach;
import com.mcts.Nnet;
import com.logic.Game;

public class Train {
	private static Logger log = Logger.getLogger(Train.class.getName());
	public static void main(String[] args) {
		Arguments argsAlg = new Arguments();
	    log.info("Loading game...");
	    Game g = new Game();

	    log.info("Loading neural net...");
	    Nnet nnet = new Nnet(g);

	    if(argsAlg.load_model) {
	        log.info(String.format("Loading checkpoint %s/%s...", argsAlg.load_folder_file[0], argsAlg.load_folder_file[1]));
	        nnet.load_checkpoint(argsAlg.load_folder_file[0], argsAlg.load_folder_file[1]);
	    }else {
	        log.warning("Not loading a checkpoint");
	    }
	    log.info("Loading the Coach...");
	    Coach c = new Coach(g, nnet, argsAlg);

	    if(argsAlg.load_model) {
	        log.info("Loading 'trainExamples' from file...");
	        c.loadTrainExamples();
	    }
	    log.info("Starting the learning process 🎉");
	    c.learn();


	}
}
