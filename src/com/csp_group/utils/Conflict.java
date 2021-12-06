package com.csp_group.utils;
public class Conflict implements Comparable<Conflict> {
	int row, column;
	int isNewConflict = 0;
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Conflict (int row, int column){
		this.row = row;
		this.column = column;
	}
	public Conflict (int row, int column, boolean isNew){
		this.row = row;
		this.column = column;
		this.isNewConflict = isNew ? 1 : 0;
	}
	
	public String toString() {
		return '['+Integer.toString(row)+','+Integer.toString(column)+"] "+this.isNewConflict;
	}

	@Override
	public int compareTo(Conflict o) {
		return isNewConflict - o.isNewConflict;
	}
}
