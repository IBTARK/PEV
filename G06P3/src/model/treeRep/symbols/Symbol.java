package model.treeRep.symbols;

import java.util.Random;

public class Symbol implements Cloneable{
	private Random random;
	private String symbol;
	private int arity;
	
	
	private int numCols;
	private int numRows;
	private int col;
	private int row;
	
	public Symbol(String symbol, int arity, int numCols, int numRows) {
		random = new Random();
		
		this.symbol = symbol;
		this.arity = arity;
		
		this.numCols = numCols;
		this.numRows = numRows;
		col = random.nextInt(0, numCols);
		row = random.nextInt(0, numRows);
	}

	//TODO comentar para ver si hay errores
	public Symbol(String symbol, int arity) {
		random = new Random();
		
		this.symbol = symbol;
		this.arity = arity;
	}
	
	/**
	 * Assign new random values to the column and the row
	 */
	public void repos() {
		col = random.nextInt(0, numCols);
		row = random.nextInt(0, numRows);
	}
	
//**********************************************************************************
//Cloning
	
	public Symbol clone() {
		Symbol newSymbol = new Symbol(symbol, arity);
		newSymbol.setCol(col);
		newSymbol.setRow(row);
		
		return newSymbol;
	}

	
//**********************************************************************************
//Getters
	
	public String getSymbol() {
		return symbol;
	}
	
	public int getArity() {
		return arity;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
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
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
}
