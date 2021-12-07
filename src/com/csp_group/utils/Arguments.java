package com.csp_group.utils;


public class Arguments {

	public final int numIters = 1000;
	public final int numMCTSSims = 25;
	public final double cpuct = 1;
	public final int tempThreshold = 15;
	public final int maxlenOfQueue = 200000;
	public final long numEps = 100;
	public final int numItersForTrainExamplesHistory = 20;
	public final String checkpoint = "./temp/";
	public final String[] load_folder_file = {"/dev/models/8x100x50","best.pth.tar"};
	public final double updateThreshold = 0.6;
	public final int arenaCompare = 40;
	public final boolean load_model = false;
	
	
	/*
	    'numIters': 1000,
	    'numEps': 100,              # Number of complete self-play games to simulate during a new iteration.
	    'tempThreshold': 15,        #
	    'updateThreshold': 0.6,     # During arena playoff, new neural net will be accepted if threshold or more of games are won.
	    'maxlenOfQueue': 200000,    # Number of game examples to train the neural networks.
	    'numMCTSSims': 25,          # Number of games moves for MCTS to simulate.
	    'arenaCompare': 40,         # Number of games to play during arena play to determine if new net will be accepted.
	    'cpuct': 1,

	    'checkpoint': './temp/',
	    'load_model': False,
	    'load_folder_file': ('/dev/models/8x100x50','best.pth.tar'),
	    'numItersForTrainExamplesHistory': 20,
	*/
}
