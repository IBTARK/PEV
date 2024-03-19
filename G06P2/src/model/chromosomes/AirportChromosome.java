package model.chromosomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.airport.FlightType;
import model.fenotypes.FenotypeFunction;
import model.genes.FlightGene;
import model.genes.Gene;

public class AirportChromosome extends Chromosome{

	public AirportChromosome(HashMap<Integer, ArrayList<String>> flights,  HashMap<Integer, ArrayList<Integer>> tels, List<FenotypeFunction> genesFenotypesFunctions) {
		super(flights.values().size(), 1, genesFenotypesFunctions);
		
		for(Integer numFlight : flights.keySet()) {
			flights.get(numFlight).size();
			genes.add(new FlightGene(numFlight, flights.get(numFlight).get(0), FlightType.valueOf(flights.get(numFlight).get(1)), tels.get(numFlight-1), genesFenotypesFunctions.get(numFlight-1)));
		}
	}
	
	public AirportChromosome(ArrayList<Gene> flightsGenes, List<FenotypeFunction> genesFenotypesFunctions) {
		super(flightsGenes.size(), 1, genesFenotypesFunctions);
		this.genes = flightsGenes;
	}

	@Override
	public Chromosome clone() {
		int cloneNumGenes = Integer.valueOf(numGenes);
		
		//Deep copy of the genesLengths and genesFenotypesFunctions lists
		ArrayList<FenotypeFunction> cloneGenesFenotypesFunctions = new ArrayList<FenotypeFunction>();
		ArrayList<Gene> cloneGenes = new ArrayList<Gene>();
		for(int i = 0; i < numGenes; i++) {
			cloneGenesFenotypesFunctions.add(genes.get(i).getFenotypeFunction().clone());
			cloneGenes.add(genes.get(i).clone());
		}
		
		AirportChromosome clone = new AirportChromosome(cloneGenes, cloneGenesFenotypesFunctions);
		
		//Set the genes and their fenotypes
		clone.setGenes(cloneGenes);
		
		//Set of the fitness, score and scoreAccumulated
		clone.setEvaluation(Double.valueOf(evaluation));
		clone.setFitness(Double.valueOf(fitness));
		clone.setScore(Double.valueOf(score));
		clone.setScoreAccumulated(Double.valueOf(scoreAccumulated));
		
		return clone;
	}

	public String toString() {
		String s = "[";
		
		for(int i = 0; i < numGenes; i++) {
			s += " " + ((FlightGene) genes.get(i)).getAllele();
		}
		
		s += "]";
		
		return s;
	}
}
