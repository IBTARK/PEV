package model.chromosomes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import model.fenotypes.FenotypeFunction;
import model.genes.FlightGene;
import model.genes.Gene;

public class AirportChromosome extends Chromosome{
	
	ArrayList<ArrayList<FlightGene>> tracks;

	public AirportChromosome(int numPistas, Set<Integer> flights, List<FenotypeFunction> genesFenotypesFunctions) {
		super(flights.size(), 1, genesFenotypesFunctions);
		
		for(Integer numFlight : flights) {
			genes.add(new FlightGene(numFlight, genesFenotypesFunctions.get(numFlight-1)));
		}
		
		tracks = new ArrayList<ArrayList<FlightGene>>();
		for(int i = 0; i < numPistas; i++) {
			ArrayList<FlightGene> l = new ArrayList<FlightGene>();
			tracks.add(l);
		}
	}
	
	public AirportChromosome(ArrayList<ArrayList<FlightGene>> tracks, ArrayList<Gene> flightsGenes, List<FenotypeFunction> genesFenotypesFunctions) {
		super(flightsGenes.size(), 1, genesFenotypesFunctions);
		this.genes = flightsGenes;
		this.tracks = tracks;
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
		//se ponen vac�as las pistas
		ArrayList<ArrayList<FlightGene>> clonetracks = new ArrayList<ArrayList<FlightGene>>();
		for(int i = 0; i < tracks.size(); i++) {
			ArrayList<FlightGene> l = new ArrayList<FlightGene>();
			clonetracks.add(l);
		}
		
		/*for(int i = 0; i < tracks.size(); i++) {
			ArrayList<FlightGene> clonetracki = new ArrayList<FlightGene>();
			for(int j = 0; j < tracks.get(i).size(); j++) {
				clonetracki.add(tracks.get(i).get(j)); //TODO clone the element
			}
			clonetracks.add(clonetracki);
		}*/
		
		AirportChromosome clone = new AirportChromosome(clonetracks, cloneGenes, cloneGenesFenotypesFunctions);
		
		//Set the genes and their fenotypes
		clone.setGenes(cloneGenes);
		
		//Set of the fitness, score and scoreAccumulated
		clone.setEvaluation(Double.valueOf(evaluation));
		clone.setFitness(Double.valueOf(fitness));
		clone.setScore(Double.valueOf(score));
		clone.setScoreAccumulated(Double.valueOf(scoreAccumulated));
		
		return clone;
	}
	
	/**
	 * Method that initializes the genes randomly
	 */
	public void initializeChromosomeRandom() {
		Collections.shuffle(genes); //reordenar aleatoriamente los genes
		//ordenar los genes
		/*ArrayList<Gene> cloneGenes = new ArrayList<Gene>();
		cloneGenes.add(genes.get(7).clone());
		cloneGenes.add(genes.get(8).clone());
		cloneGenes.add(genes.get(11).clone());
		cloneGenes.add(genes.get(5).clone());
		cloneGenes.add(genes.get(3).clone());
		cloneGenes.add(genes.get(10).clone());
		cloneGenes.add(genes.get(4).clone());
		cloneGenes.add(genes.get(9).clone());
		cloneGenes.add(genes.get(2).clone());
		cloneGenes.add(genes.get(1).clone());
		cloneGenes.add(genes.get(6).clone());
		cloneGenes.add(genes.get(0).clone());
		genes = cloneGenes;*/
	}
	
	public ArrayList<ArrayList<FlightGene>> getTracks() {
		return tracks;
	}
	
	public void setTracks(ArrayList<ArrayList<FlightGene>> tracks) {
		this.tracks = tracks;
	}

	public String toString() {
		String s = "[";
		
		for(int i = 0; i < numGenes; i++) {
			s += " " + ((FlightGene) genes.get(i)).getAllele();
		}
		
		s += "]\n";
		s += "----------PISTAS----------\n";
		for(int i = 0; i < tracks.size(); i++) {
			s += "pista " + (i+1) + ": \n";
			ArrayList<FlightGene> track = tracks.get(i);
			for(FlightGene vuelo : track) {
				s += vuelo.getAllele(0) + " " + vuelo.getTLA() + "\n";
			}
		}

		return s;
	}
}
