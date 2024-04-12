package model;

public interface GenAlgObserver {
	void onRegister();
	void onFirstGen();
	void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration);
	void onAlgFinished(Representation c);
	void remove();
}
