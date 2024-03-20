package view.auxPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Controller;
import model.GenAlgObserver;
import model.chromosomes.Chromosome;
import view.graphs.EvolutionGraph;

public class CenterPanel extends JPanel implements GenAlgObserver{
	
	private EvolutionGraph evolutionGraph;
	private Controller ctr;
	
	private JPanel graphPanel;
	private JPanel solPanel;
	
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
		graphPanel.setPreferredSize(new Dimension(initialWidth, initialHeight));
		graphPanel.add(evolutionGraph);
		add(graphPanel);
		
		//Solution panel
		solPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		solLabel = createLabel("Evaluaci√≥n: ");
		solPanel.setPreferredSize(new Dimension(width, TAMSOLPANEL));
		solPanel.add(solLabel);
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
	public void onAlgFinished(Chromosome c) {
		String text = "";
		
		if(ctr.getMinimization())
			text += "M·nimo: " + new DecimalFormat("#.##").format(c.getEvaluation()) + " en";
		else
			text += "M·ximo: " +  new DecimalFormat("#.##").format(c.getEvaluation()) + " en";
		
		for(int i = 0; i < c.getNumGenes(); i++) {
			text += " x" + (i + 1) + " = " + new DecimalFormat("#.##").format(c.getGeneFenotype(i));
		}
		
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
