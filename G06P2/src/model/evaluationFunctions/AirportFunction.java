package model.evaluationFunctions;

import java.util.ArrayList;

import model.airport.FlightType;
import model.airport.ProblemType;
import model.chromosomes.AirportChromosome;
import model.chromosomes.Chromosome;
import model.genes.FlightGene;

public class AirportFunction implements EvaluationFunction{

	@Override
	public Double apply(Chromosome c) {
		double fitness = 0;
		AirportChromosome ac = (AirportChromosome)c;
		for(int i = 0; i < c.getChromosomeLength(); i++) {
			FlightGene flight = (FlightGene) c.getGene(i);
			//calculamos el TLA del vuelo a cada pista
			Double TLA = 0.0;
			Double menorTLA = 1000.0;
			int index = 0;
			for(int j = 0; j < ac.getTracks().size(); j++) {
				ArrayList<FlightGene> track = ac.getTracks().get(j);
				Double TLAanterior = 0.0;
				if(!track.isEmpty()) {
					//get the TLA of the last flight
					FlightGene lastflight = track.get(track.size()-1);
					TLAanterior = lastflight.getTLA();
					TLA = Math.max(TLAanterior + getSeparation(lastflight, flight), flight.getTel(j));
				}
				else {
					TLA = Math.max(TLAanterior + 0, flight.getTel(j));
				}
				if(TLA < menorTLA) {
					menorTLA = TLA;
					index = j;
				}
			}
			flight.setTLA(menorTLA);
			//se asigna el vuelo actual a la pista con m�nimo TLA (menor_TLA)
			ac.getTracks().get(index).add(flight);
			
			//search the fewer tel of the flight
			int menorTel = 1000;
			for(int k = 0; k < flight.getTels().size(); k++) {
				if(flight.getTel(k) < menorTel) {
					menorTel = flight.getTel(k);
				}
			}
			
			fitness = fitness + Math.pow((menorTLA - menorTel), 2);
		}
		
		return fitness;
	}
	
	private Double getSeparation(FlightGene anterior, FlightGene actual) {
		FlightType tipeanterior = anterior.getType();
		FlightType tipeactual = actual.getType();
		if(tipeactual == FlightType.W && tipeanterior == FlightType.W) {
			return 1.0;
		}
		else if(tipeactual == FlightType.G && tipeanterior == FlightType.W) {
			return 1.5;
		}
		else if(tipeactual == FlightType.P && tipeanterior == FlightType.W) {
			return 2.0;
		}
		if(tipeactual == FlightType.W && tipeanterior == FlightType.G) {
			return 1.0;
		}
		else if(tipeactual == FlightType.G && tipeanterior == FlightType.G) {
			return 1.5;
		}
		else if(tipeactual == FlightType.P && tipeanterior == FlightType.G) {
			return 1.5;
		}
		if(tipeactual == FlightType.W && tipeanterior == FlightType.P) {
			return 1.0;
		}
		else if(tipeactual == FlightType.G && tipeanterior == FlightType.P) {
			return 1.0;
		}
		else if(tipeactual == FlightType.P && tipeanterior == FlightType.P) {
			return 1.0;
		}
		return 0.0;
	}
}
