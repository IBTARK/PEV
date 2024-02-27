package view.auxPanels;

import javax.swing.JPanel;

import control.Controller;
import view.graphs.EvolutionGraph;

public class CenterPanel extends JPanel{
	
	private EvolutionGraph evolutionGraph;
	private Controller ctr;
	
	public CenterPanel(Controller ctr, int width, int height, int generations) {
		this.ctr = ctr;
		initGUI(ctr, width, height, generations);
	}
	
	private void initGUI(Controller ctr, int width, int height, int generations) {
		evolutionGraph = new EvolutionGraph(ctr, width, height, generations);
		this.add(evolutionGraph);
	}
	
	public void newEvolutionGraph(int generations) {
		int width = evolutionGraph.getWidth(), height = evolutionGraph.getHeight();
		
		this.remove(evolutionGraph);
		evolutionGraph = new EvolutionGraph(ctr, width, height, generations);
		this.add(evolutionGraph);
	}
	
	public void setPreferredSizeGraph(int width, int height) {
		evolutionGraph.setPreferredSizeGraph(width, height);
	}
}
