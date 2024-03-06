package model.fenotypes;

public enum FenotypeType {
	PRECISION, REAL;
	
	public String toString() {
		switch(this) {
			case PRECISION:
				return "Binary";
			case REAL:
				return "Real";
			default:
				return null;
		}
	}
}
