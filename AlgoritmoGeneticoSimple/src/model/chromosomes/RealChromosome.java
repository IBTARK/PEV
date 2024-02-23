package model.chromosomes;

import java.util.ArrayList;
import java.util.List;

import model.fenotypes.FenotypeFunction;
import model.genes.Gene;
import model.genes.RealGene;

/**
 * Class to represent a chromosome with only real genes
 */
public class RealChromosome extends Chromosome{

	public RealChromosome(int numGenes, List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions) {
		super(numGenes, genesLengths, genesFenotypesFunctions);
		
		chromosomeLength = 0;
		for(int geneLength : genesLengths) {
			genes.add(new RealGene(geneLength));
			chromosomeLength += geneLength;
		}
	}

	@Override
	public RealChromosome clone() {
		int cloneNumGenes = Integer.valueOf(numGenes);
		
		//Deep copy of the genesLengths and genesFenotypesFunctions lists
		ArrayList<Integer> cloneGenesLengths = new ArrayList<Integer>();
		ArrayList<FenotypeFunction> cloneGenesFenotypesFunctions = new ArrayList<FenotypeFunction>();
		ArrayList<Gene> cloneGenes = new ArrayList<Gene>();
		ArrayList<Double> cloneGenesFenotypes = new ArrayList<Double>();
		for(int i = 0; i < numGenes; i++) {
			cloneGenesLengths.add(Integer.valueOf(genesLengths.get(i)));
			cloneGenesFenotypesFunctions.add(genesFenotypesFunctions.get(i).clone());
			cloneGenes.add(genes.get(i).clone());
			cloneGenesFenotypes.add(Double.valueOf(genesFenotypes.get(i)));
		}
		
		RealChromosome clone = new RealChromosome(cloneNumGenes, cloneGenesLengths, cloneGenesFenotypesFunctions);
		
		//Set the genes and their fenotypes
		clone.setGenesFenotypes(cloneGenesFenotypes);
		clone.setGenes(cloneGenes);
		
		//Set of the fitness, score and scoreAccumulated
		clone.setFitness(Double.valueOf(fitness));
		clone.setScore(Double.valueOf(score));
		clone.setScoreAccumulated(Double.valueOf(scoreAccumulated));
		
		return clone;
	}
}
