package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.chromosomes.Chromosome;

public interface GenAlgObserver {
	void onRegister();
	void onFirstGen();
	void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration);
	void onAlgFinished(Chromosome c, int numTracks, HashMap<Integer, ArrayList<String>> flightsInfo);
	void remove();
}
