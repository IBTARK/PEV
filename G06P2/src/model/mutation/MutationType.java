package model.mutation;

public enum MutationType {
	GENERIC, INVERSIONMUTATION, INSERTIONMUTATION, HEURISTICMUTATION, EXCHANGEMUTATION;
	
	public String toString() {
		switch(this) {
			case GENERIC:
				return "Generic";
			case INVERSIONMUTATION:
				return "Inversion";
			case INSERTIONMUTATION:
				return "Insertion";
			case HEURISTICMUTATION:
				return "Heuristic";
			case EXCHANGEMUTATION:
				return "Exchange";
			default:
				return null;
		}
	}
}
