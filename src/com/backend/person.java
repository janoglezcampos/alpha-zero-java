package psiC;



import java.util.Scanner;





public class person extends players  {

	

	
	
	
	public person(int id, short[][] Pieces) {
		super(id, Pieces);
		// TODO Auto-generated constructor stub
	}

	public void  getAction (board board )  { // NO ES LA DEFINITIVA SOLO DE PRUEBAS
		
		short piece[]= new short [2];
		short[][] rest= getPieces();
		short position[]= new short [2];
		//System.out.println("pillado antes del bucle");
		short color;
		short number; 
	    String entradaTeclado = "";
	    @SuppressWarnings("resource")
		Scanner entradaEscaner = new Scanner (System.in); 
	    
	       while(true){
	    	   
	    	   System.out.println ("Por favor introduzca una pieza por teclado, ejemplo: 0-3,0-12");
	    	   entradaTeclado = entradaEscaner.nextLine (); 
	    	   String[] parts = entradaTeclado.split(",");
	    	   try {
	    		    color= (short) Integer.parseInt((parts[0]));
		    	    number= (short) Integer.parseInt((parts[1]));
		    	    if (rest[color] [number]==1) {
		    		   	piece [0]= color;
		    		   	piece [1]= number;
					}else {    
						System.out.println("Datos erroneos");
						continue;   
					}
	    	   } catch (Exception e) {
				// TODO: handle exception
	    		   System.out.println("Datos erroneos");
	    		   continue;
	    	   }
	    	  
	    	   System.out.println ("Por favor introduzca donde desea situarla por teclado, ejemplo: 0-3,0-12");
	    	   entradaTeclado = entradaEscaner.nextLine (); 
	    	   parts = entradaTeclado.split(",");
	    	   
	    	   try {
	    		   short position1= (short) Integer.parseInt((parts[0]));
		    	   short position2= (short) Integer.parseInt((parts[1]));
		    	   
		    	   if (position1<=3&&position1>=0&&position2<=12&&position2>=0) {
		    		   position [0]= position1;
			    	   position [1]=position2;
					}else {    
						System.out.println("Datos erroneos");
						continue;   
					}
	    	   } catch (Exception e) {
				// TODO: handle exception
	    		   System.out.println("Datos erroneos de posición");
	    		   continue;
	    	   }
	    	  
	    	   rest[color] [number]=0;
			   delete_Piece(color, number); // solo si se la dan por buena....
	    	   board.place(piece,position);
	   	       System.out.println("Pieza colocada, piezas que me quedan: "+get_number_of_Pieces());
	   	        
	    	   break;
	       }
	       //entradaEscaner.close(); // PORQUE ALL CERRARLO SE ME VA A LA VERGA EN LA ITERACIÓN 2
	       
		/*
		boolean ok=false;
		while (ok==false) {   /// mejorar haciendo aleatorio donde ya sabemos que hay, menos iteraciones 
			
			short valor = (short)Math.floor(Math.random()*(12-0+1)+0);
			short valor2 = (short)Math.floor(Math.random()*(3-0+1)+0);
			if (rest[valor2] [valor]==1) {
				piece[0]=valor2;
				piece[1]=valor;
				ok=true; 
				rest[valor2] [valor]=0;
				delete_Piece(valor2, valor); // solo si se la dan por buena....
			}
		}
		
		
		position [1] = (short)Math.floor(Math.random()*(12-0+1)+0);
		position [0]= (short)Math.floor(Math.random()*(3-0+1)+0);
		//System.out.println("pillado despues del bucle");
		
		board.place(piece,position);
	    System.out.println("Pieza colocada, piezas que me quedan: "+get_number_of_Pieces());
	    */
	    
	    
	    
	}
	
}
