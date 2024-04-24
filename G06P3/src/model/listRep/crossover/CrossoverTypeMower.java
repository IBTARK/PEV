package model.listRep.crossover;

public enum CrossoverTypeMower {
	ORDER, PMX, ORDERPRIORITARYPOSITIONS, ORDERPRIORITARYORDER, ORDINALCODIFICATION, CYCLE, IJ;
	
	public String toString() {
		switch(this) {
			case ORDER:
				return "Order";
			case PMX:
				return "PMX";
			case ORDERPRIORITARYPOSITIONS:
				return "Order prioritary positions";
			case ORDERPRIORITARYORDER:
				return "Order priority";
			case ORDINALCODIFICATION:
				return "Ordinal codification";
			case CYCLE:
				return "Cycle";
			case IJ:
				return "IJ";
			default:
				return null;
		}
	}
}
