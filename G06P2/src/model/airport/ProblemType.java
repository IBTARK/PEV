package model.airport;

public enum ProblemType {
	VUELOS12, VUELOS25;
	
	public String toString() {
		switch(this) {
			case VUELOS12:
				return "12";
			case VUELOS25:
				return "25";
			default:
				return null;
		}
	}
}
