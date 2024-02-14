package model.genes;

import java.util.Random;

/**
 * Class to represent binary genes
 */
public class BinaryGene extends Gene{

	public BinaryGene(int geneLength) {
		super(geneLength);
	}
	
	/**
	 * Random initialization of the binary gene
	 */
	public void initializeGeneRandom(Random random) {
		for(int i = 0; i < geneLength; i++) {
			alleles.add(random.nextBoolean());
		}
	}
}
