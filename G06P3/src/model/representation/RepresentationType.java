package model.representation;

public enum RepresentationType {
	MOWERTREE;
	
	public String toString() {
		switch(this) {
			case MOWERTREE:
				return "MowerTree";
			default:
				return null;
		}
	}
}
