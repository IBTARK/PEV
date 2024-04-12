package model.listRep.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.listRep.chromosomes.Chromosome;

/**
 * Class to implement the order crossover with priority order
 */
public class OrderPriorityOrderCrossover implements Crossover{
	
	private Random random;
	
	private int numPos; //Number of prioritary positions in the chromosome
	
	public OrderPriorityOrderCrossover(int numPos) {
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
	 * Order crossover with priority order(the original chromosomes are modified)
	 */
	public void cross(Chromosome c1, Chromosome c2) {
		checkNumPosValid(c1);
		
		ArrayList<Integer> positions = new ArrayList<Integer>();
		int pos;
		
		//Select randomly the priority positions ensuring that there are no repeated positions
		for(int i = 0; i < numPos; i++) {
			pos = random.nextInt(c1.getChromosomeLength());
			while(positions.contains(pos)) {
				pos = random.nextInt(c1.getChromosomeLength());
			}
			positions.add(pos);
		}
		
		//TODO eliminar
		/*positions = new ArrayList<Integer>();
		positions.add(2);
		positions.add(3);
		positions.add(5);
		positions.add(8);
		*/
		
		//Deep copy of the original chromosomes
		Chromosome cp1 = c1.clone(), cp2 = c2.clone();
		
		ArrayList<Object> allelesInPosC1 = new ArrayList<Object>(), allelesInPosC2 = new ArrayList<Object>();
		ArrayList<Integer> posInC2 = new ArrayList<Integer>(), posInC1 = new ArrayList<Integer>();
		for(int i = 0; i < numPos; i++) {
			allelesInPosC1.add(c1.getAllele(positions.get(i)));
			allelesInPosC2.add(c2.getAllele(positions.get(i)));
			posInC2.add(c2.indexOf(c1.getAllele(positions.get(i)))-1);
			posInC1.add(c1.indexOf(c2.getAllele(positions.get(i)))-1);
		}
		
		int pos1 = 0, pos2 = 0;
		for(int i = 0; i < c1.getChromosomeLength(); i++) {
			if(!posInC2.contains(i)) {
				c1.setAllele(i, cp2.getAllele(i));
			}
			else {
				c1.setAllele(i, allelesInPosC1.get(pos1));
				pos1++;
			}
			
			if(!posInC1.contains(i)) {
				c2.setAllele(i, cp1.getAllele(i));
			}
			else {
				c2.setAllele(i, allelesInPosC2.get(pos2));
				pos2++;
			}
		}
		System.out.println("");
	}

}
