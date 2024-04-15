package model.listRep.mutation;

import java.util.ArrayList;
import java.util.Random;

import model.Mutation;
import model.Representation;
import model.listRep.chromosomes.Chromosome;

public class InversionMutation implements Mutation{

	private Random random;
	
	public InversionMutation() {
		random = new Random();
	}

	@Override
	public void mutate(Representation co) {
		Chromosome c = (Chromosome) co;
		
		//choose 2 points
		int p1 = random.nextInt(0, c.getChromosomeLength()), p2 = random.nextInt(0, c.getChromosomeLength());
		
		//The points have to be different
		while(p2 == p1) {
			p2 = random.nextInt(0, c.getChromosomeLength());
		}
		
		//Ensure that p1 < p2. If not, exchange
		if(p1 > p2) {
			int aux = p2;
			p2 = p1;
			p1 = aux;
		}
		
		//TODO delete
		p1 = 3;
		p2 = 6;
		
		//Section to invert
		ArrayList<Object> alleles = c.getAlleles(p1, p2);
		//invert
		ArrayList<Object> new_alleles = new ArrayList<Object>();
		for(int i = alleles.size()-1; i >= 0; i--) {
			new_alleles.add(alleles.get(i));
		}
		//Replace the section
		c.replaceAlleles(new_alleles, p1, p2);
	}
	
}
