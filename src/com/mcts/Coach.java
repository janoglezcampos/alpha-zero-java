package com.mcts;

import java.util.*;
import java.util.logging.Logger;

import com.logic.*;
import com.csp_group.utils.*;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import java.io.*;
import java.nio.file.*;

import me.tongfei.progressbar.*;

/**
 * This class executes the self-play + learning. It uses the functions defined
 * in Game and NeuralNet. args are specified in Arguments.java
 * 
 * @author Alejandro Campos
 *
 */
public class Coach {
	private Game game;
	private Nnet nnet;
	private Nnet pnet;
	private Arguments args;
	private boolean skipFirstthisPlay;
	private LinkedList<TrainExample> trainExamplesHistory;
	private int curPlayer;
	private MCTS mcts;
	private Logger log = Logger.getLogger(Coach.class.getName());

	public Coach(Game game, Nnet nnet, Arguments args) {
		this.game = game;
		this.nnet = nnet;
		this.pnet = new Nnet(game);
		this.args = args;
		// this.pnet = this.nnet.__class__(this.game) // the competitor network TODO
		this.args = args;
		this.mcts = new MCTS(this.game, this.nnet, this.args);
		this.trainExamplesHistory = new LinkedList<TrainExample>(); // history of examples from
																	// args.numItersForTrainExamplesHistory latest
																	// iterations
		this.skipFirstthisPlay = false; // can be overriden in loadTrainExamples()
		this.curPlayer = 1;
	}

	/**
	 * This function executes one episode of this-play, starting with player 1. As
	 * the game is played, each turn is added as a training example to
	 * trainExamples. The game is played till the game ends. After the game ends,
	 * the outcome of the game is used to assign values to each example in
	 * trainExamples.
	 * 
	 * It uses a temp=1 if episodeStep is smaller than tempThreshold, and thereafter uses temp=0.
	 * 
	 * @return trainExamples: a list of examples of the form (canonicalBoard,
	 *         currPlayer, pi,v) pi is the MCTS informed policy vector, v is +1 if
	 *         the player eventually won the game, else -1.
	 */
	public List<TrainExample> executeEpisode() {

		List<TrainExample> trainExamples = new LinkedList<TrainExample>();
		GameMatrix board = this.game.getInitBoard();
		this.curPlayer = 1;
		int episodeStep = 0;

		GameMatrix canonicalBoard;
		int temp = 0;
		Double[] pi;
		while (true) {
			episodeStep += 1;
			canonicalBoard = this.game.getCanonicalForm(board, this.curPlayer);
			temp = (episodeStep < this.args.tempThreshold) ? 1 : 0;

			pi = this.mcts.getActionProb(canonicalBoard, temp);
			for (Symetry sym : this.game.getSymmetries(canonicalBoard, pi)) {
				trainExamples.add(new TrainExample(sym.board, this.curPlayer, sym.pi));
			}

			int r = this.game.getGameEnded(board, this.curPlayer);

			if (r != 0) {
				for (TrainExample te : trainExamples) {
					te.result = (r * ((te.curPlayer != this.curPlayer) ? 1 : -1));
				}
				return trainExamples;
			}
		}
	}

	/**
	 * Performs numIters iterations with numEps episodes of this-play in each
	 * iteration. After every iteration, it retrains neural network with examples in
	 * trainExamples (which has a maximum length of maxlenofQueue). It then pits the
	 * new neural network against the old one and accepts it only if it wins more than
	 * updateThreshold fraction of games.
	 */
	public void learn() {
		for (int i = 0; i < this.args.numIters + 1; i++) {
			// bookkeeping examples of the iteration

			log.info("Starting Iter " + i + " ...");

			// List<TrainExample> iterationTrainExamples = new LinkedList<TrainExample>();

			if (!this.skipFirstthisPlay || i > 1) {
				Queue<TrainExample> iterationTrainExamples = new CircularFifoQueue<TrainExample>(
						this.args.maxlenOfQueue);
				ProgressBar pb = new ProgressBar("Iter " + i, this.args.numEps);
				for (int j = 0; j < this.args.numEps; j++) {
					this.mcts = new MCTS(this.game, this.nnet, this.args);
					iterationTrainExamples.addAll(this.executeEpisode());
					pb.step();
				}
				pb.close();
				// save the iteration examples to the history
				this.trainExamplesHistory.addAll(iterationTrainExamples);
			}

			if (this.trainExamplesHistory.size() > this.args.numItersForTrainExamplesHistory) {
				log.warning("Removing the oldest entry in trainExamples. len(trainExamplesHistory) = "
						+ this.trainExamplesHistory.size());
				while (this.trainExamplesHistory.size() > this.args.numItersForTrainExamplesHistory) {
					this.trainExamplesHistory.removeFirst();
				}
			}
			// backup history to a file
			// NB! the examples were collected using the model from the previous iteration,
			// so (i-1)
			this.saveTrainExamples(i - 1);

			// shuffle examples before training
			LinkedList<TrainExample> trainExamples = new LinkedList<TrainExample>(trainExamplesHistory);
			/*
			 * TODO for(TrainExample e : this.trainExamplesHistory) {
			 * trainExamples.addAll(e); }
			 */
			Collections.shuffle(trainExamples);
			// training new network, keeping a copy of the old one
			this.nnet.save_checkpoint(this.args.checkpoint, "temp.pth.tar");
			this.pnet.load_checkpoint(this.args.checkpoint, "temp.pth.tar");
			// MCTS pmcts = new MCTS(this.game, this.pnet, this.args);

			this.nnet.train(trainExamples);
			// MCTS nmcts = new MCTS(this.game, this.nnet, this.args);

			log.info("PITTING AGAINST PREVIOUS VERSION");

			Player p1 = new Player(this.game, this.pnet, this.args);
			Player p2 = new Player(this.game, this.nnet, this.args);

			Arena arena = new Arena(p1, p2, this.game);
			int[] returnList;

			int pwins = 0, nwins = 0, draws = 0;
			returnList = arena.playGames(this.args.arenaCompare, false);
			pwins = returnList[0];
			nwins = returnList[1];
			draws = returnList[2];

			log.info(String.format("NEW/PREV WINS : %d / %d ; DRAWS : %d", nwins, pwins, draws));
			if ((pwins + nwins == 0) || (nwins / (pwins + nwins) < this.args.updateThreshold)) {
				log.info("REJECTING NEW MODEL");
				this.nnet.load_checkpoint(this.args.checkpoint, "temp.pth.tar");
			} else {
				log.info("ACCEPTING NEW MODEL");
				this.nnet.save_checkpoint(this.args.checkpoint, this.getCheckpointFile(i));
				this.nnet.save_checkpoint(this.args.checkpoint, "best.pth.tar");
			}
		}
	}

	/**
	 * Generate checkpoint filename
	 * 
	 * @param iteration: number to generate the file name
	 * @return filename
	 */
	public String getCheckpointFile(int iteration) {
		return "checkpoint_" + iteration + ".pth.tar";
	}

	/**
	 * Saves current training history to binary file with name
	 * "checkpoint_i.pth.tar.examples"
	 * 
	 * @param iteration: number to generate the file name
	 */
	public void saveTrainExamples(int iteration) {
		String folder = this.args.checkpoint;
		File dir = new File(folder);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		Path path = Paths.get(folder, this.getCheckpointFile(iteration) + ".examples");

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path.toString());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.trainExamplesHistory);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Load training history to list from file specified in arguments
	 */
	public void loadTrainExamples() {
		Path modelFile = Paths.get(this.args.load_folder_file[0], this.args.load_folder_file[1]);
		File examplesFile = new File(modelFile.toString() + ".examples");
		if (!examplesFile.isFile()) {
			log.warning("File " + examplesFile + " with trainExamples not found!");
			Scanner console = new Scanner(System.in);
			System.out.println("Continue? [y|n]");
			char r = console.next().charAt(0);
			if (r != 'y') {
				log.info("Terminating...");
				console.close();
				System.exit(0);
			}
			console.close();
		} else {
			log.info("File with trainExamples found. Loading it...");
			FileInputStream fis;
			try {
				fis = new FileInputStream(examplesFile.toString());

				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				LinkedList<TrainExample> readObject = (LinkedList<TrainExample>) ois.readObject();
				this.trainExamplesHistory = readObject;
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			}

			log.info("Loading done!");
		}
		// examples based on the model were already collected (loaded)
		this.skipFirstthisPlay = true;
	}
}
