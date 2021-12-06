package psiC;

import java.util.ArrayList;

public class Piece {
	  
	
	public static final String[] COLOR_LIST =  {"NULL","AZUL", "ROJO", "NARANJA", "NEGRO"};
	
	public static String getColor(short i) {
		return COLOR_LIST[i];
	}
	
	public static  String getPiece(short i,short j){
		
		String piece= String.valueOf(i)+"-"+getColor(j);
		return piece;
	}
	

	public static short [][] getPool(){  // SIN COMODINES 
	
	short [][] baraja= new short [4][13];
	for (short  i=0; i<4; i++) {
		
		for (short j=0;j<13;j++) {
			
			baraja [i][j]=1;
		}
	}
	
	return baraja;
	}
	
	
	public static  ArrayList<String> getStringPieces (short [] [] Pieces) {
		
		ArrayList<String> cartas=  new ArrayList<String>();
		
		for (short  i=0; i<4; i++) {
			
			for (short j=0;j<13;j++) {
				if (Pieces[i][j]!=0) {
					cartas.add(getPiece((short)(j+1),(short)(i+1)));
				}
			}
		}
		return cartas; 
	}	
	
	
}