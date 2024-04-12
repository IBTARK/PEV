package model.listRep.mutation;

public enum MutationType {
	INVERSIONMUTATION, INSERTIONMUTATION, HEURISTICMUTATION, EXCHANGEMUTATION, IJMUTATION;
	
	public String toString() {
		switch(this) {
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
