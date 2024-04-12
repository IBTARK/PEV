package model.listRep.selection;

public enum SelectionType {
	MONTECARLO, REMAIN, TOURNAMENT, TRUNCATION, STOCHASTIC, RANKING;
	
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
			case RANKING:
				return "Ranking";
			default:
				return null;
		}
	}
}
