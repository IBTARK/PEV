package model.chromosomes;

public enum ChromosomeType {
	BINARYCHROMOSOME, REALCHROMOSOME, INTEGERCHROMOSOME, AIRPORTCHROMOSOME;
	
	public String toString() {
		switch(this) {
			case BINARYCHROMOSOME:
				return "Binary";
			case REALCHROMOSOME:
				return "Real";
			case INTEGERCHROMOSOME:
				return "Integer";
			case AIRPORTCHROMOSOME:
				return "Airport";
			default:
				return null;
		}
	}
}
