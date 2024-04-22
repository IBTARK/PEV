package model.representation;

public enum RepresentationType {
	MOWERTREE, GRAMMAR;
	
	public String toString() {
		switch(this) {
			case MOWERTREE:
				return "MowerTree";
			case GRAMMAR:
				return "Grammar";
			default:
				return null;
		}
	}
}
