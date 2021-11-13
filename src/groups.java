package psiC;

import java.util.ArrayList;
import java.util.Collections;


public class groups {

	
public ArrayList<ArrayList<Piece>> check_series(ArrayList<Piece> chips, int start) {
	    //------Variables
	    ArrayList<ArrayList<Piece>> total_results = new ArrayList<>();
	    ArrayList<Piece> sub_results = new ArrayList<>();
	    ArrayList<Piece> mycards = new ArrayList<>();
	    mycards=chips;
	    Collections.sort(mycards); // Necesario para como está diseñado
	   //-------Variables
	    
	    for (int i=start; i<mycards.size();i++) { 
	    	sub_results.add(mycards.get(i));
	    	
	    	for (int j=start+1;j<mycards.size();j++) {
	    		//Here code is included to use jockers. PENSAR COMO METER LOS JOKERS SIN JODER LA SERIE, COMO ESTÁ AHORA INTERRUMPIRÍA LA SERIE
	            if ((mycards.get(j).getNumber() == sub_results.get(sub_results.size()).getNumber() + 1 || mycards.get(j).isJoker()) && 
	            		(mycards.get(i).getColor() == mycards.get(j).getColor() || mycards.get(j).isJoker())) {
	            
	            			sub_results.add(mycards.get(j));  
	            }
	    	}
	            if (sub_results.size()>=3) {
	        	
	            	for (Piece item: sub_results) {
	            		mycards.remove(item);
	            	}  
	            	total_results.add(sub_results);  // Creo que debería ser así enmede de un array y hacer un addAll()
	            	sub_results.removeAll(sub_results);  // DARÁ ERROR ???
	            
	    	    }else {
	    		 sub_results.removeAll(sub_results);
	    	    } 
	            
	    }// fin del 1 for 
	    ArrayList<ArrayList<Piece>> final_result= new ArrayList<>();
	    final_result.addAll(total_results);
	    final_result.add(mycards); //HACIENDO ESTO SABEMOS QUE EL ULTIMO ARRAY DE FINAL_RESULT SERÁN LAS SOBRANTES EL RESTO LAS SERIES 
	    return final_result;
}




public ArrayList<ArrayList<Piece>> check_groups (ArrayList<Piece> chips) {
	  //------Variables
    ArrayList<ArrayList<Piece>> total_results = new ArrayList<>();
    ArrayList<Piece> sub_results = new ArrayList<>();
    ArrayList<Piece> mycards = new ArrayList<>();
    mycards=chips;
    Collections.sort(mycards); // Necesario para como está diseñado
   //-------Variables
	
	
    for (int i=0; i<mycards.size();i++) { 
    	sub_results.add(mycards.get(i));
    	
    	for (int j=0;j<mycards.size();j++) {//Podemos ahorrarnos una iteración con j=i+1
    		//Here code is included to use jockers. PENSAR COMO METER LOS JOKERS SIN JODER LA SERIE, COMO ESTÁ AHORA INTERRUMPIRÍA LA SERIE
            if ((mycards.get(j).getNumber() == sub_results.get(sub_results.size()).getNumber() || mycards.get(j).isJoker()) && 
            		(mycards.get(i).getColor() != mycards.get(j).getColor() || mycards.get(j).isJoker())) {
            
            			sub_results.add(mycards.get(j)); 
            }
    	}
            if (sub_results.size()>=3) {
        	
            	for (Piece item: sub_results) {
            		mycards.remove(item);
            	}  
            	total_results.add(sub_results);  // Creo que debería ser así enmede de un array y hacer un addAll()
            	sub_results.removeAll(sub_results);  // DARÁ ERROR ???
            
    	    }else {
    		 sub_results.removeAll(sub_results);
    	    } 
            
    }// fin del 1 for 
    ArrayList<ArrayList<Piece>> final_result= new ArrayList<>();
    final_result.addAll(total_results);
    final_result.add(mycards); //HACIENDO ESTO SABEMOS QUE EL ULTIMO ARRAY DE FINAL_RESULT SERÁN LAS SOBRANTES EL RESTO LAS SERIES 
    return final_result;
	
}

public ArrayList<Piece> check_one_group(ArrayList<Piece> chips,int start) { 
	
	ArrayList<Piece> total_results= new ArrayList<>();
	total_results.add(chips.get(start)); 
	
	for (int j=0;j<chips.size();j++) {
		//Here code is included to use jockers. PENSAR COMO METER LOS JOKERS SIN JODER LA SERIE, COMO ESTÁ AHORA INTERRUMPIRÍA LA SERIE
        if ((chips.get(j).getNumber() == total_results.get(total_results.size()).getNumber() || chips.get(j).isJoker()) && 
        		(chips.get(j).getColor() != total_results.get(total_results.size()).getColor() || chips.get(j).isJoker())) {
        
        			total_results.add(chips.get(j)); 
        }
	}
        if (total_results.size()>=3) {
        	return total_results;
        
	    }else {
		 return null;  // ok?
	    } 
	
}


public ArrayList<Piece> check_one_serie (ArrayList<Piece> chips,int start){
	
	ArrayList<Piece> total_results= new ArrayList<>();
	total_results.add(chips.get(start)); 
	
	for (int j=0;j<chips.size();j++) {
		//Here code is included to use jockers. PENSAR COMO METER LOS JOKERS SIN JODER LA SERIE, COMO ESTÁ AHORA INTERRUMPIRÍA LA SERIE
        if ((chips.get(j).getNumber() == total_results.get(total_results.size()).getNumber() +1 || chips.get(j).isJoker()) && 
        		(chips.get(j).getColor() == total_results.get(total_results.size()).getColor() || chips.get(j).isJoker())) {
        
        			total_results.add(chips.get(j)); 
        }
	}
        if (total_results.size()>=3) {
        	return total_results;
        
	    }else {
		 return null;  // ok?
	    } 

}

public int generaterandom(int S){
	
	int valorEntero = (int) Math.floor(Math.random()*((S-1)-(0)+1)+(0));
	return valorEntero;
}


public Piece random_trials(int trials, ArrayList<Piece> chips) { 
	
	//-------Variables
    ArrayList<ArrayList<Piece>> all_resuts= new ArrayList<>();
    ArrayList<Piece> mycards = chips;
    ArrayList<Piece>temp_cards = mycards;
    ArrayList<ArrayList<Piece>>results = new ArrayList<>();
    //int points = 0;
    //-------Variables
	
    for(int i=0;i<trials;i++){   // CODIGO MUERTO?? PORQUE EN PYTHON ES ASI??
    	
    	for (int j=0;j<trials;j++) {
    		
    		ArrayList<Piece> out_put = check_one_group(temp_cards, generaterandom(temp_cards.size()));
    		if (out_put!=null) {
    			results.add(out_put);
    			for (Piece item:out_put) {
    				temp_cards.remove(item);
    			}
    		}
    		out_put = check_one_serie(temp_cards, generaterandom(temp_cards.size()));
    		if (out_put!=null) {
    			results.add(out_put);
    			for (Piece item:out_put) {
    				temp_cards.remove(item);
    			}
    		}	
    	}// FIN 2 FOR 
    	
    	/*
    	for(Piece item:temp_cards) {
    		points+= item.getNumber();
    	}
    	results.add(points);                 --------> ESTO LO QUE PONIA EN PYTHON, PARA QUE LO HACE ???
    	points=0;
    	
    	*/
    	
    	if (!all_resuts.contains(results)) {
    		all_resuts.addAll(results);
    		results.removeAll(results);
    		temp_cards=mycards;
    	}
    	return all_resuts.get(all_resuts.size()).get(all_resuts.get(all_resuts.size()).size());  // En python tambn tambn devuelve chips para que si están en la clase ya ??
    }//FIN 1 FOR 																				//Porque devuelve solo una pieza????
    
    return null;
}

public void lets_play_a(ArrayList<Piece>chips) {
	
	
	
	
	
	
	
}













	
}
