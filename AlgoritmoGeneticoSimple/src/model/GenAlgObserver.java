package model;

public interface GenAlgObserver {
	void onRegister();
	void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration);
}
