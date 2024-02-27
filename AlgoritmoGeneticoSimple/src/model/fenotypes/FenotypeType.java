package model.fenotypes;

public enum FenotypeType {
	PRECISION, REAL;
	
	public String toString() {
		switch(this) {
			case PRECISION:
				return "Binario";
			case REAL:
				return "Real";
			default:
				return null;
		}
	}
}
