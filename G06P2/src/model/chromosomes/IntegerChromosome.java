package model.chromosomes;

import java.util.ArrayList;
import java.util.List;

import model.fenotypes.FenotypeFunction;
import model.genes.Gene;
import model.genes.IntegerGene;

/**
 * Class to represent a chromosome with only integer genes
 */
public class IntegerChromosome extends Chromosome{

	public IntegerChromosome(int numGenes, List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions) {
		super(numGenes, genesLengths, genesFenotypesFunctions);
		
		for(int i = 0; i < numGenes; i++) {
			genes.add(new IntegerGene(genesLengths.get(i), genesFenotypesFunctions.get(i)));
		}
	}
	
	@Override
	public IntegerChromosome clone() {
		int cloneNumGenes = Integer.valueOf(numGenes);
		
		//Deep copy of the genesLengths and genesFenotypesFunctions lists
		ArrayList<Integer> cloneGenesLengths = new ArrayList<Integer>();
		ArrayList<FenotypeFunction> cloneGenesFenotypesFunctions = new ArrayList<FenotypeFunction>();
		ArrayList<Gene> cloneGenes = new ArrayList<Gene>();
		for(int i = 0; i < numGenes; i++) {
			cloneGenesLengths.add(Integer.valueOf(genesLengths.get(i)));
			cloneGenesFenotypesFunctions.add(genes.get(i).getFenotypeFunction().clone());
			cloneGenes.add(genes.get(i).clone());
		}
		
		IntegerChromosome clone = new IntegerChromosome(cloneNumGenes, cloneGenesLengths, cloneGenesFenotypesFunctions);
		
		//Set the genes and their fenotypes
		clone.setGenes(cloneGenes);
		
		//Set of the fitness, score and scoreAccumulated
		clone.setEvaluation(Double.valueOf(evaluation));
		clone.setFitness(Double.valueOf(fitness));
		clone.setScore(Double.valueOf(score));
		clone.setScoreAccumulated(Double.valueOf(scoreAccumulated));
		
		return clone;
	}
}
