package psiC;



public class Piece {
	  
	
	public static final String[] COLOR_LIST =  {"AZUL", "ROJO", "NARANJA", "NEGRO"};
	
	public String getColor(short i) {
		return COLOR_LIST[i];
	}
	
	public String getPiece(short i,short j){
		
		String piece= String.valueOf(i)+"-"+getColor(j);
		return piece;
	}
	

	public static short [][] getPool(){  // SIN COMODINES 
	
	short [][] baraja= new short [13][4];
	for (short  i=1; i<14; i++) {
		
		for (short j=0;j<4;j++) {
			
			baraja [i][j]=1;
		}
	}
	
	return baraja;
	}
	
}