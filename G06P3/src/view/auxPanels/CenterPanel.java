package view.auxPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import control.Controller;
import model.GenAlgObserver;
import model.chromosomes.Chromosome;
import view.graphs.EvolutionGraph;
import view.tables.RunwayTable;

public class CenterPanel extends JPanel implements GenAlgObserver{
	
	private EvolutionGraph evolutionGraph;
	private Controller ctr;
	
	private JPanel graphPanel;
	private JPanel solPanel;
	private JPanel tablesPanel;
	
	private JLabel solLabel;
	
	private ArrayList<JTable> tables;
	
	private static final int TAMSOLPANEL = 130;
	
	private int initialWidth;
	private int initialHeight;
	
	public CenterPanel(Controller ctr, int width, int height, int generations) {
		this.ctr = ctr;
		initialWidth = width;
		initialHeight = height;
		
		tables = new ArrayList<JTable>();
		
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
		add(graphPanel);
		
		//Solution panel
		solPanel = new JPanel();
		solPanel.setLayout(new BoxLayout(solPanel, BoxLayout.Y_AXIS));
		solLabel = createLabel("Evaluación: ");
		solLabel.setAlignmentX(CENTER_ALIGNMENT);
		solPanel.setPreferredSize(new Dimension(width, TAMSOLPANEL));
		solPanel.add(solLabel);
		solPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		tablesPanel = new JPanel();
		solPanel.add(new JScrollPane(tablesPanel));
		
		
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
	public void onAlgFinished(Chromosome c, int numTracks, HashMap<Integer, ArrayList<String>> flightsInfo) {
		String text = "";
		
		if(ctr.getMinimization())
			text += "Minimo: " + new DecimalFormat("#.##").format(c.getEvaluation()) + " en";
		else
			text += "Maximo: " +  new DecimalFormat("#.##").format(c.getEvaluation()) + " en";
		
		text += "[";
		for(int i = 0; i < c.getNumGenes(); i++) {
			text += " " + new DecimalFormat("#.##").format(c.getGeneFenotype(i)) + ",";
		}
		text += "]";
		
		graphPanel.setPreferredSize(new Dimension(initialWidth, initialHeight - TAMSOLPANEL));
		setPreferredSizeGraph(initialWidth, initialHeight - TAMSOLPANEL);
		
		solLabel.setText(text);
		
		tablesPanel.removeAll();
		
		tables = new ArrayList<JTable>();
		
		for(int track = 1; track <= numTracks; track++) {
			RunwayTable tableModel = new RunwayTable(track, flightsInfo);
			JTable table = new JTable(tableModel);
			tables.add(table);
			
			JPanel tablePanel = new JPanel(new BorderLayout());
			tablePanel.add(new JScrollPane(table));
			
			tablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(20, 100, 70), 4), "Pista " + track));
			tablePanel.setPreferredSize(new Dimension(200, 100));
			
			tablesPanel.add(tablePanel);
			
			tableModel.setElems(c);
			
		}
		
		solPanel.setVisible(true);
	}

	@Override
	public void remove() {
		ctr.removeObserver(this);
	}

}
