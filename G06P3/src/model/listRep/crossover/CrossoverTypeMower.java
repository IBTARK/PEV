package model.listRep.crossover;

public enum CrossoverTypeMower {
	SINGLEPOINT, UNIFORM;
	
	public String toString() {
		switch(this) {
			case SINGLEPOINT:
				return "Single point";
			case UNIFORM:
				return "Uniform";
			default:
				return null;
		}
	}
}
