package model.crossover;

public enum CrossoverType {
	TREECROSSOVER;
	
	public String toString() {
		switch(this) {
			case TREECROSSOVER:
				return "Arbol";
			default:
				return null;
		}
	}
}
