package view.graphs;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

public class EvolutionGraph extends JPanel{
	private double[] absoluteBests;
	private double[] generationBests;
	private double[] meanGenerations;
	private double[] generations;
	
	public EvolutionGraph(int width, int height) {
		this.absoluteBests = new double[60];
		this.generationBests = new double[60];
		this.meanGenerations = new double[60];
		this.generations = new double[60];
		for(int i = 0; i < 60; i++) {
			this.absoluteBests[i] = 1;
			this.generationBests[i] = 2;
			this.meanGenerations[i] = 3;
			this.generations[i] = i;
		}
		initGUI(width, height);
	}
	
	public EvolutionGraph(int width, int height, ArrayList<Double> absoluteBests, ArrayList<Double> generationBests, 
			ArrayList<Double> meanGenerations, ArrayList<Double> generations) {
		
		this.absoluteBests = new double[absoluteBests.size()];
		this.generationBests = new double[generationBests.size()];
		this.meanGenerations = new double[meanGenerations.size()];
		this.generations = new double[generations.size()];
		
		for(int i = 0; i < generations.size(); i++) {
			this.absoluteBests[i] = absoluteBests.get(i);
			this.generationBests[i] = generationBests.get(i);
			this.meanGenerations[i] = meanGenerations.get(i);
			this.generations[i] = generations.get(i);
		}
		
		initGUI(width, height);
	}
	
	public void initGUI(int width, int height) {
		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();
		
		//Define the legend position
		plot.addLegend("SOUTH");
		
		
		plot.addLinePlot("Mejor absoluto", generations, absoluteBests);
		plot.addLinePlot("Mejor generación", generations, generationBests);
		plot.addLinePlot("Mejor media generación", generations, meanGenerations);
		
		plot.setPreferredSize(new Dimension(width, height));
		this.add(plot);
	}
	
	public double[] getAbsoluteBests() {
		return absoluteBests;
	}

	public double[] getGenerationBest() {
		return generationBests;
	}

	public double[] getMeanGeneration() {
		return meanGenerations;
	}

	public double[] getGenerations() {
		return generations;
	}

	public void setAbsoluteBest(ArrayList<Integer> absoluteBests) {
		this.absoluteBests = new double[absoluteBests.size()];
		
		for(int i = 0; i < absoluteBests.size(); i++) {
			this.absoluteBests[i] = absoluteBests.get(i);
		}
	}

	public void setGenerationBest(ArrayList<Integer> generationBests) {
		this.generationBests = new double[generationBests.size()];
		
		for(int i = 0; i < generationBests.size(); i++) {
			this.generationBests[i] = generationBests.get(i);
		}
	}

	public void setMeanGeneration(ArrayList<Integer> meanGenerations) {
		this.meanGenerations = new double[meanGenerations.size()];
		
		for(int i = 0; i < meanGenerations.size(); i++) {
			this.meanGenerations[i] = meanGenerations.get(i);
		}
	}

	public void setGenerations(ArrayList<Integer> generations) {
		this.generations = new double[generations.size()];
		
		for(int i = 0; i < generations.size(); i++) {
			this.generations[i] = generations.get(i);
		}
	}
	
	
}
