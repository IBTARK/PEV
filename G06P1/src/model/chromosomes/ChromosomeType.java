package model.chromosomes;

public enum ChromosomeType {
	BINARYCHROMOSOME, REALCHROMOSOME;
	
	public String toString() {
		switch(this) {
			case BINARYCHROMOSOME:
				return "Binary";
			case REALCHROMOSOME:
				return "Real";
			default:
				return null;
		}
	}
}
