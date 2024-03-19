package model.fenotypes;

public enum FenotypeType {
	PRECISION, REAL, AIRPORT;
	
	public String toString() {
		switch(this) {
			case PRECISION:
				return "Binary";
			case REAL:
				return "Real";
			case AIRPORT:
				return "Airport";
			default:
				return null;
		}
	}
}
