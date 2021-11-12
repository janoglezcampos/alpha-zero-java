package backend;


public class Piece{
	private short color;
	private short number;
	private boolean joker;

	public static final String[] COLOR_LIST =  {"AZUL", "ROJO", "NARANJA", "NEGRO"};

	public Piece(short color, short number, boolean joker){
		this.color = color;
		this.number = number;
		this.joker = joker;
	}
	public Piece(boolean canBeJoker){
		if(canBeJoker) joker = (5<Math.random()*9) ? true:false;
		if (!joker){
			this.joker = false;
			this.color = (short)(Math.random()*4);
			this.number = (short) (1 + Math.floor((Math.random() * 13)));	
		}
	}

	public Piece(){
		this.joker=false;
		this.color = (short)(Math.random()*4);
		this.number = (short) (1 + Math.floor((Math.random() * 13)));	
	}

	public byte asByte(){
		if (joker) return 48;				//48 = 00110000
		byte bColor = (byte) this.color;
		byte bNumber = (byte) this.number;
		
		return bNumber |= (bColor << 6);
	}

	public String toString(){
		if(this.joker) return "Pieza{Joker}";
		return String.format("Pieza{$1%d,$2%s}",number,COLOR_LIST[this.color]);
	}

}
