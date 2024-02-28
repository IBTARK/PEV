package model.mutation;

public enum MutationType {
	GENERIC;
	
	public String toString() {
		switch(this) {
			case GENERIC:
				return "Generic";
			default:
				return null;
		}
	}
}
