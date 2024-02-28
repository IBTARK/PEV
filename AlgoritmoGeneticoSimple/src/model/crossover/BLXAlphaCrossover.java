package model.crossover;

import java.util.Random;

import model.chromosomes.Chromosome;
import model.chromosomes.RealChromosome;

public class BLXAlphaCrossover implements Crossover{
	private Double alpha;
	private Random random;
	
	public BLXAlphaCrossover(Double alpha) {
		if(alpha < 0 || alpha > 1)
			this.alpha = 0.6;
		else
			this.alpha = alpha;
		
		random = new Random();
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
		if(c1.getClass() == RealChromosome.class && c2.getClass() == RealChromosome.class) {
			Double cmax, cmin, I;
			
			for(int i = 0; i < c1.getChromosomeLength(); i++) {
				cmax = Math.max((double)c1.getAllele(i), (double)c2.getAllele(i));
				cmin = Math.min((double)c1.getAllele(i), (double)c2.getAllele(i));
				I = cmax - cmin;
				
				if (I == 0) {//Random needs the origin to be different from the bound
					c1.setAllele(i, cmin);
					c2.setAllele(i, cmin);
				}
				else {
					c1.setAllele(i, random.nextDouble(cmin - I * alpha, cmax + I * alpha));
					c2.setAllele(i, random.nextDouble(cmin - I * alpha, cmax + I * alpha));
				}
			}
		}
		
	}

}
