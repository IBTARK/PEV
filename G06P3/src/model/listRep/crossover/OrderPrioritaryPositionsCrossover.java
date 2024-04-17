package model.listRep.crossover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Crossover;
import model.listRep.chromosomes.Chromosome;
import model.representation.Representation;

/**
 * Class to implement the order crossover with priority positions
 */
public class OrderPrioritaryPositionsCrossover implements Crossover{
	
	private Random random;
	
	private int numPos; //Number of prioritary positions in the chromosome
	
	public OrderPrioritaryPositionsCrossover(int numPos) {
		random = new Random();
		this.numPos = numPos;
	}
	
	private void checkNumPosValid(Chromosome c) {
		if(numPos > c.getChromosomeLength() || numPos < 0) {
			numPos = random.nextInt(1, c.getChromosomeLength() / 2);
		}
	}

	@Override
	/**
	 * Order crossover with priority positions(the original chromosomes are modified)
	 */
	public void cross(Representation co1, Representation co2) {
		Chromosome c1 = (Chromosome) co1;
		Chromosome c2 = (Chromosome) co2;
		
		checkNumPosValid(c1);
		
		ArrayList<Integer> priorityPositions = new ArrayList<Integer>();
		int pos;
		
		//Select randomly the priority positions ensuring that there are no repeated positions
		for(int i = 0; i < numPos; i++) {
			pos = random.nextInt(c1.getChromosomeLength());
			while(priorityPositions.contains(pos)) {
				pos = random.nextInt(c1.getChromosomeLength());
			}
			priorityPositions.add(pos);
		}
		
		//Deep copy of the original chromosomes
		Chromosome cp1 = c1.clone(), cp2 = c2.clone();
		//Values to be copied
		ArrayList<Object> alleles1 = new ArrayList<Object>(), alleles2 = new ArrayList<Object>();
		
		//Exchange of the priority positions
		for(int pPos : priorityPositions) {
			c1.setAllele(pPos, cp2.getAllele(pPos));
			alleles2.add(cp2.getAllele(pPos));
			
			c2.setAllele(pPos, cp1.getAllele(pPos));
			alleles1.add(cp1.getAllele(pPos));
		}
		
		int pMax = Collections.max(priorityPositions), cont1 = (pMax + 1) % c1.getChromosomeLength(), contAux1 = cont1;
		int cont2 = cont1, contAux2 = cont1;
		while(cont1 != pMax || cont2 != pMax) {
			if(cont1 != pMax) {
				if(priorityPositions.contains(cont1)) {
					cont1 = (cont1 + 1) % c1.getChromosomeLength();
				}
				else {
					if(!alleles2.contains(cp1.getAllele(contAux1))) {
						c1.setAllele(cont1, cp1.getAllele(contAux1));
						alleles2.add(cp1.getAllele(contAux1));
						cont1 = (cont1 + 1) % c1.getChromosomeLength();
					}
					contAux1 = (contAux1 + 1) % c1.getChromosomeLength();
				}
			}
			
			if(cont2 != pMax) {
				if(priorityPositions.contains(cont2)) {
					cont2 = (cont2 + 1) % c1.getChromosomeLength();
				}
				else {
					if(!alleles1.contains(cp2.getAllele(contAux2))) {
						c2.setAllele(cont2, cp2.getAllele(contAux2));
						alleles1.add(cp2.getAllele(contAux2));
						cont2 = (cont2 + 1) % c2.getChromosomeLength();
					}
					contAux2 = (contAux2 + 1) % c2.getChromosomeLength();
				}
			}
		}
	}

}
