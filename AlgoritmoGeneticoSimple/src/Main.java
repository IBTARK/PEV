import java.util.ArrayList;

import model.GeneticAlgorithm;
import model.chromosomes.Chromosome;
import model.chromosomes.ChromosomeType;
import model.crossover.Crossover;
import model.crossover.SinglePointCrossover;
import model.evaluationFunctions.Michalewicz;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.PrecisionRepresentation;
import model.mutation.GenericMutation;
import model.mutation.Mutation;
import model.selection.MontecarloSelection;
import model.selection.Selection;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> genesLengths = new ArrayList<>();
		genesLengths.add(9);
		genesLengths.add(9);
		genesLengths.add(9);
		genesLengths.add(9);
		genesLengths.add(9);
		
		PrecisionRepresentation rep = new PrecisionRepresentation(0.0, Math.PI, 9);
		PrecisionRepresentation rep1 = new PrecisionRepresentation(0.0, Math.PI, 9);
		PrecisionRepresentation rep2 = new PrecisionRepresentation(0.0, Math.PI, 9);
		PrecisionRepresentation rep3 = new PrecisionRepresentation(0.0, Math.PI, 9);
		PrecisionRepresentation rep4 = new PrecisionRepresentation(0.0, Math.PI, 9);
		ArrayList<FenotypeFunction> genesFenotypesFunctions = new ArrayList<>();
		genesFenotypesFunctions.add(rep);
		genesFenotypesFunctions.add(rep1);
		genesFenotypesFunctions.add(rep2);
		genesFenotypesFunctions.add(rep3);
		genesFenotypesFunctions.add(rep4);
		
		Selection selection = new MontecarloSelection(0.0);
		Crossover crossover = new SinglePointCrossover();
		Mutation mutation = new GenericMutation(0.05);
		
		GeneticAlgorithm alg = new GeneticAlgorithm(ChromosomeType.BINARYCHROMOSOME, 5, genesLengths, genesFenotypesFunctions, 20, 20, 
													selection, crossover, 0.6, mutation, new Michalewicz(5), true);
		
		for(Chromosome c : alg.execute()) {
			System.out.println("x1: " + c.getGeneFenotype(0) + " x2: " + c.getGeneFenotype(1) + " fitness: " + c.getEvaluation());
		}
	}
}
