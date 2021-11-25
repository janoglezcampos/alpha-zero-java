package com.csp_group.utils;
import java.util.*;
import java.util.BitSet;

import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic1DMatrix;


public class GameMatrix {
	private BitSet bit_list;
	
	private int n;
	private int k;
	private int m;
	private boolean valid;
	public int lostScore=0;
	
	public GameMatrix(int n, int k, int m) {
		this.n = n;
		this.k = k;
		this.m = m;
		this.valid = true;
		
		this.bit_list = new BitSet(n*k);
	}
	
	public List<BitSet> getMatrix() {
		List<BitSet> matrix = new ArrayList<BitSet>();
		matrix.add(bit_list.get(0,13));
		matrix.add(bit_list.get(13,26));
		matrix.add(bit_list.get(26,39));
		matrix.add(bit_list.get(39,52));
		return matrix;
	}
	
	public GameMatrix(BitSet bit_list) {
		this.bit_list = bit_list;
		this.n = 13;
		this.k = 4;
		this.m = 1;
		this.valid = true;
	}
	
	public GameMatrix(GameMatrix copy) {
		this.n = copy.n;
		this.k = copy.k;
		this.m = copy.m;
		this.valid = copy.valid;
		
		this.bit_list = (BitSet) copy.bit_list.clone();
	}
	
	public boolean getValid() {
		return valid;
	}
	
	public void setValid(boolean v) {
		this.valid = v;
	}
	
	public BitSet getBitList(){
		return bit_list;
	}
	
	public GameMatrix and(GameMatrix matrix) {
		GameMatrix tmp = new GameMatrix(this);
		BitSet bs = tmp.getBitList();
		bs.and(matrix.getBitList());
		return tmp;
	}
	
	public GameMatrix or(GameMatrix matrix) {
		GameMatrix tmp = new GameMatrix(this);
		BitSet bs = tmp.getBitList();
		bs.or(matrix.getBitList());
		return tmp;
	}
	
	public GameMatrix andNot(GameMatrix matrix) {
		GameMatrix tmp = new GameMatrix(this);
		BitSet bs = tmp.getBitList();
		bs.andNot(matrix.getBitList());
		return tmp;
	}
	
	public boolean getBit(int row, int column) {
		if(column < 0 || column >= this.n) {
			//System.out.println("Care outbounds getBit");
			return false;
		} 
		return bit_list.get((row*n)+column);
	}
	
	public void setBit(short row, short column, int value) {
		if(column >= 0 && column < this.n) {
			int pos = (row*n)+column;
			bit_list.set(pos, value != 0);
		}
	}
	public void setBit(int row, int column, boolean value) {
		if(column >= 0 && column < this.n) {
			bit_list.set((row*n)+column,value);
		}
	}
	
	public GameMatrix getRuns() {
		GameMatrix runs = new GameMatrix(13,4,1);
		List<BitSet> matrix = this.getMatrix();
		for(int i = 0; i < 4;i++) {
			for(int j = 0; j < 11;j++) {
				if(matrix.get(i).get(j) && matrix.get(i).get(j+1) && matrix.get(i).get(j+2)) {
					runs.setBit(i, j, true);
					runs.setBit(i, j+1, true);
					runs.setBit(i, j+2, true);
				}
			}
		}
		return runs;
	}
	
	private static BitSet getGroupBitset(BitSet g0, BitSet g1, BitSet g2, BitSet g3) {
		BitSet ha1, ha2, ho1, ho2;
		
		ha1 = new BitSet(g0.length());
		ha2 = new BitSet(g0.length());
		ho1 = new BitSet(g0.length());
		ho2 = new BitSet(g0.length());
		
		ha1.or(g0); //Cargamos en ha1
		ha1.and(g1); //g0 and g1 en ha1
		
		ha2.or(g2);
		ha2.and(g3);
		
		ho1.or(g0);
		ho1.or(g1);
		
		ho2.or(g2);
		ho2.or(g3);
		
		ha1.or(ha2);
		
		ho1.and(ho2);
		
		ha1.and(ho1);
		
		return ha1;
	}
	
	public GameMatrix getGroups() {
		GameMatrix groups = new GameMatrix(this);
		List<BitSet> matrix = getMatrix();
		BitSet g0 = matrix.get(0);
		BitSet g1 = matrix.get(1);
		BitSet g2 = matrix.get(2);
		BitSet g3 = matrix.get(3);
		
		BitSet groupBitSet = getGroupBitset(g0, g1, g2, g3);
		
		for(int i = 0; i < 13;i++) {
			if(!groupBitSet.get(i)) {
				groups.setBit(0, i, false);
				groups.setBit(1, i, false);
				groups.setBit(2, i, false);
				groups.setBit(3, i, false);
			}
		}
		return groups;
	}
	
	public static int getValue(GameMatrix matrix) {
		return matrix.bit_list.cardinality();
	}
	
	public static int getValue(GameMatrix matrix, double[] costArray) {
		if(costArray == null) {
			return getValue(matrix);
		}
		Matrix costMatrix = new Basic1DMatrix(1,costArray.length,costArray);
		double[] matrixArr = bitsetToDoubleArr(matrix.bit_list, costArray.length);

		Matrix originalsMatrix = new Basic1DMatrix(matrixArr.length,1,matrixArr);
		return (int)(costMatrix.multiply(originalsMatrix).sum()*100);
	}
	
	private static double[] bitsetToDoubleArr(BitSet set, int size) {
		double[] arr = new double[size];
		
		for(int i = 0; i < set.length(); i++) {
			arr[i] =(double) (set.get(i)? 1 : 0);
		}
		
		return arr;
	}
	
	public String toString() {
		List<BitSet> matrix = this.getMatrix();
		String val = "BitSet: " + bit_list.toString() +"\nMatrix:\n";
		for(BitSet row : matrix) {
			String set="[";
			for(int i = 0; i < 13; i++) {
				if(row.get(i)) {
					set += "1";
				}
				else {
					set += "0";
				}
				if(i!=12) {
					set +=", ";
				}
			}
			val+=(set+"]\n");
		}
		return val;
	}


}
