package com.csp_group;
import com.csp_group.utils.*;
import java.util.*;

public class GroupAlgorithm {
	private GameMatrix board;
	private GameMatrix deck;
	private List<Conflict> conflictList;
	private boolean verbose = false;
	private double setup_execution_time;
	private double run_execution_time;
	private GameMatrix best_solution;
	private int best_value;
	//private double[] scoreMatrix;
	private int[] score_matrix;
	private int maxScore;
	public Set<BitSet> validStates = new LinkedHashSet<BitSet>();
	
	public GroupAlgorithm(BitSet board_bitset, BitSet deck_bitset) {
		this(board_bitset, deck_bitset,false);
	}
	
	public GroupAlgorithm(BitSet board_bitset, BitSet deck_bitset, boolean verbose) {
		
		this(new GameMatrix(board_bitset),new GameMatrix(deck_bitset), null,verbose);

	}
	
	public GroupAlgorithm(BitSet board_bitset, BitSet deck_bitset, int[] score_matrix, boolean verbose) {
		this(new GameMatrix(board_bitset),new GameMatrix(deck_bitset), score_matrix, verbose);
	}
	
	public GroupAlgorithm(GameMatrix board, GameMatrix deck_arg, int[] score_matrix,boolean verbose) {
		this.board = board;
		this.best_solution = board;
		this.best_value = 0;
		this.deck = deck_arg;

		for(int i = 0; i < score_matrix.length; i++){
			this.deck.getBitList().set(i, this.deck.getBitList().get(i) && (score_matrix[i]>=0));
		}
		System.out.println(this.deck);

		this.verbose = verbose;
		this.setup_execution_time = 0;
		this.setup_execution_time = 0;
		//this.scoreMatrix = scoreMatrix;
		this.score_matrix = score_matrix;
		for(int i = 0; i < score_matrix.length; i++){
			score_matrix[i] *=(this.deck.getBitList().get(i)) ? 1:0;
			//score_matrix[i] =(this.board.getBitList().get(i)) ? 1:score_matrix[i];
		}
		this.maxScore = (score_matrix != null) ? sumArr(score_matrix) : 0;

		this.maxScore = ((this.maxScore > 0)? 1:0) * this.maxScore;
		System.out.println(this.maxScore);	
		System.out.println(score_matrix);
		/*
		if(verbose) {
			debug("** Initial check: ");
			debug("** Board: ");
			debug(this.board.toString());
			
			debug("** deck: ");
			debug(this.deck.toString());
			
			debug("** Complete: ");
			GameMatrix complete = board.or(deck);
			debug(complete.toString());
			
			debug("** Runs: ");
			GameMatrix runs = complete.getRuns();
			debug(runs.toString());
			
			debug("** Groups: ");
			GameMatrix groups = complete.getGroups();
			debug(groups.toString());
			
			this.conflictList = getConflictList(runs, groups);
			
			debug("** Conflicts: ");
			debug(this.conflictList.toString());
			
			int conflict = 0;
			debug("\n** Runs removed "+ this.conflictList.get(conflict) + ":");
			GameMatrix new_runs = removeRuns(this.conflictList.get(conflict), runs, groups);
			
			debug("\n** New Runs: ");
			debug("Valid: " + new_runs.getValid());
			debug(new_runs.toString());
			debug("\n* New Runs Complete: ");
			debug((groups.or(new_runs)).toString());
			
			debug("\n** Groups removed "+ this.conflictList.get(conflict) + ":");
			GameMatrix new_groups = removeGroups(this.conflictList.get(conflict), runs, groups);
			
			debug("** New Groups: ");
			debug("Valid: " + new_groups.getValid());
			debug(new_groups.toString());
			debug("\n* New Groups Complete: ");
			debug((runs.or(new_groups)).toString());
		}*/
	}
	
	private static int sumArr(int[] arr) {
		int total = 0;
		
		for(int val : arr) {
			total += val;
		}
		return total;
	}

	public int run() {
		long startTime = System.nanoTime();
		
		GameMatrix complete = board.or(deck);
		GameMatrix runs = complete.getRuns();
		GameMatrix groups = complete.getGroups();
		
		GameMatrix boardConflicts = getConflictMatrix(board.getRuns(),board.getGroups());
		this.conflictList = getConflictList(runs, groups, boardConflicts);
		debug("Conflicts: " + this.conflictList);
		
		long endTime = System.nanoTime();
		
		setup_execution_time = (double)(endTime - startTime)/1000000;
		
		startTime = System.nanoTime();
		
		solveConflicts(runs, groups, -1);
		
		endTime = System.nanoTime();
		
		run_execution_time = (double)(endTime - startTime)/1000000;
		
		return this.best_value;
	}
	
	private void debug(String arg) {
		if(this.verbose){
			System.out.println(arg);
		}
	}
	
	private List<Conflict> getConflictList(GameMatrix runs, GameMatrix groups, GameMatrix boardConflicts) {
		GameMatrix conflictMatrix = getConflictMatrix(runs,groups);
		
		GameMatrix newConflicts = conflictMatrix.and(deck);
		
		debug("** Conflict matrix: \n" + conflictMatrix);
		List<Conflict> cTmp = new LinkedList<Conflict>();

		Collections.sort(cTmp);
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				if(conflictMatrix.getBit(i, j)) {
					cTmp.add(new Conflict(i,j, newConflicts.getBit(i, j)));
				}
			}
		}
		Collections.sort(cTmp);
		return cTmp;
	}
	
	private GameMatrix getConflictMatrix(GameMatrix runs, GameMatrix groups) {
		return runs.and(groups);
	}
	
	private int solveConflicts(GameMatrix runs, GameMatrix groups, int nConflict) 
	{
		GameMatrix new_runs, new_groups;
		Conflict conflict;
		
		nConflict = getNextConflict(runs,groups,nConflict);
		
		if(nConflict == -1) {
			
			//System.out.println(runs.or(groups));
			//int value = GameMatrix.getValue(runs.or(groups));
			int value = maxScore - runs.lostScore - groups.lostScore;
			validStates.add(runs.or(groups).getBitList());
			if(value > this.best_value) {
				this.best_value = value;
				this.best_solution = runs.or(groups);
				//System.out.println("** Terminal node with best value "+ value +"** ");
			}
			return 0; //No more conflicts, terminal node
		}
		
		conflict = conflictList.get(nConflict);
		
		
		
		//debug("\n** New runs: " + new_runs);
		

		new_groups = removeGroups(conflict,runs,groups);
		new_runs = removeRuns(conflict,runs,groups);
		
		int groupStateLostScore = new_groups.lostScore - groups.lostScore;
		int runStateLostScore =  new_runs.lostScore - runs.lostScore;
		
		//debug("\n** New groups: " + new_groups);
		if((maxScore-new_groups.lostScore) > best_value) {
			if(new_groups.getValid()) {
				groupStateLostScore += solveConflicts(runs, new_groups,nConflict);
			}else{
				groupStateLostScore = 100000;
			}
		}
		
		if((maxScore-new_runs.lostScore) > best_value) {
			if(new_runs.getValid()) {
				runStateLostScore += solveConflicts(new_runs, groups,nConflict);
			}
			else {
				runStateLostScore = 100000;
			}
		}

		if(runStateLostScore<groupStateLostScore) {
			return runStateLostScore;
		}
		return groupStateLostScore;
	}

	
	
	private int getNextConflict(GameMatrix runs, GameMatrix groups, int n) {
		boolean isRun, isGroup;
		Conflict each;
		for (n = n+1; n < conflictList.size(); n++) {
			each = conflictList.get(n);
			isRun = runs.getBit(each.getRow(), each.getColumn());
			isGroup = groups.getBit(each.getRow(), each.getColumn());
			if(isRun && isGroup) {
				return n;
			}
        }
		return -1;
	}
	
	private GameMatrix removeRuns(Conflict c, GameMatrix runs, GameMatrix groups) {
		//   e1 x1 x2 c x3 x4 e2
		
		GameMatrix new_runs = new GameMatrix(runs);
		int row = c.getRow();
		int column = c.getColumn();
		
		int score = 0;
		int new_score = 0;
		
		boolean e1 = new_runs.getBit(row, column-3);
		boolean x1 = new_runs.getBit(row, column-2);
		boolean x2 = new_runs.getBit(row, column-1);
		boolean x3 = new_runs.getBit(row, column+1);
		boolean x4 = new_runs.getBit(row, column+2);
		boolean e2 = new_runs.getBit(row, column+3);
		
		boolean b1 = board.getBit(row, column-2);
		boolean b2 = board.getBit(row, column-1);
		boolean b3 = board.getBit(row, column+1);
		boolean b4 = board.getBit(row, column+2);
		
		boolean g1 = groups.getBit(row, column-2);
		boolean g2 = groups.getBit(row, column-1);
		boolean g3 = groups.getBit(row, column+1);
		boolean g4 = groups.getBit(row, column+2);
		
		if(score_matrix!=null) {
			score += ((g1 || x1) ? score_matrix[(row*13)+column-2]:0);
			score += ((g2 || x2) ? score_matrix[(row*13)+column-1]:0);
			score += ((g3 || x3) ? score_matrix[(row*13)+column+1]:0);
			score += ((g4 || x4) ? score_matrix[(row*13)+column+2]:0);
		}
		
		x1 = e1 & x1;
		x2 = x1 & x2;
		
		x4 = e2 & x4;
		x3 = x4 & x3;
		
		boolean v1 = !b1 || (b1 && (g1 || x1));
		boolean v2 = !b2 || (b2 && (g2 || x2));
		boolean v3 = !b3 || (b3 && (g3 || x3));
		boolean v4 = !b4 || (b4 && (g4 || x4));
		
		boolean valid = v1 && v2 && v3 && v4;
		
		if(!valid) {
			new_runs.setValid(false);
			return new_runs;
		}
		
		new_runs.setBit(row, column-2, x1);
		new_runs.setBit(row, column-1, x2);
		new_runs.setBit(row, column, false);
		new_runs.setBit(row, column+1, x3);
		new_runs.setBit(row, column+2, x4);

		if(score_matrix==null) {
			return new_runs;
		}
		
		new_score += ((g1 || x1) ? score_matrix[(row*13)+column-2]:0);
		new_score += ((g2 || x2) ? score_matrix[(row*13)+column-1]:0);
		new_score += ((g3 || x3) ? score_matrix[(row*13)+column+1]:0);
		new_score += ((g4 || x4) ? score_matrix[(row*13)+column+2]:0);
		
		new_runs.lostScore += (score-new_score);
		
		return new_runs;
	}
	
	private GameMatrix removeGroups(Conflict c, GameMatrix runs, GameMatrix groups) {
		//   e1 x1 x2 c x3 x4 e2
		GameMatrix new_groups = new GameMatrix(groups);
		
		int row = c.getRow();
		int column = c.getColumn();
		
		int score=0;
		int new_score=0;
		
		boolean g1 = new_groups.getBit(0, column);
		boolean g2 = new_groups.getBit(1, column);
		boolean g3 = new_groups.getBit(2, column);
		boolean g4 = new_groups.getBit(3, column);
		boolean val  = g1 && g2 && g3 && g4;
		
		if(val) {
			new_groups.setBit(row, column, false);
			return new_groups;
		}

		
		boolean b1 = board.getBit(0, column);
		boolean b2 = board.getBit(1, column);
		boolean b3 = board.getBit(2, column);
		boolean b4 = board.getBit(3, column);
		
		boolean r1 = runs.getBit(0, column);
		boolean r2 = runs.getBit(1, column);
		boolean r3 = runs.getBit(2, column);
		boolean r4 = runs.getBit(3, column);
		
		if(score_matrix != null) {
			score += ((g1 || r1) ? score_matrix[column]:0);
			score += ((g2 || r2) ? score_matrix[13+column]:0);
			score += ((g3 || r3) ? score_matrix[26+column]:0);
			score += ((g4 || r4) ? score_matrix[39+column]:0);
		}

		g1=false;
		g2=false;
		g3=false;
		g4=false;
		
		boolean v1 = !b1 || (b1 && (g1 || r1));
		boolean v2 = !b2 || (b2 && (g2 || r2));
		boolean v3 = !b3 || (b3 && (g3 || r3));
		boolean v4 = !b4 || (b4 && (g4 || r4));
		
		boolean valid = v1 && v2 && v3 && v4;
		
		if(!valid) {
			new_groups.setValid(false);
			return new_groups;
		}
		
		
		new_groups.setBit(0, column, g1);
		new_groups.setBit(1, column, g2);
		new_groups.setBit(2, column, g3);
		new_groups.setBit(3, column, g4);
		
		if(score_matrix == null) {
			return new_groups;
		}
		
		new_score += ((g1 || r1) ? score_matrix[column]:0);
		new_score += ((g2 || r2) ? score_matrix[13+column]:0);
		new_score += ((g3 || r3) ? score_matrix[26+column]:0);
		new_score += ((g4 || r4) ? score_matrix[39+column]:0);
		
		new_groups.lostScore += (score-new_score);
		
		return new_groups;
	}
	
	public GameMatrix getResults() {
		return best_solution;
	}
	public int getBestValue() {
		return this.best_value;
	}
	
	public double getSetup_execution_time() {
		return setup_execution_time;
	}

	public double getRun_execution_time() {
		return run_execution_time;
	}
}
