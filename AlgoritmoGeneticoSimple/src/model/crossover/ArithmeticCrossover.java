package model.crossover;

import model.chromosomes.Chromosome;
import model.chromosomes.RealChromosome;

public class ArithmeticCrossover implements Crossover{
	
	private Double alpha;
	
	public ArithmeticCrossover(Double alpha) {
		if(alpha < 0 || alpha > 1)
			this.alpha = 0.6;
		else
			this.alpha = alpha;
	}

	/**
	 * 
	 * @return alpha
	 */
	public Double getAlpha() {
		return alpha;
	}
	
	/**
	 * Set alpha (if the value is less than 0 or greater than 1 is set to 0.6).
	 * 
	 * @param alpha 
	 */
	public void setAlpha(Double alpha) {
		if(alpha < 0 || alpha > 1)
			this.alpha = 0.6;
		else
			this.alpha = alpha;
	}
	
	@Override
	public void cross(Chromosome c1, Chromosome c2) {
		//TODO revisar
		if(c1.getClass() == RealChromosome.class || c2.getClass() == RealChromosome.class) {
			Chromosome cp1 = c1.clone();
			Chromosome cp2 = c2.clone();
			
			for(int i = 0; i < c1.getChromosomeLength(); i++) {
				c1.setAllele(i, alpha * (double) cp1.getAllele(i) + (1- alpha) * (double) cp2.getAllele(i));
				c2.setAllele(i, alpha * (double) cp2.getAllele(i) + (1- alpha) * (double) cp1.getAllele(i));
			}
		}
	}
}
