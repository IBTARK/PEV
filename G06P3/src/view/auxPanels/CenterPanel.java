package view.auxPanels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import control.Controller;
import model.GenAlgObserver;
import model.representation.Representation;
import model.treeRep.trees.MowerTree;
import view.graphs.EvolutionGraph;
import view.mower.Garden;

public class CenterPanel extends JPanel implements GenAlgObserver{
	private Controller ctr;
	
	private JPanel mainCenterPanel;
	
	//Card layout
	private CardLayout cardLayout;
	public static final String graphPanelId = "Panel to display the resutl";
	public static final String gardenPanelId = "Panel to display the garden";
	
	private JPanel graphPanel;
	private EvolutionGraph evolutionGraph;
	private JPanel solPanel;
	private JLabel solLabel;
	
	private JPanel gardenExteriorPanel;
	private Garden gardenPanel;
	
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
		
		cardLayout = new CardLayout();
		mainCenterPanel = new JPanel(cardLayout);
		
		//Graph panel
		graphPanel = new JPanel();
		evolutionGraph = new EvolutionGraph(ctr, width, height, generations);
		graphPanel.setPreferredSize(new Dimension(initialWidth, initialHeight));
		graphPanel.add(evolutionGraph);
	
		gardenExteriorPanel = new JPanel(new BorderLayout());
		
		//Garden panel
		JLabel titleLabel = new JLabel("Garden");
		titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gardenExteriorPanel.add(titleLabel, BorderLayout.NORTH);
		
		gardenPanel = new Garden(ctr, width / 2, height / 2);
		gardenPanel.setPreferredSize(new Dimension(initialWidth / 2, initialHeight / 2));
		gardenExteriorPanel.add(gardenPanel, BorderLayout.CENTER);
		
		mainCenterPanel.add(graphPanel, graphPanelId);
		mainCenterPanel.add(gardenExteriorPanel, gardenPanelId);
		add(mainCenterPanel);
		
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
		add(solPanel);
		
	}
	
	public void newExecution(int generations, int numCols, int numRows) {
		int width = evolutionGraph.getWidth(), height = evolutionGraph.getHeight();
		
		graphPanel.remove(evolutionGraph);
		evolutionGraph.remove();
		evolutionGraph = new EvolutionGraph(ctr, width, height, generations);
		graphPanel.add(evolutionGraph);
		
		//New garden
		gardenExteriorPanel.remove(gardenPanel);
		gardenPanel = new Garden(ctr, width/2, height/2, numCols, numRows);
		gardenExteriorPanel.add(gardenPanel, BorderLayout.CENTER);
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
	
	public void cangeCenterPanel(Boolean inDisplayGraph) {
		if(inDisplayGraph) {
			cardLayout.show(mainCenterPanel, gardenPanelId);
		}
		else {
			cardLayout.show(mainCenterPanel, graphPanelId);
		}
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
