package model.evaluationFunctions;

public enum EvaluationFunctionType {
	AIRPORT;
	
	public String toString() {
		switch(this) {
			case AIRPORT:
				return "Airport";
			default:
				return null;
		}
	}
}
