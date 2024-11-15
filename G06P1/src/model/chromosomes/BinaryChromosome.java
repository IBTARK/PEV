package model.chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.fenotypes.FenotypeFunction;
import model.genes.BinaryGene;
import model.genes.Gene;

/**
 * Class to represent a chromosome with only binary genes
 */
public class BinaryChromosome extends Chromosome{

	public BinaryChromosome(int numGenes, List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions) {
		super(numGenes, genesLengths, genesFenotypesFunctions);
		
		for(int i = 0; i < numGenes; i++) {
			genes.add(new BinaryGene(genesLengths.get(i), genesFenotypesFunctions.get(i)));
		}
	}

	@Override
	public BinaryChromosome clone() {
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
		
		BinaryChromosome clone = new BinaryChromosome(cloneNumGenes, cloneGenesLengths, cloneGenesFenotypesFunctions);
		
		//Set the genes and their fenotypes
		clone.setGenes(cloneGenes);
		
		//Set the evaluation, fitness, score and scoreAccumulated
		clone.setEvaluation(Double.valueOf(evaluation));
		clone.setFitness(Double.valueOf(fitness));
		clone.setScore(Double.valueOf(score));
		clone.setScoreAccumulated(Double.valueOf(scoreAccumulated));
		
		return clone;
	}

}
