package com;
import java.util.BitSet;

import com.csp_group.GroupAlgorithm;

public class GroupAlgorithmTest {
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		int[] board_bitset_0 = {
							0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
							};
		@SuppressWarnings("unused")
		int[] deck_bitset_0 = {
							1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
							1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0,
							0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1
							};
		@SuppressWarnings("unused")
		int[] board_bitset_1 = {
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0
							};

		@SuppressWarnings("unused")
		int[] deck_bitset_1 = {
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
							};
		@SuppressWarnings("unused")
		double[] score = {
				2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				3, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
				};
		
		double[] decimal_score = {
				0.92 ,0.33 ,0.62 ,0.58 ,0.37 ,0.78 ,0.14 ,0.41 ,0.39 ,0.99 ,0.30 ,0.17 ,0.88,
				0.40 ,0.12 ,0.74 ,0.64 ,0.55 ,0.71 ,0.48 ,0.97 ,0.94 ,0.19 ,0.63 ,0.34 ,0.32,
				0.1 ,0.93 ,0.90 ,0.63 ,0.02 ,0.38 ,0.97 ,0.81 ,0.37 ,0.50 ,0.42 ,0.80 ,0.46,
				0.22 ,0.1 ,0.51 ,0.30 ,0.10 ,0.24 ,0.64 ,0.29 ,0.48 ,0.34 ,0.41 ,0.17 ,0.35
		};
		
		int[] int_score = {
				92 ,33 ,62 ,58 ,37 ,78 ,14 ,41 ,39 ,99 ,30 ,17 ,88,
				40 ,12 ,74 ,64 ,55 ,71 ,48 ,97 ,94 ,19 ,63 ,34 ,32,
				10 ,93 ,90 ,63 ,02 ,38 ,97 ,81 ,37 ,50 ,42 ,80 ,46,
				22 ,10 ,51 ,30 ,10 ,24 ,64 ,29 ,48 ,34 ,41 ,17 ,35
		};
		
		int[] int_score_1 = {
				1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1,
				1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1,
				1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1,
				1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1
		};
		@SuppressWarnings("unused")
		int[] deck_score = {
				1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
				1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
				};
		
		//GameMatrix deck_score_mat = new GameMatrix(listToBitset(deck_score));
		//System.out.println("** Test score: " + GameMatrix.getValue(deck_score_mat, decimal_score));
		
		int[] board_bitset = board_bitset_0;
		int[] deck_bitset = deck_bitset_0;
		
		System.out.println("** Running test **");

		GroupAlgorithm alg = new GroupAlgorithm(listToBitset(board_bitset),listToBitset(deck_bitset),int_score,true);
		alg.run();
		
		System.out.printf("\n** Found all runs, groups and conflicts in: %.2fms\n", alg.getSetup_execution_time());
		
		System.out.printf("\n** Found all  terminal nodes in: %.2fms\n", alg.getRun_execution_time());
	
		System.out.println("\n** Best solution with value "+alg.getBestValue()  +": " + alg.getResults());
	}
	
	private static BitSet listToBitset(int[] list) {
		BitSet bs = new BitSet(list.length);
		for(int i =0; i < list.length; i++) {
			bs.set(i,list[i] != 0);
		}
		return bs;
	}
}
