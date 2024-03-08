package model.chromosomes;

public enum ChromosomeType {
	BINARYCHROMOSOME, REALCHROMOSOME, INTEGERCHROMOSOME;
	
	public String toString() {
		switch(this) {
			case BINARYCHROMOSOME:
				return "Binary";
			case REALCHROMOSOME:
				return "Real";
			case INTEGERCHROMOSOME:
				return "Integer";
			default:
				return null;
		}
	}
}
