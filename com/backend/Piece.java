package psiC;

import java.util.ArrayList;

public class Piece implements java.lang.Comparable<Piece>{
	private short color;
	private short number;
	private boolean joker;
	
	public String getColor() {
		return COLOR_LIST[this.color];
	}
	
	public short getNumber() {
		return number;
	}
	
	public boolean isJoker() {
		return joker;
	}
	
	public static final String[] COLOR_LIST =  {"AZUL", "ROJO", "NARANJA", "NEGRO"};

	public Piece(short color, short number, boolean joker){
		this.color = color;
		this.number = number;
		this.joker = joker;
	}
	public Piece(boolean canBeJoker){// PARA QUE ESTA FUNCION??
		if(canBeJoker) joker = (5<Math.random()*9) ? true:false;  // será menor apartir de 0,5 incluido.
		if (!joker){
			this.joker = false;
			this.color = (short)(Math.random()*4); // 0.0-0.999999999 es lo que da random 
			this.number = (short) (1 + Math.floor((Math.random() * 13)));	// Math.floor redondeo ala bajo
		}
	}

	public Piece(){  // Generate random piece
		this.joker=false;
		this.color = (short)(Math.random()*4);
		this.number = (short) (1 + Math.floor((Math.random() * 13)));	
	}

	public byte asByte(){
		if (joker) return 48;				//48 = 00110000
		byte bColor = (byte) this.color;
		byte bNumber = (byte) this.number;
		
		return bNumber |= (bColor << 6); //  | es un or la expresion se lee igual que un +=; el<< desplaza bits ala izquierda el numero acordado.
	}										// debería ser un 4 no un 6 

	public String toString(){
		if(this.joker) return "Pieza{Joker}";
		return String.format("Pieza{$1%d,$2%s}",number,COLOR_LIST[this.color]);
	}
	
	
	public static ArrayList<Piece> getPool(){  // SIN COMODINES 
		
		ArrayList<Piece> baraja= new ArrayList<>();
		
		for (short  i=1; i<14; i++) {
			
			for (short j=0;j<4;j++) {
				
				baraja.add(new Piece(j, i, false));
				
			}
			
		}
		
		return baraja;
	}

	@Override
	public int compareTo(Piece o) {
		 if(o.getNumber()>number){
	            return -1;
	        }else if(o.getNumber()==number){
	            return 0;
	        }else{
	            return 1;
	        }
	}	
	
	
	
	
	

}