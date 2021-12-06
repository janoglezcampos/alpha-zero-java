package psiC;

import java.util.ArrayList;

public class play {

	
	public static  void main (String[] args) {
		
		System.out.println("EMPIEZA EL JUEGO");
		board tapete= new board();
		Game now = new Game (Integer.parseInt(args[0]),Integer.parseInt(args[1]),tapete); 
		
		System.out.println("Numero de jugadores "+args[0]);
		
		showHands(now); // mostrar manos jugadores
		
		System.out.println("TABLERO INICIO JUEGO");
		showTable(tapete);
		
		boolean end= false;
		
		
		while (end!=true) {
			
			for (int i=0; i<now.getPlayers().size();i++) {
				System.out.println("Jugador con id "+now.getPlayers().get(i).getId()+" le toca turno");
				if (now.getPlayers().get(i) instanceof person) {
					person me = (person) now.getPlayers().get(i);
					 me.getAction(tapete); //incompleto
					 tapete.updateTable();
					 
					 me.updatePieces(); // sin implementar
					 
					 
					 
					 System.out.println("TABLERO ACTUAL");
					 showTable(tapete);
					 if (now.endGame(me)) {
						 System.out.println("FIN DEL JUEGO");
						 end=true;
						 break; 
					 }else {
						 me.insert_Piece(tapete.delearOne()); // NO TENDRIA QUE ERSTAR AQUI CAMBIAR YA 
						///////////////////
						showHands(now);
						///////////////////// 
					 }
					
					
				} else if (now.getPlayers().get(i) instanceof random) {
					
					random ia= (random) now.getPlayers().get(i);
					
					tapete.placeRandom(ia.getAction(tapete));
					tapete.updateTable();
					
					 ia.updatePieces();// sin implementar
					
					
					System.out.println("TABLERO ACTUAL");
					showTable(tapete);
					if (now.endGame(ia)) {
						 System.out.println("FIN DEL JUEGO");
						 end=true;
						 break; 
					 }else {
						 ia.insert_Piece(tapete.delearOne());
						 ///////////////////
						 showHands(now);
						///////////////////// 
					 }
				}
			}

		}

	}
	
	
	public static void showTable(board tapete ) {
		ArrayList<String> tablero=  new ArrayList<String>();
		
		for (int i=0;i<4; i++) {
			for (int j=0;j<13; j++) {
				try {
					tablero.add(Piece.getPiece( tapete.getTable().get(i).get(j) [1], tapete.getTable().get(i).get(j) [0]) );
					System.out.print(tablero.get(tablero.size()-1)+" ");
				} catch (Exception e) {
					tablero.add("0-0");
					System.out.print(tablero.get(tablero.size()-1)+" ");
				}
			}
			System.out.println("\n");
		}
		
		
	}
	
	
	public static void showHands(Game now) {
		
		for (int i=0; i<now.getPlayers().size();i++) {
			ArrayList<String> cartas= Piece.getStringPieces(now.getPlayers().get(i).getPieces());
			
			System.out.println("Jugador con id "+now.getPlayers().get(i).getId()+" tiene las cartas "+ cartas);
		}
	}
	
	
	
	
	
}
