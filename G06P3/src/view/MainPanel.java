package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import control.Controller;
import model.evaluationFunctions.MowerEvaluation;
import model.fenotypes.MowerFenotypeFunction;
import model.fenotypes.TreeFenotypeFunction;
import model.representation.RepresentationType;
import model.selection.MontecarloSelection;
import model.selection.RankingSelection;
import model.selection.RemainSelection;
import model.selection.StochasticSelection;
import model.selection.TournamentSelection;
import model.selection.TruncationSelection;
import model.treeRep.crossover.TreeCrossover;
import model.treeRep.mutation.FunctionalMutation;
import model.treeRep.mutation.PermutationMutation;
import model.treeRep.mutation.SubTreeMutation;
import model.treeRep.mutation.TerminalMutation;
import model.treeRep.symbols.MowerSymbols;
import model.treeRep.trees.InitializationType;
import view.auxPanels.BottomPanel;
import view.auxPanels.CenterPanel;
import view.auxPanels.LeftPanel;
import view.auxPanels.TopPanel;

public class MainPanel extends JPanel{
	private Controller ctr;
	
	private JButton menuButton;
	private JButton runButton;
	private Boolean graphInDisp;
	
	private int windowsWidth;
	private int windowsHeight;
	
	private boolean menuVisible;
	
	private TopPanel topPanel;
	private LeftPanel leftPanel;
	private CenterPanel centerPanel;
	private BottomPanel bottomPanel;
	
	public static final int LEFTPANELWIDTH = 270; //To adjust the size of the left panel
	public static final double TOPPANELPCTGHEIGHT = 0.1;
	public static final double BOTTOMPANELPCTGHEIGHT = 0.18;
	
	public MainPanel(Controller ctr, int windowsWidth, int windowsHeight) {
		super(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		this.ctr = ctr;
		this.windowsWidth = windowsWidth;
		this.windowsHeight = windowsHeight;
		menuVisible = true;
		graphInDisp = true;
		
		initGUI();
	}
	
	private void initGUI() {
		
		//Left panel and the menu button
		menuButton = new JButton();
		leftPanel = new LeftPanel(ctr, menuButton, LEFTPANELWIDTH, windowsHeight);
		
		add(leftPanel);
		
		JPanel restPanel = new JPanel(new BorderLayout());
		restPanel.setMinimumSize(new Dimension(windowsWidth - LEFTPANELWIDTH -13 , windowsHeight));
		
		topPanel = new TopPanel("Evolutiva", windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * TOPPANELPCTGHEIGHT));
		restPanel.add(topPanel, BorderLayout.PAGE_START);
		
		//Action listener of the button menu
		menuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuVisible = !menuVisible;
				leftPanel.setPanelsVisible(menuVisible);
				
				if(menuVisible) {
					leftPanel.setPreferredSize(new Dimension(LEFTPANELWIDTH - leftPanel.getBorderSize(), windowsHeight - leftPanel.getBorderSize()));
					restPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, windowsHeight));
					topPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * TOPPANELPCTGHEIGHT)));
					centerPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * (1 - TOPPANELPCTGHEIGHT - BOTTOMPANELPCTGHEIGHT))));
					centerPanel.setPreferredSizeGraph(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * (1 - TOPPANELPCTGHEIGHT - BOTTOMPANELPCTGHEIGHT )));
					bottomPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * BOTTOMPANELPCTGHEIGHT)));
				}
				else {
					leftPanel.setPreferredSize(new Dimension(50 - leftPanel.getBorderSize(), windowsHeight - leftPanel.getBorderSize()));
					restPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13 + LEFTPANELWIDTH - 50, windowsHeight));
					topPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13 + LEFTPANELWIDTH - 50, (int) Math.round(windowsHeight * TOPPANELPCTGHEIGHT)));
					centerPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13 + LEFTPANELWIDTH - 50, (int) Math.round(windowsHeight * (1 - TOPPANELPCTGHEIGHT - BOTTOMPANELPCTGHEIGHT))));
					centerPanel.setPreferredSizeGraph(windowsWidth - LEFTPANELWIDTH - 13 + LEFTPANELWIDTH - 50, (int) Math.round(windowsHeight * (1 - TOPPANELPCTGHEIGHT - BOTTOMPANELPCTGHEIGHT)));
					bottomPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13 + LEFTPANELWIDTH - 50, (int) Math.round(windowsHeight * BOTTOMPANELPCTGHEIGHT)));
				}
			}
		});
		
		//Center panel
		centerPanel = new CenterPanel(ctr, windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * (1 - TOPPANELPCTGHEIGHT - BOTTOMPANELPCTGHEIGHT)) - 13, leftPanel.getGenerations());
		centerPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * (1 - TOPPANELPCTGHEIGHT - BOTTOMPANELPCTGHEIGHT))));
		restPanel.add(centerPanel, BorderLayout.CENTER);
		
		//Run button
		runButton = new JButton(new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons/run.png")));
		
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Change the label
				topPanel.setTitle("Garden");
				//Adjust the genetic algorithms settings
				setGeneralSettings();
				centerPanel.newExecution(leftPanel.getGenerations(), bottomPanel.getNumCols(), bottomPanel.getNumRows());
				setEvaluationFunction();
				setSelection();
				setCrossover();
				setMutation();
				ctr.setMinimization(false); //TODO revisar
				setRepresentation();
				
				//Execute the genetic algorithm
				ctr.execute();
			}
			
		});
		
		//Bottom panel
		bottomPanel = new BottomPanel(ctr, runButton);
		bottomPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, (int) Math.round(windowsHeight * BOTTOMPANELPCTGHEIGHT)));
		restPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		restPanel.setPreferredSize(new Dimension(windowsWidth - LEFTPANELWIDTH - 13, windowsHeight));
		add(restPanel);
	}
	
	private void setGeneralSettings() {
		ctr.setPopulationSize(leftPanel.getPopulationSize());
		ctr.setNumGenerations(leftPanel.getGenerations());
		ctr.setCrossoverPctg(leftPanel.getCrossoverPctg() / 100);
		ctr.setElitism(leftPanel.getElitismPctg() / 100);
	}
	
	
	/**
	 * Set the selection type
	 */
	private void setSelection() {
		
		switch (leftPanel.getSelectionType())
		{
			case "Montecarlo":
			{
				ctr.setSelection(new MontecarloSelection());
				break;
			}
			case "Remain":
			{
				ctr.setSelection(new RemainSelection(leftPanel.getK()));
				break;
			}
			case "Tournament":
			{
				ctr.setSelection(new TournamentSelection(leftPanel.getProbabilistic() ,leftPanel.getK()));
				break;
			}
			case "Truncation":
			{
				ctr.setSelection(new TruncationSelection(leftPanel.getTruncation() / 100));
				break;
			}
			case "Stochastic":
			{
				ctr.setSelection(new StochasticSelection());
				break;
			}
			case "Ranking":
			{
				ctr.setSelection(new RankingSelection(leftPanel.getBeta()));
				break;
			}
		}
	}
	
	/**
	 * Set the crossover type
	 */
	private void setCrossover() {
		
		switch (leftPanel.getCrossoverType())
		{
			case "Arbol":
			{
				ctr.setCrossover(new TreeCrossover(leftPanel.getFunctionalOrTerminalProb()));
				break;
			}
		}
	}
	
	/**
	 * Set the mutation type
	 */
	private void setMutation() {
		
		switch (leftPanel.getMutationType())
		{
			case "Funcional":
			{
				ctr.setMutation(new FunctionalMutation());
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
			case "Terminal":
			{
				ctr.setMutation(new TerminalMutation());
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
			case "Permutacion":
			{
				ctr.setMutation(new PermutationMutation());
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
			case "Subarbol":
			{
				ctr.setMutation(new SubTreeMutation());
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
		}
	}
	
	/**
	 * Set the fenotype function
	 */
	private void setFenotypeFunction() {
		ctr.setFenotypeFunction(new TreeFenotypeFunction());
	}
	
	/**
	 * Set the evaluation function
	 */
	private void setEvaluationFunction() {
		ctr.setEvaluationFunction(new MowerEvaluation(bottomPanel.getNumCols(), bottomPanel.getNumRows()));
	}
	
	/**
	 * Set the representation
	 */
	private void setRepresentation() {
		switch (bottomPanel.getRepresentation()) {
			case "Grammar":
			{
				ctr.setRepresentationType(RepresentationType.GRAMMAR);
				grammarSettings();
				break;
			}
			case "MowerTree":
			{
				ctr.setRepresentationType(RepresentationType.MOWERTREE);
				treeSettings();
				break;
			}
		}
	}
	
	private void grammarSettings() {
		ArrayList<Integer> genesLengths = new ArrayList<Integer>();
		genesLengths.add(1);
		ctr.setGenesLengths(genesLengths);
		ctr.setFenotypeFunction(new MowerFenotypeFunction());
		numGenesSettings();
	}
	
	private void treeSettings() {
		switch (leftPanel.getIniType())
		{
			case "Completa":
			{
				ctr.setIniType(InitializationType.FULL);
				break;
			}
			case "Creciente":
			{
				ctr.setIniType(InitializationType.GROW);
				break;
			}
			case "Ramped and half":
			{
				ctr.setIniType(InitializationType.RAMPEDANDHALF);
				break;
			}
		}
		ctr.setSymbols(new MowerSymbols(bottomPanel.getNumCols(), bottomPanel.getNumRows()));
		ctr.setFenotypeFunction(new TreeFenotypeFunction());
		ctr.setMaxHeight(5);
		ctr.setMinHeight(1);
		numGenesSettings();
	}
	
	/**
	 * Set the number of genes
	 */
	private void numGenesSettings() {
		ctr.setNumGenes(1); //TODO revisar
	}
}
