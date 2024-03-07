package model.crossover;

public enum CrossoverType {
	ARITHMETIC, BLXALPHA, SINGLEPOINT, UNIFORM, ORDER;
	
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
			case ORDER:
				return "Order";
			default:
				return null;
		}
	}
}
