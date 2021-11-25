package psiC;

import java.util.ArrayList;

public class play {

	
	public static  void main (String[] args) {
		
		System.out.println("EMPIEZA EL JUEGO");
		Game now = new Game (Integer.parseInt(args[0])); 
		board tapete= new board();
		System.out.println("Numero de jugadores "+args[0]);
		
		for (int i=0; i<now.getPlayers().size();i++) {
			ArrayList<String> cartas=  new ArrayList<String>();
			for (int j=0; j < now.getPlayers().get(i).getPieces().size();j++) {
				
				cartas.add( now.getPlayers().get(i).getPieces().get(j).getNumber()+","+now.getPlayers().get(i).getPieces().get(j).getColor());
			}
			System.out.println("Jugador con id "+now.getPlayers().get(i).getId()+" tiene las cartas "+ cartas);
			
		}
		
		
		
		
		ArrayList<String> tablero=  new ArrayList<String>();
		System.out.println("Tablero inicial"+"\n");
		for (int i=0;i<4; i++) {
			for (int j=0;j<13; j++) {
				try {
					tablero.add(tapete.getTable()[j][i].getNumber()+","+tapete.getTable()[j][i].getColor());
					System.out.print(tablero.get(tablero.size()-1)+" ");
				} catch (Exception e) {
					tablero.add("0-0");
					System.out.print(tablero.get(tablero.size()-1)+" ");
				}
				
			}
			System.out.println("\n");
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
