package com;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import Jama.Matrix;

import com.csp_group.GroupAlgorithm;
import com.csp_group.utils.GameMatrix;
import org.apache.commons.lang3.*;

public class GroupAlgorithmTest {
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		int[] board_bitset_0 = {
							0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0
							};
		@SuppressWarnings("unused")
		int[] deck_bitset_0 = {
							1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
							1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0,
							0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
							};
		@SuppressWarnings("unused")
		int[] board_bitset_1 = {
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0
							};

		@SuppressWarnings("unused")
		int[] deck_bitset_1 = {
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1
							};
		@SuppressWarnings("unused")
		int[] board_bitset_2 = {
							1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0,
							1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0,
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
							};
		@SuppressWarnings("unused")
		int[] deck_bitset_2 = {
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0,
							0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
							1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
							};

		@SuppressWarnings("unused")
		double[] score = {
				2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				3, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
				};
		@SuppressWarnings("unused")
		double[] decimal_score = {
				0.92 ,0.33 ,0.62 ,0.58 ,0.37 ,0.78 ,0.14 ,0.41 ,0.39 ,0.99 ,0.30 ,0.17 ,0.88,
				0.40 ,0.12 ,0.74 ,0.64 ,0.55 ,0.71 ,0.48 ,0.97 ,0.94 ,0.19 ,0.63 ,0.34 ,0.32,
				0.1 ,0.93 ,0.90 ,0.63 ,0.02 ,0.38 ,0.97 ,0.81 ,0.37 ,0.50 ,0.42 ,0.80 ,0.46,
				0.22 ,0.1 ,0.51 ,0.30 ,0.10 ,0.24 ,0.64 ,0.29 ,0.48 ,0.34 ,0.41 ,0.17 ,0.35
		};
		
		int[] int_score = {
				92 ,33 ,62 ,58 ,37 ,78 ,14 ,41 ,39 ,99 ,30 ,17 ,88,
				40 ,12 ,74 ,64 ,55 ,71 ,48 ,97 ,94 ,19 ,63 ,34 ,32,
				10 ,93 ,90 ,63 ,02 ,38 ,97 ,81 ,37 ,50 ,42 ,50 ,20,
				22 ,10 ,51 ,30 ,10 ,24 ,64 ,29 ,48 ,34 ,41 ,17 ,35
		};
		int[] int_score_2 = {
				92 ,33 ,62 ,58 ,37 ,78 ,14 ,41 ,39 ,99 ,30 ,17 ,88,
				40 ,12 ,74 ,64 ,55 ,71 ,48 ,97 ,94 ,19 ,63 ,34 ,32,
				10 ,93 ,90 ,63 ,02 ,38 ,97 ,81 ,37 ,50 ,42 ,50 ,20,
				22 ,10 ,51 ,30 ,10 ,24 ,64 ,29 ,48 ,34 ,41 ,17 ,35
		};
		
		@SuppressWarnings("unused")
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
		
		int[] board_bitset = board_bitset_2;
		int[] deck_bitset = deck_bitset_2;
		
		System.out.println("** Running test **");
		GroupAlgorithm alg = new GroupAlgorithm(listToBitset(board_bitset),listToBitset(deck_bitset),int_score,true);
		alg.setThreshold(100);
		alg.run();
		
		System.out.printf("\n** Found all runs, groups and conflicts in: %.2fms\n", alg.getSetup_execution_time());
		
		System.out.printf("\n** Found all  terminal nodes in: %.2fms\n", alg.getRun_execution_time());
	
		System.out.println("\n** Best solution with value "+alg.getBestValue()  +": \n" + alg.getResults());
		
		System.out.println("\n** Valid states: \n");
		
		List<Double> values = alg.validValues;
		int i = 0;
		for(BitSet state:alg.validStates) {
			System.out.println(new GameMatrix(state));
			System.out.println("Value: " + values.get(i));
			i++;
		}
		
		System.out.println("N valid actions: " + alg.validStates.size());
		System.out.println(int_score[0]);
		int[] int_score_3 = {20,30,10,
							 10,0,0,
							 10,0,0};
		jamaTest(int_score_3);
	}
	public class Action{
		public Double value;
		public BitSet newState;
		
		public Action(Double double1, BitSet set) {
			this.value = double1;
			this.newState = set;
		}
	}
	private List<Action> getPolicyArray(int[] board_bitset, int[] deck_bitset, int[] score_matrix) {
		GroupAlgorithm alg = new GroupAlgorithm(listToBitset(board_bitset),listToBitset(deck_bitset),score_matrix,false);
		alg.setThreshold(100);
		alg.run();
		List<Action> action_list = new ArrayList<Action>();
		List<Double> values = alg.validValues;
		int i = 0;
		for(BitSet state:alg.validStates) {
			action_list.add(new Action(values.get(i), state));
			i++;
		}
		
		return action_list;
	}
	
	public static void jamaTest(int[] score_matrix) {
		double[][] lhsArray1 = { {1, 1, 1, 1,1,0,0}, 
								 {0, 1, 1, 1,0,0,0}, 
								 {1, 1, 1, 0,0,1,0}};
		


		
		double[][] lhsArray2 = {{1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0},
								{1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0,1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0}};
		
		double[] rhsArray2 = {4.49, 4.54, 4.9};

		double[] rhsArray1 = {0.909326674, 1.428941916, 0.7794228634};
		
		double[][] lhsArray3 = {{1, 1, 1,
								 0, 0, 0,
								 0, 0, 0}, 
				 				{1, 0, 0,
								 1, 0, 0,
								 1, 0, 0}};
		
		double[] rhsArray3 = {0.30, 0.70};
		
		
		double[][] lhsArray = lhsArray3;
		double[] rhsArray = rhsArray3;
		
		double[] repeated = new double[lhsArray[0].length];
		double[][] not_repeated = new double[lhsArray.length][lhsArray[0].length];

		int[] zeros_arr = new int[lhsArray[0].length];
		Arrays.fill(repeated, 0.0);
		Arrays.fill(zeros_arr, 0);
		int repeated_count = 0;
		int zeros = 0;
		int not_repeated_count = 0;
		
		int last_j = 0;
		
		for(int i = 0; i < lhsArray[0].length; i ++) {
			double total = 0;
			for(int j = 0; j < lhsArray.length; j ++) {
				total+=lhsArray[j][i];
				if(lhsArray[j][i] == 1) {
					last_j = j;
				}
			}
			if(total == 0.0) {
				zeros+=1;
				zeros_arr[i]=1;
			}
		}

		lhsArray=removeCol(lhsArray, zeros_arr ,zeros);
		score_matrix=removeCol(score_matrix, zeros_arr ,zeros);
		
		for(int i = 0; i < lhsArray[0].length; i ++) {
			double total = 0;
			for(int j = 0; j < lhsArray.length; j ++) {
				total+=lhsArray[j][i];
				if(lhsArray[j][i] == 1) {
					last_j = j;
				}
			}
			if(total == lhsArray.length) {
				repeated[i] = 1;
				repeated_count+=1;
			}else
			if(total == 1.0) {
				not_repeated_count+=1;
				not_repeated[last_j][i] = 1;
			}
		}
		
		
		
		int i = 0;
		System.out.println("Zero values: " + zeros);
		System.out.println(Arrays.toString(zeros_arr));
		/*
		for(i = 0; i < zeros_arr.length; i++) {
			if(zeros_arr[i] !=0) {
				System.out.println("Removing");
				lhsArray=removeCol(lhsArray,i);
			}
		}*/
		boolean first = true;
		int pos1 = 0;
		int pos2 = 0;
		i = 0;
		for(Double val: repeated) {
			if(val != 0.0) {
				if(first) {
					pos1 = i; 
					first = false;
				}else {
					pos2 = i; 
					double[] ecuation = new double[lhsArray[0].length];
					Arrays.fill(ecuation, 0.0);
					ecuation[pos1] = 1.0;
					ecuation[pos2] = -1.0;
					lhsArray = ArrayUtils.add(lhsArray, ecuation);
					rhsArray = ArrayUtils.add(rhsArray, 0.0);
				}
			}
			i++;
		}
		
		for(double[] arr: not_repeated) {
			i=0;
			for(Double val: arr) {
				if(val != 0.0) {
						double[] ecuation = new double[lhsArray[0].length];
						Arrays.fill(ecuation, 0.0);
						ecuation[i] = 1.0;
						lhsArray = ArrayUtils.add(lhsArray, ecuation);
						System.out.println(score_matrix[0]);
						rhsArray = ArrayUtils.add(rhsArray, Double.valueOf(score_matrix[i])/100);
					}
				i++;
			}
			
		}
		
		System.out.println("Equations: \n" + Arrays.deepToString(lhsArray));
		System.out.println("N vars: " + lhsArray[0].length);
		System.out.println("Repeated values: " + repeated_count);
		System.out.println("Not Repeated values: " + not_repeated_count);
		System.out.println("Non repeated values: \n" + Arrays.deepToString(not_repeated));
		
		System.out.println("Equation n:" + lhsArray.length);
		System.out.println("n results:" + rhsArray.length);
		System.out.println("results:" + Arrays.toString(rhsArray));
		
		Matrix lhs = new Matrix(lhsArray);
		Matrix rhs = new Matrix(rhsArray, rhsArray.length);
		Matrix ans = lhs.solve(rhs);
		System.out.println(Arrays.deepToString(ans.getArray()));
	}
	
	private static int[][] removeCol(int [][] array, int colRemove)
	{
	    int row = array.length;
	    int col = array[0].length;

	    int [][] newArray = new int[row][col-1]; //new Array will have one column less


	    for(int i = 0; i < row; i++)
	    {
	        for(int j = 0,currColumn=0; j < col; j++)
	        {
	            if(j != colRemove)
	            {
	                newArray[i][currColumn++] = array[i][j];
	            }
	        }
	    }

	    return newArray;
	}
	private static double[][] removeCol(double [][] array, int[] colRemove, int zero_count)
	{
	    int row = array.length;
	    int col = array[0].length;

	    double [][] newArray = new double[row][col-zero_count]; //new Array will have one column less

	    for(int i = 0; i < row; i++)
	    {
	        for(int j = 0,currColumn=0; j < col; j++)
	        {
	            if(colRemove[j] == 0)
	            {
	            	System.out.println("adding");
	                newArray[i][currColumn++] = array[i][j];
	            }else {
	            	System.out.println("not adding " + j + " " + currColumn);
	            	
	            }
	        }
	    }

	    return newArray;
	}
	
	private static int[] removeCol(int [] array, int[] colRemove, int zero_count)
	{
	    int row = array.length;

	    int [] newArray = new int[row-zero_count]; //new Array will have one column less
	    
	    colRemove = ArrayUtils.addAll(colRemove,colRemove);
	    colRemove = ArrayUtils.addAll(colRemove,colRemove);
	    
	    for(int i = 0, currColumn=0; i < row; i++)
	    {
	            if(colRemove[i] == 0)
	            {
	            	System.out.println("adding");
	                newArray[currColumn++] = array[i];
	            }else {
	            	System.out.println("not adding " + i + " " + currColumn);
	            	
	            }
	    }

	    return newArray;
	}
	
	private static BitSet listToBitset(int[] list) {
		BitSet bs = new BitSet(list.length);
		for(int i =0; i < list.length; i++) {
			bs.set(i,list[i] != 0);
		}
		return bs;
	}
}
