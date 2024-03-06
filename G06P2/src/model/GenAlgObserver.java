package model;

import model.chromosomes.Chromosome;

public interface GenAlgObserver {
	void onRegister();
	void onFirstGen();
	void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration);
	void onAlgFinished(Chromosome c);
	void remove();
}
