package model.listRep.fenotypes;

public enum FenotypeType {
	PRECISION, REAL, AIRPORT;
	
	public String toString() {
		switch(this) {
			case AIRPORT:
				return "Airport";
			default:
				return null;
		}
	}
}
