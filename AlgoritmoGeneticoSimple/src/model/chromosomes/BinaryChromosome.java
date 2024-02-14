package model.chromosomes;

import java.util.List;

import model.fenotypes.FenotypeFunction;
import model.genes.BinaryGene;

/**
 * Class to represent a chromosome with only binary genes
 */
public class BinaryChromosome extends Chromosome{

	public BinaryChromosome(int chromosomeLenght, List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions) {
		super(chromosomeLenght, genesFenotypesFunctions);
		
		for(int geneLength : genesLengths) {
			genes.add(new BinaryGene(geneLength));
		}
	}

}
