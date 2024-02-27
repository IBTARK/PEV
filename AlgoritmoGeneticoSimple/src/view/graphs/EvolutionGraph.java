package view.graphs;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import control.Controller;
import model.GenAlgObserver;

public class EvolutionGraph extends JPanel implements GenAlgObserver{
	private Controller ctr;
	
	private double[] absoluteBests;
	private double[] generationBests;
	private double[] generationMean;
	private double[] generations;
	
	private Plot2DPanel plot;
	
	public EvolutionGraph(Controller ctr, int width, int height, int generations) {
		this.ctr = ctr;
		this.absoluteBests = new double[generations];
		this.generationBests = new double[generations];
		this.generationMean = new double[generations];
		this.generations = new double[generations];
		initGUI(width, height);
		ctr.addObserver(this);
	}
	
	public void initGUI(int width, int height) {
		// create your PlotPanel (you can use it as a JPanel)
		plot = new Plot2DPanel();
		
		//Define the legend position
		plot.addLegend("SOUTH");
		
		plot.addLinePlot("Mejor absoluto", generations, absoluteBests);
		plot.addLinePlot("Mejor generación", generations, generationBests);
		plot.addLinePlot("Mejor media generación", generations, generationMean);
		
		plot.setPreferredSize(new Dimension(width, height));
		this.add(plot);
	}
	
	public void setPreferredSizeGraph(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		plot.setPreferredSize(new Dimension(width, height));
	}
	
	public double[] getAbsoluteBests() {
		return absoluteBests;
	}

	public double[] getGenerationBest() {
		return generationBests;
	}

	public double[] getGenerationMean() {
		return generationMean;
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

	public void setGenerationMeann(ArrayList<Integer> generationMean) {
		this.generationMean = new double[generationMean.size()];
		
		for(int i = 0; i < generationMean.size(); i++) {
			this.generationMean[i] = generationMean.get(i);
		}
	}

	public void setGenerations(ArrayList<Integer> generations) {
		this.generations = new double[generations.size()];
		
		for(int i = 0; i < generations.size(); i++) {
			this.generations[i] = generations.get(i);
		}
	}

//*****************************************************************************
//Observer interface 
	
	@Override
	public void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration) {
		absoluteBests[generation] = absoluteBest;
		generationBests[generation] = generationBest;
		generationMean[generation] = meanGeneration;
		generations[generation] = generation;
		
		plot.removeAllPlots();
		
		plot.addLinePlot("Mejor absoluto", generations, absoluteBests);
		plot.addLinePlot("Mejor generación", generations, generationBests);
		plot.addLinePlot("Media generación", generations, generationMean);
		
		plot.repaint();
	}

	@Override
	public void onRegister() {
	}
	
	
}
