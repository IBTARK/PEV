package model.fenotypes;

import model.listRep.chromosomes.MowerChromosome;

public class MowerFenotypeFunction extends FenotypeFunction<MowerChromosome>{

	/* GRAMÁTICA
	 * <start> ::= <op>
	 * <op> ::= (progn2 <op> <op>) | (suma <op> <op>) | (salta <op>)
	 * <op> ::= (avanza) | (izquierda) | cte
	 */
	
	public MowerFenotypeFunction() {
		
	}

	@Override
	public void apply(MowerChromosome c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FenotypeFunction<MowerChromosome> clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
