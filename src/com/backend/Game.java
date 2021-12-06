package psiC;

import java.util.ArrayList;


public class Game {

	
public  ArrayList<players> players;
//private ArrayList<Piece> enable_Pieces;
private int number_players;
private board board;




public  ArrayList<players> getPlayers(){
	return players; 
}

public Game(int players, int person,board board) {
		this.number_players=players;
		this.board=board;
		setPlayer(number_players,person);
		
	}

private void setPlayer(int number,int person ) { 
	board.setRest(Piece.getPool());
	players= new ArrayList<players>();
	if (person==1) {
		for (int i=0; i<number-1; i++) {
			players.add(new random(i,dealer()));
			System.out.println(board.getRestSize2());
		}	
			players.add(new person(number-1,dealer()));
			//System.out.println(board.getRestSize2());
	}else {
		
		for (int i=0; i<number; i++) {
			players.add(new random(i,dealer()));
			//System.out.println(board.getRestSize2());
		}	
		
	}

}


public  boolean endGame (players player) {
	if(player.get_number_of_Pieces()==0 || board.getRestSize()==0) {
		return true;
	}else {
		return false;
	}
}



private short [] [] dealer() {
	
	short [] []random= new short [4] [13];
	short [][]enable_Pieces= new short [4][13];
	enable_Pieces= board.getRest().clone();         
	//Collections.shuffle(enable_Pieces);
	boolean ok=false;
	ArrayList<String>used= new ArrayList<>();
	String result;
	
	for (short i=0; i<4; i++) {
		for (short j=0;j<13;j++) {
			if (enable_Pieces[i][j]==1) {
				result= String.valueOf(j)+String.valueOf(i);
				used.add(result);
			}
		}
	}
	for (int i =0; i<6; i++) {
		while (ok==false) {
			//int numero = (int)(Math.random()*(X-Y+1)+Y;
			//int numero = (int)(Math.random()*(75-25+1)+25);
			
			short valor = (short)Math.floor(Math.random()*(12-0+1)+0);
			short valor2 = (short)Math.floor(Math.random()*(3-0+1)+0);
			result= String.valueOf(valor)+String.valueOf(valor2);
			if (used.contains(result)) {
				used.remove(result);
				ok=true; 
				random [valor2][valor]=1;
				enable_Pieces [valor2] [valor]=0;
			}
		}
		ok=false;
	}
	board.setRest(enable_Pieces); // las pieces que faltarían por salir 
	return random;	
}



	
	
}
