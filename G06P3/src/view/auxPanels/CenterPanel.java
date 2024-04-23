package view.auxPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import control.Controller;
import model.GenAlgObserver;
import model.representation.Representation;
import model.treeRep.trees.MowerTree;
import view.graphs.EvolutionGraph;
import view.mower.Garden;

public class CenterPanel extends JPanel implements GenAlgObserver{
	private Controller ctr;
	
	private JPanel graphPanel;
	private EvolutionGraph evolutionGraph;
	private JPanel solPanel;
	private JLabel solLabel;
	
	private JPanel gardenPanel;
	private Garden garden;
	
	private static final int TAMSOLPANEL = 60;
	
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
		graphPanel.setPreferredSize(new Dimension(initialWidth, initialHeight));
		graphPanel.add(evolutionGraph);
		
		//Solution panel
		solPanel = new JPanel();
		solPanel.setLayout(new BoxLayout(solPanel, BoxLayout.Y_AXIS));
		solLabel = createLabel("Evaluación: ");
		solLabel.setAlignmentX(CENTER_ALIGNMENT);
		solPanel.setPreferredSize(new Dimension(width, TAMSOLPANEL));
		solPanel.add(solLabel);
		solPanel.setAlignmentX(CENTER_ALIGNMENT);
		solPanel.add(new JScrollPane(solLabel));
		solPanel.setVisible(false);
		graphPanel.add(solPanel);
	
		//Garden panel
		gardenPanel = new JPanel();
		garden = new Garden(ctr, width, height);
		gardenPanel.add(garden);		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Gráfica", graphPanel);
		tabbedPane.addTab("Jardín", gardenPanel);
		add(tabbedPane);
		
	}
	
	public void newExecution(int generations, int numCols, int numRows) {
		int width = evolutionGraph.getWidth(), height = evolutionGraph.getHeight();
		
		graphPanel.remove(evolutionGraph);
		evolutionGraph.remove();
		evolutionGraph = new EvolutionGraph(ctr, width, height, generations);
		graphPanel.add(evolutionGraph);
		
		//New garden
		garden.remove(); //se elimina el observador del jardín anterior
		gardenPanel.remove(garden);
		garden = new Garden(ctr, initialWidth, initialHeight, numCols, numRows);
		gardenPanel.add(garden);
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
		String linea1 = "La mejor solución es: " + ((MowerTree) c).getFenotype();
		String linea2 = "Fitness: " + c.getFitness();
		String text = "<html>" + linea1 + "<br>" + linea2 + "</html>";
		
		graphPanel.setPreferredSize(new Dimension(initialWidth, initialHeight - TAMSOLPANEL));
		gardenPanel.setPreferredSize(new Dimension(initialWidth, initialHeight - TAMSOLPANEL));
		setPreferredSizeGraph(initialWidth, initialHeight - TAMSOLPANEL);
		
		solLabel.setText(text);
		
		solPanel.setVisible(true);
	}

	@Override
	public void remove() {
		ctr.removeObserver(this);
	}

}
