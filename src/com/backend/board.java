package psiC;

import java.util.ArrayList;

import com.sun.tools.javac.code.Attribute.Array;



public class board {

	public  ArrayList < ArrayList<short []>>  table;		// tablero real 
	public short [][] matrix;       // Tablero en matriz ordenada 
	public  static short [][] rest; // PIEZAS QUE AUN QUEDAN EN LA BARAJA
	
	public board () {
		table= new ArrayList<>();
		matrix= new short[13][4];
		generateMatrix();
		generateBoard();
	}   
	
	public ArrayList < ArrayList<short []>> getTable(){
		
		return table; 
	}	
	
	public static void setRest(short [] [] cartas) {
		
		board.rest=cartas;
	}
	
	public static int getRestSize() {
		int total=0;
		
		for (int i=0;i<13; i++) {
			for (int j=0;j<4; j++) {
				
				total+=rest[i][j];
				if (total!=0) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	public short []  delearOne() {
		short [] e= new short [2];
		boolean ok=false;
		while (ok==false) {   /// mejorar haciendo aleatorio donde ya sabemos que hay, menos iteraciones 
			
				short valor = (short)Math.floor(Math.random()*(13-1+1)+1);
				short valor2 = (short)Math.floor(Math.random()*(4-1+1)+1);
				if (rest[valor] [valor2]==1) {
					e[0]=valor;
					e[1]=valor2;
					ok=true; 
					rest[valor] [valor]=0;
				}
		}
			return e;
	}
	public void generateMatrix() {
		
		for (int i=0;i<13; i++) {
			for (int j=0;j<4; j++) {
				
				matrix[i][j]=0;
			}
		}
	}
	
	public void generateBoard () {
		ArrayList<short []> data= new ArrayList<>();
		short [] number= {0,0};
		
		for (int j=0;j<13; j++) {
			data.add(number);
		}
		for (int i=0;i<4; i++) {
			table.add(data);
		}
	}
	
	
	public void  place ( short [] piece ,short [] position) { // primero la fila 

		if (table.get(position[0]).get(position[1])[0]==0 ) {
			
			table.get(position[0]).get(position[1])[0]=piece[0];
			table.get(position[0]).get(position[1])[1]=piece[1];
			
		} else {
			System.out.println("BAD POSITION");	
		}
	}
	
	
	
	public int  checkBoard() {
		ArrayList<Piece> found= new ArrayList<>();
		
		for (int j=0; j<3;j++) {
			for (int i=0; i<12;i++) {
				
				if(table [i][j]!= null) {
					
					if(found.size()==0) {
						found.add(table[i][j]);
					}else if (table[i-1][j]!=null) {
						found.add(table[i][j]);
					}else if (table[i-1][j]==null) { // aqui acabamos una cadena 
						
						boolean ok = checkGroup(found);
						if (ok==false) {
							return -1;
						}
					}
				}// es nula la posicion 
			}
			found.clear();
		}
		return 1;
	}


	private boolean checkGroup(ArrayList<Piece> found) {
		int check=0;
		for (int i=0; i<found.size()-2;i++) {
			if(found.get(i).getNumber()!=found.get(i+1).getNumber()+1){
				check=-1;
			}
		}
		for (int i=0; i<found.size()-2;i++) {
			if(found.get(i).getNumber()!=found.get(i+1).getNumber()){ // sin repeticion de colores 
				check=-1;
			}
		}
		if (check==-2) {
			return false;
		}else {
			return true ; 
		}
	} 
	
	
	public void updateMatrix() {
		
		for (int i=0;i<12; i++) {
			for (int j=0;j<3; j++) {
				
				if (table[j][i]!= null ) {
					
					matrix[ table[j][i].getPosition()[0]  ]   [ table[j][i].getPosition()[1] ] =1;	
				}
			}
		}

	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
