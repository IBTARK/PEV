package model.treeRep.trees;

public enum InitializationType {
	FULL, GROW, RAMPEDANDHALF;
	
	public String toString() {
		switch(this) {
			case FULL:
				return "Completa";
			case GROW:
				return "Creciente";
			case RAMPEDANDHALF:
				return "Ramped and half";
			default:
				return null;
		}
	}
}
