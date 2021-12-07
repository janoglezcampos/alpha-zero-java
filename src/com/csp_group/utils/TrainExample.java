package com.csp_group.utils;

public class TrainExample {

	public GameMatrix board;
	public int curPlayer;
	public Double[] pi;
	public int result;
	
	public TrainExample(GameMatrix board, int curPlayer, Double[] pi) {
		this.board = board;
		this.curPlayer = curPlayer;
		this.pi = pi;
	}
}
