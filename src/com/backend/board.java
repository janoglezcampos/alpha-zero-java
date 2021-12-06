package psiC;

import java.util.ArrayList;








public class board {

	private   ArrayList < ArrayList<short []>>  table;		// tablero temporal
	private  short [][] matrix;       // Tablero en matriz ordenada 
	private  short [][] rest;       // PIEZAS QUE AUN QUEDAN EN LA BARAJA
	private  ArrayList < ArrayList<short []>>  table2; // la buena, tablero rel visto desde la app
	
	
	public board () {
		table= new ArrayList<>();
		table2= new ArrayList<>();
		matrix= new short[4][13];
		generateMatrix();
		generateBoard();
	}   
	
	public ArrayList < ArrayList<short []>> getTable(){
		
		return table2; 
	}	
	public short [][] getMatrix(){
		
		return matrix; 
	}	
	public short [][] getRest(){
		
		return rest; 
	}
	
	public void setRest(short [] [] cartas) {
		
		this.rest=cartas;
	}
	
	public  int getRestSize() {
		int total=0;
		
		for (int i=0;i<4; i++) {
			for (int j=0;j<13; j++) {
				
				total+=this.rest[i][j];
				if (total!=0) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	public  int getRestSize2() {
		int total=0;
		
		for (int i=0;i<4; i++) {
			for (int j=0;j<13; j++) {
				total+=rest[i][j];
			}
		}
		return total;
	}
	
	public  short []  delearOne() {
		System.out.println("TAMAÑO DE REST antes de repartir: "+getRestSize2());
		short [] e= new short [2];
		boolean ok=false;
		////////////////////////////////////////////////////
		for (int i=0;i<4; i++) {
			for (int j=0;j<13; j++) {
				
				System.out.print(rest[i][j]);
				}
				System.out.print("\n");
			}
		////////////////////////////////////////////////////////////
		while (ok==false) {   /// mejorar haciendo aleatorio donde ya sabemos que hay, menos iteraciones 
		
				short valor = (short)Math.floor(Math.random()*(12-0+1)+0);
				short valor2 = (short)Math.floor(Math.random()*(3-0+1)+0);
				if (rest[valor2] [valor]==1) {
					e[0]=valor2;
					e[1]=valor;
					ok=true; 
					rest[valor2] [valor]=0;
				}
		}
		System.out.println("TAMAÑO DE REST despues de repartir: "+getRestSize2());
			return e;
	}
	public void generateMatrix() {
		
		for (int i=0;i<4; i++) {
			for (int j=0;j<13; j++) {
				
				matrix[i][j]=0;
			}
		}
	}
	
	public void generateBoard () {
		ArrayList<short []> data= new ArrayList<>();
		short [] number= {0,0};
		
		for (int j=0;j<13; j++) {
			data.add(number);
		}
		for (int i=0;i<4; i++) {
			table.add(data);
			table2.add(data);
		}
	}
	
	@SuppressWarnings("unchecked")
	public  void placeRandom(short[][] matrix) {// se parte de la idea de que es posible utilizar todas las piezas sino da error  
		
		ArrayList < ArrayList<short []>>  table_copy = new ArrayList <>();
		ArrayList< ArrayList<String> >used= new ArrayList<>();
		ArrayList< ArrayList<String> >used_grupo= new ArrayList<>();
		
		ArrayList<String> serie= new ArrayList<>();
		String result;
		serie.clear();
		
		
		
		
		for (short i=0; i<4; i++) {
			for (short j=0;j<13;j++) {
				if(matrix[i][j]==1&& j!=12) {
					result= String.valueOf(i+1)+","+String.valueOf(j+1); //cambios +1
					serie.add(result);
					
				}else if (matrix[i][j]==0 && serie.size()>=3|| (j==12&&serie.size()>=3)){ // detectada serie
					 used.add((ArrayList<String>)serie.clone());
					 for(int w=0;w<serie.size();w++) {
						 String[] parts = serie.get(w).split(",");
						 short color= (short) Integer.parseInt((parts[0]));
				    	 short number= (short) Integer.parseInt((parts[1]));
				    	 matrix [color-1][number-1]=0; 
					 }
					 serie.clear();
				}else { // serie no detectada
					serie.clear();
				}
		 
			}// for numeros
		}// for colores
		
		// metidos todas las series encontradas, en matrix solo quedan las piezas que faltal
		serie.clear();
		//System.out.println("Tamaño used ANTES DE MIRAR VERTICALAMENTE: "+used.size());
		for (short i=0; i<4; i++) {
			for (short j=0;j<13;j++) {
				if(matrix[i][j]==1) {
					System.out.println("ENTRA A COMPROBAR VERTICALES");
					result= String.valueOf(i+1)+","+String.valueOf(j+1); // la que se encuentra se mete siempre
					serie.add(result);
					for(int w=i+1;w<4;w++) {// recorremos vertical hacia abajo
						
							result= String.valueOf(w+1)+","+String.valueOf(j+1);
							int contador=0;
							for(int e=0;e<used.size();e++) {// comprobamos cada array
								
								if (used.get(e).contains(result)){
									int position= used.get(e).indexOf(result);
									if(used.get(e).size()>3) {
										if(position==used.get(e).size()-1||position==0) {// comprobamos que no estea en medio
											used.get(e).remove(result);// lo quitamos de serie para grupo
											serie.add(result);
											//System.out.println(result);
											break;
										} 
									}
								} else { contador++; }//no lo contiene
							}// for recorrer Array
							if(contador==used.size()&& matrix[w][j]==1) {          
								serie.add(result);
								matrix[w] [j]=0;
							}
							
					}//for hacia abajo
					// puede haber hacia arriba repetimos//////////////////////////////////////////// 
					for(int w=i-1;w>=0;w--) {// recorremos vertical hacia abajo
						
						result= String.valueOf(w+1)+","+String.valueOf(j+1);
						int contador=0;
						for(int e=0;e<used.size();e++) {// comprobamos cada array
							
							if (used.get(e).contains(result)){
								int position= used.get(e).indexOf(result);
								if(used.get(e).size()>3) {
									if(position==used.get(e).size()-1||position==0) {// comprobamos que no estea en medio
										used.get(e).remove(result);// lo quitamos de serie para grupo
										serie.add(result);
										//System.out.println(result);
										break;
									}
								}
							} else { contador++; }//no lo contiene
						}// for recorrer Array
						if(contador==used.size()&& matrix[w][j]==1) {          
							serie.add(result);
							matrix[w] [j]=0;
						}
						
						
					}//for hacia abajo
				/////////////////////hemos sacado los grupos de una vertical
					used_grupo.add((ArrayList<String>)serie.clone());
					//System.out.println("Tamaño de la serie "+serie.size());
					//System.out.println("CONTENIDO DE serie "+serie.get(0));
				}//POSICION NO ES 1
				serie.clear();
				 
			}//for numbers
		}// for colors
		
		////////////// Tenemos en los arrays las series y grupos definitivos, procedamos a meterlos en el tablero
		System.out.println("Tamaño used despues "+used.size());
		System.out.println("Tamaño used_grupo despues "+used_grupo.size());
		System.out.println("contenido used_grupo primer elemento "+used.get(0)); // ESTAAAA CEROOO MIRAR 
		
		ArrayList< ArrayList<String> >conjunto= new ArrayList<>();
		ArrayList< ArrayList<String> >conjunto_copy= new ArrayList<>();
		conjunto.addAll(used);
		conjunto.addAll(used_grupo);
		conjunto_copy.addAll(conjunto);
		short [] nulo= {0,0};
		ArrayList<short []>  fila= new ArrayList <>();
		int disponibles=13;
		boolean fin=false;
		System.out.println("TAMAÑO CONJUNTO_COPY "+conjunto_copy.size()+" TAMAÑO CONJUNTO "+conjunto.size());
		while(fin!=true) {
			//System.out.println("ME QUEDO EN EL WHILE");
			for (int i=0;i<conjunto.size(); i++) {
				if (conjunto.get(i).size()<=disponibles) {
					//System.out.println("DENTRO DE DISPONIBLES");
					//System.out.println(conjunto.get(i).size());
					for(int r=0; r<conjunto.get(i).size();r++) {
						//System.out.println("FOR");
						short [] piece= new short [2];
						String[] parts = conjunto.get(i).get(r).split(",");
						piece[0]=(short)Integer.parseInt(parts[0]);
						piece[1]=(short)Integer.parseInt(parts[1]);
						fila.add(piece);
						//System.out.println("antes de borrar");
						conjunto_copy.remove(conjunto.get(i));// eliminas un array de copy
					}
					if(conjunto.get(i).size()==disponibles) {
						disponibles=0;
						//table_copy.add(fila);// completada una fila
						//fila.clear();
						break;
					}else if(conjunto.get(i).size()<disponibles) {
						//System.out.println("metemos un nulo");
						fila.add(nulo); // metemos separador entre conjuntos 
						disponibles-=conjunto.get(i).size()+1;
					}
				}                            
			} // recorrridos todos los conjuntos, fila formada
			
			
			if(disponibles==0) {
				table_copy.add((ArrayList<short []>)fila.clone());
				fila.clear();
			}else {
				for(int i=0;i<disponibles;i++) {
					fila.add(nulo);	
					//System.out.print(i);
				}
				table_copy.add((ArrayList<short []>)fila.clone());
				fila.clear();			
			}
			disponibles=13;
			
			if(conjunto_copy.size()==0) { // TODOS ACABADOS
					fin=true;
			}else {
				conjunto.clear();
				conjunto.addAll(conjunto_copy);
			}
			
		}// fin del while
	
		
		//System.out.println("TAMAÑO TABLE_COPY "+table_copy.size());
		if (table_copy.size()!=4) {
			ArrayList<short []>  nulos= new ArrayList <>();
			for(int i=0;i<13;i++) {
				nulos.add(nulo);
			}
			int size=table_copy.size();
			for (int i=0; i<4-size;i++) {
				table_copy.add(nulos);
			}
		}
		//System.out.println("MOSTRAMOS TABLE_COPY");
		//showTable(table_copy);
		table.clear();// ya estaba inicializada a ceros hay que borrar
		table.addAll((ArrayList < ArrayList<short []>>)table_copy.clone());
		//System.out.println("MOSTRAMOS TABLE");
		//showTable(table);

	}
	//ESTE SERÁ EL METODO PARA EL JUGADOR, SIN ACABAR...
	public  void  place ( short [] piece ,short [] position) { // primero la fila 
		//ArrayList < ArrayList<short []>>  table_copy = new ArrayList < ArrayList<short []>>(table2);// TABLE 2 ES LA BUENA 
	    ArrayList < ArrayList<short []>>  table_copy = new ArrayList <>();
		//System.out.println(table2.size());
		//table_copy=(ArrayList<ArrayList<short[]>>) table2.clone();
		//table_copy= table2;
		
		table_copy.addAll(table2); 
		
		if (table_copy.get(position[0]).get(position[1])[0]==0 ) {
			
			ArrayList<short []> data= new ArrayList<>();
			data.addAll(table_copy.get(position[0]));
			data.set(position[1], piece); // mirar si hay que restar OJO
			table_copy.set(position[0], data);
			
			
		} else {
			System.out.println("BAD POSITION");	
		}
		
		table.addAll(table_copy);
		
	}
	
	@SuppressWarnings("unchecked")
	public   void updateTable() {
		
		System.out.println("VAMOS A CHEKEAR LA SIGUIENTE TABLA");
		showTable(table);
		
		if (checkBoard()==1) {
			System.out.println("Checkboard is good");
			table2.clear();
			table2.addAll((ArrayList < ArrayList<short []>>)table.clone());
			updateMatrix();
		} else {
			
			System.out.println("Checkboard is bad");
			//System.out.println(table2.size());
			table.clear();
			
			
		}
	}
	
	public int  checkBoard() {
		ArrayList<short []> found= new ArrayList<>();
		
		for (int j=0; j<4;j++) {
			for (int i=0; i<13;i++) {
				if(table.get(j).get(i)[0]!= 0) {
					
					if(found.size()==0) {
						found.add(table.get(j).get(i));
					}else if (table.get(j).get(i-1)[0]!=0) {
						found.add(table.get(j).get(i));
					}else if (table.get(j).get(i-1)[0]==0) { // aqui acabamos una cadena // HACER IN IF PARA MIRAR SI LLEGA 3 SINO PODEMOS DESCARTAR DE UNA 
						if (found.size()>=3) {
							System.out.println("grupo enviado a check");
							boolean ok = checkGroup(found);
							if (ok==false) {
								return -1;
							}else {found.clear();}
						} else {return -1;}
						found.add(table.get(j).get(i));	
					}
				}
				 
			}// acaba fila
			if (found.size()>=3) {
				System.out.println("grupo enviado a check abajo");
				boolean ok = checkGroup(found);
				if (ok==false) {
					return -1;
				}
			} else if(found.size()!=0){ return -1;}
			found.clear();
		}
		
		return 1;
	}


	private static boolean checkGroup(ArrayList<short []> found) {
		int check=0;
		int check1=0;
		
		System.out.println("grupo");
		
		for(int i=0;i<found.size();i++) {
			
			System.out.print(String.valueOf(found.get(i)[0]) +String.valueOf(found.get(i)[1])+" ");
			
		}
		System.out.println("\n");
		
		
		
		for (int i=0; i<found.size()-2;i++) { 
			if(found.get(i)[1]!=found.get(i+1)[1]-1){// que sean consecutivos
				check++;
			}//else {System.out.println("consecutivos");}
			if(found.get(i)[0]!=found.get(i+1)[0]){ //que sean del mismo color
				check++;
			}//else {System.out.println("mismo color");}
			if(found.get(i)[1]!=found.get(i+1)[1]){// que sean el mismo numero,como no hay repeticiones vale con esto 
				check1++;
			}//else {System.out.println("mismo numero");}
			
			
		}
		if (check==0||check1==0) {
			return true;
		}else {
			System.out.println("aqui");
			return false ; 
		}
	} 
	
	
	public  void updateMatrix() {
		System.out.println("ACTUALIZANDO MATRIX");
		 
		//System.out.println( String.valueOf(table2.get(0).get(0)[0])+ String.valueOf(table2.get(0).get(0)[1]));
		//System.out.println( table2.get(0).get(0)[0]+ table2.get(0).get(0)[0]);
		
		for(int i=0; i<table2.size(); i++) {
			for (int j=0; j<table2.get(i).size();j++) {
				if (table2.get(i).get(j)[0]!=0) {
					matrix [table2.get(i).get(j)[0]-1] [table2.get(i).get(j)[1]-1]=1;
				} //else {
					//matrix [table2.get(i).get(j)[0]] [table2.get(i).get(j)[1]]=0;
				//}
			}
		}
		//System.out.println("posicion"+String.valueOf(matrix[0][0]));

	}
	
	public static void showTable (ArrayList < ArrayList<short []>>  table) {

		ArrayList<String> tablero=  new ArrayList<String>();
		for (int i=0;i<table.size(); i++) {
			for (int j=0;j<table.get(i).size(); j++) {
				try {
					tablero.add(Piece.getPiece( table.get(i).get(j) [1], table.get(i).get(j) [0]) );
					System.out.print(String.valueOf((int)table.get(i).get(j) [1])+String.valueOf( (int)table.get(i).get(j) [0])+" ");
					//System.out.print(tablero.get(tablero.size()-1)+" ");
				} catch (Exception e) {
					tablero.add("0-0");
					System.out.print(tablero.get(tablero.size()-1)+" ");
				}
			}
			System.out.println("\n");
			tablero.clear();
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
