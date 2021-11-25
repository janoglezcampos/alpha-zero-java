package psiC;


public class players {

private int id;
private short [] [] Pieces= new short [13] [4];

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public short [] []getPieces() {
	return Pieces;
}

public void setPieces(short [] [] Pieces) {
	this.Pieces = Pieces;
}

	
public players(int id,short [] [] Pieces) {
		this.id=id;
		this.Pieces=Pieces;	
	}
	
	
public void insert_Piece(short i, short j ) {
		Pieces[i] [j]=1;
	}

public void delete_Piece(short i,short j ) {
	
		Pieces [i] [j]=0;
}
	
		

public int  get_number_of_Pieces(){  // mas que nada para actualizar en la interfaz
	
	int total=0;
	
	for (int i=0;i<13; i++) {
		for (int j=0;j<4; j++) {
			total+=Pieces[i][j];
		}
	}
	return total;
	
}	
	
	
}
