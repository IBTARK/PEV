package model.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.chromosomes.Chromosome;

/**
 * Class to implement the partially mapped crossover
 */
public class PMXCrossover implements Crossover{
	
	private Random random;
	
	public PMXCrossover() {
		random = new Random();
	}
	
	@Override
	/**
	 * PMX crossover (the original chromosomes are modified)
	 */
	public void cross(Chromosome c1, Chromosome c2) {
		//Choose the points
		int p1 = random.nextInt(c1.getChromosomeLength()), p2 = random.nextInt(c1.getChromosomeLength());
		
		//The points have to be different
		while(p2 == p1) {
			p2 = random.nextInt(c1.getChromosomeLength());
		}
		
		//Ensure that p1 < p2. If not, exchange
		if(p1 > p2) {
			int aux = p2;
			p2 = p1;
			p1 = aux;
		}
		p1 = 3;
		p2 = 6;
		
		//Deep copy of the original chromosomes
		Chromosome cp1 = c1.clone(), cp2 = c2.clone();
		//Sections to be copied
		ArrayList<Object> alleles1 = cp1.getAlleles(p1, p2), alleles2 = cp2.getAlleles(p1, p2);
		
		//Exchange the sections
		c1.replaceAlleles(alleles2, p1, p2);
		c2.replaceAlleles(alleles1, p1, p2);
		
		//Add the missing alleles
		int cont1 = (p2 + 1) % c1.getChromosomeLength();
		//copy the alleles that have not been exchanged
		while(cont1 != p1) {
			//son1
			if(!alleles2.contains(cp1.getAllele(cont1))) {
				//copy the allele as the father
				c1.setAllele(cont1, cp1.getAllele(cont1));
			}
			else {
				//numero a buscar su homólogo en parte copiada del otro padre
				//get position of that number
				int index1 = cp2.indexOf(cp1.getAllele(cont1));
				//el nuevo alelo será el que esté en esa pos del padre1 original
				c1.setAllele(cont1, cp1.getAllele(index1));
			}
			//son2
			if(!alleles1.contains(cp2.getAllele(cont1))) {
				//copy the allele as the father
				c2.setAllele(cont1, cp2.getAllele(cont1));
			}
			else {
				//numero a buscar su homólogo en parte copiada del otro padre
				//get position of that number
				int index2 = cp1.indexOf(cp2.getAllele(cont1));
				//el nuevo alelo será el que esté en esa pos del padre1 original
				c2.setAllele(cont1, cp2.getAllele(index2));
			}
			cont1 = (cont1 + 1) % c1.getChromosomeLength();
		}
	}
}
