package psiC;

import java.util.ArrayList;



public class players {

private int id;
private ArrayList<token>tokens= new ArrayList<>();

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public ArrayList<token> getTokens() {
	return tokens;
}

public void setTokens(ArrayList<token> tokens) {
	this.tokens = tokens;
}

	
public players(int id, ArrayList<token> tokens) {
		
		this.id=id;
		this.tokens=tokens;
	
		
	}
	
	
public void insert_token(token token) {
		tokens.add(token);
	}

public void delete_token(token token) {
	
	for (int i=0;i<tokens.size();i++) {
		
		if (tokens.get(i)==token) {
			tokens.remove(i);
			return;
		}	
	}
	
	return;	
}
	
public ArrayList<token>  find_type(int number, String color) {   // utilizaremos el * para indicar culquier color y -1 para cualquier num
	ArrayList<token> tokens_aux = new ArrayList<>();
	for (int i=0;i<tokens.size();i++) {
		
		if (number==-1) {
			
			if (tokens.get(i).getColor()==color) {
				
				tokens_aux.add(tokens.get(i));
			}	
		}else if(color=="*") {
			
			if (tokens.get(i).getNumber()==number) {
				
				tokens_aux.add(tokens.get(i));
			}	
				
		}else {
			
			if (tokens.get(i).getNumber()==number&& tokens.get(i).getColor()) {
				
				tokens_aux.add(tokens.get(i));
			}	
		}
			
	}
	
	return tokens_aux;
}	
	

public int  get_number_of_tokens(){  // mas que nada para actualizar en la interfaz
	
	return tokens.size();
	
	
}	
	
	
	
	
	
	
	
	
}
