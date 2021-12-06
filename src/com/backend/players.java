package psiC;


public class players {

private int id;
private short [] [] Pieces= new short [4] [13];
//private short [] [] Pieces_temp= new short [4] [13];

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
	
	
public void insert_Piece(short [] e ) {
		Pieces[e[0]] [e[1]]=1;
	}

public void delete_Piece(short i,short j ) {
	
		Pieces [i] [j]=0;
}

public void delete_Pieces(short [][] mano ) { //por ahora no se utiliza
	for (int i=0;i<4; i++) {
		for (int j=0;j<13; j++) {
			if(mano[i][j]==1) {
				Pieces[i][j]=0;
			}
		}
	}
}
	
		
public void updatePieces() {
	
	
	
}

public int  get_number_of_Pieces(){  // mas que nada para actualizar en la interfaz
	
	int total=0;
	
	for (int i=0;i<4; i++) {
		for (int j=0;j<13; j++) {
			total+=Pieces[i][j];
		}
	}
	return total;
	
}	
	
	
}
