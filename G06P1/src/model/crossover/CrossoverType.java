package model.crossover;

public enum CrossoverType {
	ARITHMETIC, BLXALPHA, SINGLEPOINT, UNIFORM;
	
	public String toString() {
		switch(this) {
			case ARITHMETIC:
				return "Arithmetic";
			case BLXALPHA:
				return "BLXAlpha";
			case SINGLEPOINT:
				return "Single point";
			case UNIFORM:
				return "Uniform";
			default:
				return null;
		}
	}
}
