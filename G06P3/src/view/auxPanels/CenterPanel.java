package view.auxPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Controller;
import model.GenAlgObserver;
import model.representation.Representation;
import model.treeRep.trees.MowerTree;
import view.graphs.EvolutionGraph;
import view.mower.Garden;

public class CenterPanel extends JPanel implements GenAlgObserver{
	
	private EvolutionGraph evolutionGraph;
	//private Garden garden;
	private Controller ctr;
	
	private JPanel graphPanel;
	private JPanel solPanel;
	//private JPanel tablesPanel;
	
	private JLabel solLabel;
	
	private static final int TAMSOLPANEL = 30;
	
	private int initialWidth;
	private int initialHeight;
	
	public CenterPanel(Controller ctr, int width, int height, int generations) {
		this.ctr = ctr;
		initialWidth = width;
		initialHeight = height;
		
		initGUI(ctr, width, height, generations);
		ctr.addObserver(this);
	}
	
	private void initGUI(Controller ctr, int width, int height, int generations) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Graph panel
		graphPanel = new JPanel();
		evolutionGraph = new EvolutionGraph(ctr, width, height, generations);
		//garden = new Garden(ctr, width, height);
		graphPanel.setPreferredSize(new Dimension(initialWidth, initialHeight));
		graphPanel.add(evolutionGraph);
		//graphPanel.add(garden);
		add(graphPanel);
		
		//Solution panel
		solPanel = new JPanel();
		solPanel.setLayout(new BoxLayout(solPanel, BoxLayout.Y_AXIS));
		solLabel = createLabel("Evaluación: ");
		solLabel.setAlignmentX(CENTER_ALIGNMENT);
		solPanel.setPreferredSize(new Dimension(width, TAMSOLPANEL));
		solPanel.add(solLabel);
		solPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		
		solPanel.setVisible(false);
		add(solPanel);
		
	}
	
	public void newEvolutionGraph(int generations) {
		int width = evolutionGraph.getWidth(), height = evolutionGraph.getHeight();
		
		graphPanel.remove(evolutionGraph);
		evolutionGraph.remove();
		evolutionGraph = new EvolutionGraph(ctr, width, height, generations);
		graphPanel.add(evolutionGraph);
	}
	
	public void setPreferredSizeGraph(int width, int height) {
		evolutionGraph.setPreferredSizeGraph(width, height);
	}
	
	/**
	 * creates a label
	 * @param text text that will be displayed in the label
	 * @return the label
	 */
	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font(Font.SERIF, Font.BOLD, 14));
		//label.setForeground(Color.WHITE);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		return label;
	}

//*********************************************************************************
//Observer interface
	
	@Override
	public void onRegister() {
		
	}
	
	@Override
	public void onFirstGen() {
		graphPanel.setPreferredSize(new Dimension(initialWidth, initialHeight));
		setPreferredSizeGraph(initialWidth, initialHeight);
		
		solPanel.setVisible(false);
	}

	@Override
	public void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration) {
		
	}

	@Override
	public void onAlgFinished(Representation c) {
		String text = "La mejor solución es: " + ((MowerTree) c).getFenotype();
		
		graphPanel.setPreferredSize(new Dimension(initialWidth, initialHeight - TAMSOLPANEL));
		setPreferredSizeGraph(initialWidth, initialHeight - TAMSOLPANEL);
		
		solLabel.setText(text);
		
		solPanel.setVisible(true);
	}

	@Override
	public void remove() {
		ctr.removeObserver(this);
	}

}
