package model.mutation;

public enum MutationType {
	FUNCTIONALMUTATION, TERMINALMUTATION, PERMUTATIONMUTATION, SUBTREEMUTATION;
	
	public String toString() {
		switch(this) {
			case FUNCTIONALMUTATION:
				return "Funcional";
			case TERMINALMUTATION:
				return "Terminal";
			case PERMUTATIONMUTATION:
				return "Permutacion";
			case SUBTREEMUTATION:
				return "Subarbol";
			default:
				return null;
		}
	}
}
