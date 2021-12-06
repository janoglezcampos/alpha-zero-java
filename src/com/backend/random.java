package psiC;



public class random extends players  {

	

	
	
	public random(int id, short[][] Pieces) {
		super(id, Pieces);
		// TODO Auto-generated constructor stub
	}

	public short[][]  getAction (board tapete) {
		
		short[][] rest= getPieces();//PIEZAS EN LA MANO
		short tablero_nuevo [][]={{1,1,1,1,0,0,0,0,0,0,0,0,0}, {0,0,0,1,0,0,0,0,0,0,0,0,0}, {0,0,0,1,0,0,0,0,0,0,0,0,0},{1,1,1,0,0,0,0,0,0,0,0,0,0}};
		
	
		
		System.out.println("MATRIX OPRIGINAL ");
	    for (int i=0;i<4; i++) {
			for (int j=0;j<13; j++) {
				System.out.print(tapete.getMatrix()[i][j]);
				}
				System.out.print("\n");
			}
		
		
		//ESTE CODIGO ERA PARA INSERTAR UNA PIEZA DISPONIBLE ALEATORIA
		/*
		short new_table[][]= new short [4][13];
		for(int i=0;i<tapete.getMatrix().length;i++) {
			new_table[i]=Arrays.copyOf(tapete.getMatrix()[i], tapete.getMatrix()[i].length);
		}
		//System.out.println("pillado antes del bucle");
		boolean ok=false;
		while (ok==false) {   /// mejorar haciendo aleatorio donde ya sabemos que hay, menos iteraciones 
			
			short valor = (short)Math.floor(Math.random()*(12-0+1)+0);
			short valor2 = (short)Math.floor(Math.random()*(3-0+1)+0);
			if (rest[valor2] [valor]==1) {
				new_table[valor2] [valor]=1;
				ok=true; 
				rest[valor2] [valor]=0;
				delete_Piece(valor2, valor);
			}
		}*/

		
	    System.out.println("IA MANDA COLOCAR LA SIGUIENTE TABLA ");
	    for (int i=0;i<4; i++) {
			for (int j=0;j<13; j++) {
				System.out.print(tablero_nuevo[i][j]); 
				}
				System.out.print("\n");
			}

		
		/*
		//PROCEDEMOS A BORRAR LAS PIEZAS PUESTAS DE LA MANO
		for (int i=0;i<4; i++) {
				for (int j=0;j<13; j++) {
					if(tablero_nuevo[i][j]==1&&rest[i][j]==1) {
						delete_Piece((short)i,(short)j);
					}
				}
		}*/
		
		
		return(tablero_nuevo);
		//board.placeRandom(tablero_nuevo);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
