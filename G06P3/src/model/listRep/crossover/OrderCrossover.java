package model.listRep.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.Crossover;
import model.listRep.chromosomes.Chromosome;
import model.representation.Representation;

/**
 * Class to implement the order crossover
 */
public class OrderCrossover implements Crossover{

	private Random random;
	
	public OrderCrossover() {
		random = new Random();
	}
	
	@Override
	/**
	 * Order crossover (the original chromosomes are modified)
	 */
	public void cross(Representation co1, Representation co2) {
		Chromosome c1 = (Chromosome) co1;
		Chromosome c2 = (Chromosome) co2;
		
		int p1 = random.nextInt(c1.getChromosomeLength()), p2 = random.nextInt(c1.getChromosomeLength());
		
		//The points have to be different
		while(p2 == p1) {
			p2 = random.nextInt(c1.getChromosomeLength());
		}
		
		//Ensure that p1 < p2
		if(p1 > p2) {
			int aux = p2;
			p2 = p1;
			p1 = aux;
		}
		
		//Deep copy of the original chromosomes
		Chromosome cp1 = c1.clone(), cp2 = c2.clone();
		//Sections to be copied
		ArrayList<Object> alleles1 = cp1.getAlleles(p1, p2), alleles2 = cp2.getAlleles(p1, p2);
		
		//Exchange the sections
		c1.replaceAlleles(alleles2, p1, p2);
		c2.replaceAlleles(alleles1, p1, p2);
		
		int cont1 = (p2 + 1) % c1.getChromosomeLength(), contAux1 = cont1;
		int cont2 = cont1, contAux2 = cont1;
		while(cont1 != p1 || cont2 != p1) {
			if(cont1 != p1) {
				if(!alleles2.contains(cp1.getAllele(contAux1))) {
					c1.setAllele(cont1, cp1.getAllele(contAux1));
					alleles2.add(cp1.getAllele(contAux1));
					cont1 = (cont1 + 1) % c1.getChromosomeLength();
				}
				contAux1 = (contAux1 + 1) % c1.getChromosomeLength();
			}
			
			if(cont2 != p1) {
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
