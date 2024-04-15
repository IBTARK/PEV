package model.treeRep.symbols;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class Symbols implements Cloneable{
	protected ArrayList<Symbol> terminals;
	protected ArrayList<Symbol> functions;
	
	protected ArrayList<Symbol> both; //List with all the terminals and the functions
	protected HashMap<Integer, ArrayList<Symbol>> functionsAritiesMap; //Map of the arity of a function to a list of functions
	
	/**
	 * By default the arity of each function is set to 2
	 * 
	 * @param terminals list of terminals symbols
	 * @param functions list of functions symbols
	 */
	public Symbols(ArrayList<String> terminals, ArrayList<String> functions, int numCols, int numRows){
		
		//Terminals (arity 0)
		for(String terminal : terminals) {
			this.terminals.add(new Symbol(terminal, 0, numCols, numRows));
		}
		
		//Functions
		ArrayList<Symbol> funs;
		Symbol s;
		for(int i = 0; i < functions.size(); i++) {
			s = new Symbol(functions.get(i), 2, numCols, numRows);
			this.functions.add(s);
			
			funs = functionsAritiesMap.get(2);
			if(funs == null) {
				funs = new ArrayList<Symbol>();
				funs.add(s);
				functionsAritiesMap.put(2, funs);
			}
			else {
				funs.add(s);
			}
		}
		
		both.addAll(this.terminals);
		both.addAll(this.functions);
		Collections.shuffle(both);
	}
	
	/**
	 * The arity of all the functions is the same
	 * 
	 * @param terminals list of terminals symbols
	 * @param functions list of functions symbols
	 */
	public Symbols(ArrayList<String> terminals, ArrayList<String> functions, int arity, int numCols, int numRows){
		
		//Terminals (arity 0)
		for(String terminal : terminals) {
			this.terminals.add(new Symbol(terminal, 0, numCols, numRows));
		}
		
		//Functions
		ArrayList<Symbol> funs;
		Symbol s;
		for(int i = 0; i < functions.size(); i++) {
			s = new Symbol(functions.get(i), arity, numCols, numRows);
			this.functions.add(s);
			
			funs = functionsAritiesMap.get(arity);
			if(funs == null) {
				funs = new ArrayList<Symbol>();
				funs.add(s);
				functionsAritiesMap.put(arity, funs);
			}
			else {
				funs.add(s);
			}
		}
		
		both.addAll(this.terminals);
		both.addAll(this.functions);
		Collections.shuffle(both);
	}
	
	/**
	 * 
	 * @param terminals list of terminals symbols
	 * @param functions list of functions symbols
	 * @param functionsArities list of the arities of the functions
	 */
	public Symbols(ArrayList<String> terminals, ArrayList<String> functions, ArrayList<Integer> functionsArities, int numCols, int numRows){
		
		//Terminals (arity 0)
		for(String terminal : terminals) {
			this.terminals.add(new Symbol(terminal, 0, numCols, numRows));
		}
		
		//Functions
		ArrayList<Symbol> funs;
		Symbol s;
		for(int i = 0; i < functions.size(); i++) {
			s = new Symbol(functions.get(i), functionsArities.get(i), numCols, numRows);
			this.functions.add(s);
			
			funs = functionsAritiesMap.get(functionsArities.get(i));
			if(funs == null) {
				funs = new ArrayList<Symbol>();
				funs.add(s);
				functionsAritiesMap.put(functionsArities.get(i), funs);
			}
			else {
				funs.add(s);
			}
		}
		
		both.addAll(this.terminals);
		both.addAll(this.functions);
		Collections.shuffle(both);
	}

//**********************************************************************************
// Cloneable
	
	@Override
	public abstract Symbols clone();
	
//**********************************************************************************
//Getters
	
	/**
	 * 
	 * @return list of terminals
	 */
	public ArrayList<Symbol> getTerminals(){
		return terminals;
	}
	
	/**
	 * 
	 * @return list of functions
	 */
	public ArrayList<Symbol> getFunctions(){
		return functions;
	}
	
	/**
	 * 
	 * @return list of functions and terminals
	 */
	public ArrayList<Symbol> getBoth(){
		return both;
	}
	
	/**
	 * 
	 * @return a map from the arity to a list of functions with that arity
	 */
	public HashMap<Integer, ArrayList<Symbol>> getFunctionsAritiesMap() {
		return functionsAritiesMap;
	}
	
//**********************************************************************************
//Setters
	
	/**
	 * Set the terminal symbols
	 * 
	 * @param terminals
	 */
	public void setTerminals(ArrayList<Symbol> terminals) {
		this.terminals = terminals;
	}
	
	/**
	 * Set the function symbols
	 * 
	 * @param functions
	 */
	public void setFunctions(ArrayList<Symbol> functions) {
		this.functions = functions;
	}
	
	/**
	 * 
	 * @param functionsAritiesMap
	 */
	public void setFunctionsAritiesMap(HashMap<Integer, ArrayList<Symbol>> functionsAritiesMap) {
		this.functionsAritiesMap = functionsAritiesMap;
	}
}
