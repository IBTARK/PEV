package symbols;

import java.util.ArrayList;

public abstract class Symbols {
	protected ArrayList<Symbol> terminals;
	protected ArrayList<Symbol> functions;
	
	/**
	 * By default the arity of each function is set to 2
	 * 
	 * @param terminals list of terminals symbols
	 * @param functions list of functions symbols
	 */
	public Symbols(ArrayList<String> terminals, ArrayList<String> functions){
		
		//Terminals (arity 0)
		for(String terminal : terminals) {
			this.terminals.add(new Symbol(terminal, 0));
		}
		
		//Functions
		for(int i = 0; i < functions.size(); i++) {
			this.functions.add(new Symbol(functions.get(i), 2));
		}
	}
	
	/**
	 * The arity of all the functions is the same
	 * 
	 * @param terminals list of terminals symbols
	 * @param functions list of functions symbols
	 */
	public Symbols(ArrayList<String> terminals, ArrayList<String> functions, int arity){
		
		//Terminals (arity 0)
		for(String terminal : terminals) {
			this.terminals.add(new Symbol(terminal, 0));
		}
		
		//Functions
		for(int i = 0; i < functions.size(); i++) {
			this.functions.add(new Symbol(functions.get(i), arity));
		}
	}
	
	/**
	 * 
	 * @param terminals list of terminals symbols
	 * @param functions list of functions symbols
	 * @param functionsArities list of the arities of the functions
	 */
	public Symbols(ArrayList<String> terminals, ArrayList<String> functions, ArrayList<Integer> functionsArities){
		
		//Terminals (arity 0)
		for(String terminal : terminals) {
			this.terminals.add(new Symbol(terminal, 0));
		}
		
		//Functions
		for(int i = 0; i < functions.size(); i++) {
			this.functions.add(new Symbol(functions.get(i), functionsArities.get(i)));
		}
	}
	
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
}
