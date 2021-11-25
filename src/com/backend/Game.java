package psiC;

import java.util.ArrayList;


public class Game {

	
public  ArrayList<players> players;
//private ArrayList<Piece> enable_Pieces;
private short [][]enable_Pieces;
private int number_players;             




public  ArrayList<players> getPlayers(){
	return players; 
}

public Game(int players) {
		this.number_players=players;
		setPlayer(number_players);
		
	}

private void setPlayer(int number ) { 
	players= new ArrayList<players>();
	for (int i=0; i<number-1; i++) {
		
		players.add(new random(i,dealer()));
		
	}	
	
		players.add(new person(number-1,dealer()));
}


public boolean endGame (players player) {
	if(player.get_number_of_Pieces()==0 || board.getRestSize()==0) {
		return true;
	}else {
		return false;
	}
}



private short [] [] dealer() {
	
	short [] []random= new short [13] [4];
	enable_Pieces= Piece.getPool();	   // NO INCLUYE COMODINES 
	//Collections.shuffle(enable_Pieces);
	boolean ok=false;
	ArrayList<String>used= new ArrayList<>();
	
	for (int i =0; i<6; i++) {
		while (ok==false) {
			
			short valor = (short)Math.floor(Math.random()*(13-1+1)+1);
			short valor2 = (short)Math.floor(Math.random()*(4-1+1)+1);
			String result= String.valueOf(valor)+String.valueOf(valor2);
			if (!used.contains(result)) {
				used.add(result);
				ok=true; 
				random [valor][valor2]=1;
				enable_Pieces [valor] [valor]=0;
			}
		}
		ok=false;
	}
	board.setRest(enable_Pieces); // las pieces que faltarían por salir 
	return random;	
}



	
	
}
