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
		
		for(int geneLength : genesLengths) {
			genes.add(new BinaryGene(geneLength));
		}
	}
	
	public BinaryChromosome(BinaryChromosome bc) {
		
		random = new Random();
		
		numGenes = Integer.valueOf(bc.getNumGenes());
		chromosomeLength = Integer.valueOf(bc.getChromosomeLength());
		
		//Copy of the fitness, score and scoreAccumulated
		fitness = Double.valueOf(bc.getFitness());
		score = Double.valueOf(bc.getScore());
		scoreAccumulated = Double.valueOf(bc.getScoreAccumulated());

		//Deep copy of the genesLengths and genesFenotypesFunctions lists
		genesLengths = new ArrayList<Integer>();
		genesFenotypesFunctions = new ArrayList<FenotypeFunction>();
		genes = new ArrayList<Gene>();
		genesFenotypes = new ArrayList<Double>();
		for(int i = 0; i < numGenes; i++) {
			genesLengths.add(Integer.valueOf(bc.getGenesLengths().get(i)));
			genesFenotypesFunctions.add(bc.getGenesFenotypesFunctions().get(i).clone());
			genes.add(new BinaryGene((BinaryGene) bc.getGene(i)));
			genesFenotypes.add(Double.valueOf(bc.getGeneFenotype(i)));
		}
	}

}
