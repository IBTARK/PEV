package model.listRep.chromosomes;

public enum ChromosomeType {
	AIRPORTCHROMOSOME;
	
	public String toString() {
		switch(this) {
			case AIRPORTCHROMOSOME:
				return "Airport";
			default:
				return null;
		}
	}
}
