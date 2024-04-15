package model.listRep.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.Crossover;
import model.Representation;
import model.listRep.chromosomes.Chromosome;

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
	public void cross(Representation co1, Representation co2) {
		Chromosome c1 = (Chromosome) co1;
		Chromosome c2 = (Chromosome) co2;
		
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
		
		//TODO delete
		//p1 = 3;
		//p2 = 6;
		
		//Deep copy of the original chromosomes
		Chromosome cp1 = c1.clone(), cp2 = c2.clone();
		//Sections to be copied
		ArrayList<Object> alleles1 = cp1.getAlleles(p1, p2), alleles2 = cp2.getAlleles(p1, p2);
		
		//Exchange the sections
		c1.replaceAlleles(alleles2, p1, p2);
		c2.replaceAlleles(alleles1, p1, p2);
		
		//Add the missing alleles
		int cont = (p2 + 1) % c1.getChromosomeLength();
		//copy the alleles that have not been exchanged
		while(cont != p1) {
			//son1
			if(!alleles2.contains(cp1.getAllele(cont))) {
				//copy the allele as the father
				c1.setAllele(cont, cp1.getAllele(cont));
				alleles2.add(cp1.getAllele(cont));
			}
			else {
				//get the number on the required position
				Object al1 = cp1.getAllele(cont);
				//get the index on the other parent
				int index = cp2.indexOf(al1)-1;
				
				boolean found = false;
				while(!found) {
					if(!alleles2.contains(cp1.getAllele(index))) {
						//copy the allele as the father
						c1.setAllele(cont, cp1.getAllele(index));
						alleles2.add(cp1.getAllele(index));
						found = true;
					}
					else {
						//search for other allele
						al1 = cp1.getAllele(index);
						//get the index on the other parent
						index = cp2.indexOf(al1)-1;
					}
				}
				//get the allele of the original parent on index
				//c1.setAllele(cont, cp1.getAllele(index));
			}
			//son2
			if(!alleles1.contains(cp2.getAllele(cont))) {
				//copy the allele as the father
				c2.setAllele(cont, cp2.getAllele(cont));
				alleles1.add(cp2.getAllele(cont));
			}
			else {
				//get the number on the required position
				Object al1 = cp2.getAllele(cont);
				//get the index on the other parent
				int index = cp1.indexOf(al1)-1;
				
				boolean found = false;
				while(!found) {
					if(!alleles1.contains(cp2.getAllele(index))) {
						//copy the allele as the father
						c2.setAllele(cont, cp2.getAllele(index));
						alleles1.add(cp2.getAllele(index));
						found = true;
					}
					else {
						//search for other allele
						al1 = cp2.getAllele(index);
						//get the index on the other parent
						index = cp1.indexOf(al1)-1;
					}
				}
				//get the allele of the original parent on index
				//c2.setAllele(cont, cp2.getAllele(index));
			}
			cont = (cont + 1) % c1.getChromosomeLength();
		}
	}
}
