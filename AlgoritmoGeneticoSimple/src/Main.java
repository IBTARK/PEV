import java.util.ArrayList;

import model.GeneticAlgorithm;
import model.chromosomes.Chromosome;
import model.chromosomes.ChromosomeType;
import model.crossover.CrossoverType;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.PrecisionRepresentation;
import model.fitnessFunctions.MishraBird;
import model.mutation.MutationType;
import model.selection.SelectionType;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> genesLengths = new ArrayList<>();
		genesLengths.add(7);
		genesLengths.add(7);
		
		PrecisionRepresentation rep = new PrecisionRepresentation(-10.0, 0.0, 7);
		PrecisionRepresentation rep1 = new PrecisionRepresentation(-6.5, 0.0, 7);
		ArrayList<FenotypeFunction> genesFenotypesFunctions = new ArrayList<>();
		genesFenotypesFunctions.add(rep);
		genesFenotypesFunctions.add(rep1);
		
		GeneticAlgorithm alg = new GeneticAlgorithm(ChromosomeType.BINARYCHROMOSOME, 2, genesLengths, genesFenotypesFunctions, 400, 2000, 
													SelectionType.MONTECARLO, CrossoverType.SINGLEPOINT, 0.6, MutationType.GENERIC, 0.05, new MishraBird());
		
		for(Chromosome c : alg.execute()) {
			System.out.println("x1: " + c.getGeneFenotype(0) + " x2: " + c.getGeneFenotype(1) + " fitness: " + c.getFitness());
		}
	}
}
