package psiC;

import java.util.ArrayList;



public class players {

private int id;
private ArrayList<Piece>Pieces= new ArrayList<>();

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public ArrayList<Piece> getPieces() {
	return Pieces;
}

public void setPieces(ArrayList<Piece> Pieces) {
	this.Pieces = Pieces;
}

	
public players(int id, ArrayList<Piece> Pieces) {
		
		this.id=id;
		this.Pieces=Pieces;
	
		
	}
	
	
public void insert_Piece(Piece Piece) {
		Pieces.add(Piece);
	}

public void delete_Piece(Piece Piece) {
	
	for (int i=0;i<Pieces.size();i++) {
		
		if (Pieces.get(i)==Piece) {
			Pieces.remove(i);
			return;
		}	
	}
	
	return;	
}
	
public ArrayList<Piece>  find_type(int number, String color) {   // utilizaremos el * para indicar culquier color y -1 para cualquier num
	ArrayList<Piece> Pieces_aux = new ArrayList<>();
	for (int i=0;i<Pieces.size();i++) {
		
		if (number==-1) {
			
			if (Pieces.get(i).getColor()==color) {
				
				Pieces_aux.add(Pieces.get(i));
			}	
		}else if(color=="*") {
			
			if (Pieces.get(i).getNumber()==number) {
				
				Pieces_aux.add(Pieces.get(i));
			}	
				
		}else {
			
			if (Pieces.get(i).getNumber()==number &&Pieces.get(i).getColor()==color) {
				
				Pieces_aux.add(Pieces.get(i));
			}	
		}
			
	}
	
	return Pieces_aux;
}	
	

public int  get_number_of_Pieces(){  // mas que nada para actualizar en la interfaz
	
	return Pieces.size();
	
	
}	
	
	
	
	
	
	
	
	
}
