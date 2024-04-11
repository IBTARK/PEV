package symbols;

public class Symbol {
	private String symbol;
	private int arity;
	
	public Symbol(String symbol, int arity) {
		this.symbol = symbol;
		this.arity = arity;
	}
	
//**********************************************************************************
//Getters
	
	public String getSymbol() {
		return symbol;
	}
	
	public int gerArity() {
		return arity;
	}

//**********************************************************************************
//Setters
	
	/**
	 * Set the symbol
	 * 
	 * @param symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Set the arity
	 * 
	 * @param arity
	 */
	public void setArity(int arity) {
		this.arity = arity;
	}
}
