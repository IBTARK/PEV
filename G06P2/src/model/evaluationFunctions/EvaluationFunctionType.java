package model.evaluationFunctions;

public enum EvaluationFunctionType {
	FUNCION1, HOLDERTABLE, MICHALEWICZ, MISHRABIRD;
	
	public String toString() {
		switch(this) {
			case FUNCION1:
				return "Funcion1";
			case HOLDERTABLE:
				return "Holder Table";
			case MICHALEWICZ:
				return "Michalewicz";
			case MISHRABIRD:
				return "Mishra Bird";
			default:
				return null;
		}
	}
}
