import java.util.ArrayList;

import model.GeneticAlgorithm;
import model.chromosomes.Chromosome;
import model.chromosomes.ChromosomeType;
import model.crossover.BLXAlphaCrossover;
import model.crossover.Crossover;
import model.evaluationFunctions.Michalewicz;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.RealRepresentation;
import model.mutation.GenericMutation;
import model.mutation.Mutation;
import model.selection.Selection;
import model.selection.TruncationSelection;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> genesLengths = new ArrayList<>();
		genesLengths.add(1);
		
		RealRepresentation rep = new RealRepresentation(0.0, Math.PI);
		ArrayList<FenotypeFunction> genesFenotypesFunctions = new ArrayList<>();
		genesFenotypesFunctions.add(rep);
		
		Selection selection = new TruncationSelection(0.1, 0.5);
		Crossover crossover = new BLXAlphaCrossover(0.5);
		Mutation mutation = new GenericMutation(0.05);
		
		GeneticAlgorithm alg = new GeneticAlgorithm(ChromosomeType.REALCHROMOSOME, 5, genesLengths, genesFenotypesFunctions, 20, 50, 
													selection, crossover, 0.6, mutation, new Michalewicz(5), true);
		
		for(Chromosome c : alg.execute()) {
			System.out.println("x1: " + c.getGeneFenotype(0) + " x2: " + c.getGeneFenotype(1) + " fitness: " + c.getEvaluation());
		}
	}
}
