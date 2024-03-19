package model.mutation;

public enum MutationType {
	GENERIC, INVERSIONMUTATION, INSERTIONMUTATION, HEURISTICMUTATION, EXCHANGEMUTATION, IJMUTATION;
	
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
			case IJMUTATION:
				return "IJ";
			default:
				return null;
		}
	}
}
