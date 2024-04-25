package model.evaluationFunctions;

public enum EvaluationFunctionType {
	GRAMMAR, MOWER;
	
	public String toString() {
		switch(this) {
			case GRAMMAR:
				return "Grammar";
			case MOWER:
				return "Mower";
			default:
				return null;
		}
	}
}
