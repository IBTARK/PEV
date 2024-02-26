package view.auxPanels;

import javax.swing.JPanel;

import view.graphs.EvolutionGraph;

public class CenterPanel extends JPanel{
	
	private EvolutionGraph evolutionGraph;
	
	public CenterPanel(int width, int height) {
		initGUI(width, height);
	}
	
	private void initGUI(int width, int height) {
		evolutionGraph = new EvolutionGraph(width, height);
		
		this.add(evolutionGraph);
	}
}
