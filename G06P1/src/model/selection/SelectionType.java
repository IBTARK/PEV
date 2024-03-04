package model.selection;

public enum SelectionType {
	MONTECARLO, REMAIN, TOURNAMENT, TRUNCATION, STOCHASTIC;
	
	public String toString() {
		switch(this) {
			case MONTECARLO:
				return "Montecarlo";
			case REMAIN:
				return "Remain";
			case TOURNAMENT:
				return "Tournament";
			case TRUNCATION:
				return "Truncation";
			case STOCHASTIC:
				return "Stochastic";
			default:
				return null;
		}
	}
}
