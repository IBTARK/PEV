package model.selection;

public enum SelectionType {
	MONTECARLO, REMAIN, TOURNAMENT, TRUNCATION;
	
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
			default:
				return null;
		}
	}
}
