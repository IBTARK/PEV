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
import model.chromosomes.ChromosomeType;
import model.crossover.ArithmeticCrossover;
import model.crossover.BLXAlphaCrossover;
import model.crossover.SinglePointCrossover;
import model.crossover.UniformCrossover;
import model.evaluationFunctions.Funcion1;
import model.evaluationFunctions.HolderTable;
import model.evaluationFunctions.Michalewicz;
import model.evaluationFunctions.MishraBird;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.PrecisionRepresentation;
import model.fenotypes.RealRepresentation;
import model.mutation.GenericMutation;
import model.selection.MontecarloSelection;
import model.selection.RankingSelection;
import model.selection.RemainSelection;
import model.selection.StochasticSelection;
import model.selection.TournamentSelection;
import model.selection.TruncationSelection;
import view.auxPanels.BottomPanel;
import view.auxPanels.CenterPanel;
import view.auxPanels.LeftPanel;
import view.auxPanels.TopPanel;

public class MainPanel extends JPanel{
	private Controller ctr;
	
	private JButton menuButton;
	private JButton runButton;
	
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
				topPanel.setTitle(bottomPanel.getEvaluationFunctionType());
				//Adjust the genetic algorithms settings
				setGeneralSettings();
				centerPanel.newEvolutionGraph(leftPanel.getGenerations());
				setSelection();
				setCrossover();
				setMutation();
				setEvaluationFunction();
				ctr.setMinimization(bottomPanel.getMinimization());
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
			case "Arithmetic":
			{
				ctr.setCrossover(new ArithmeticCrossover(leftPanel.getAlpha() / 100));
				break;
			}
			case "BLXAlpha":
			{
				ctr.setCrossover(new BLXAlphaCrossover(leftPanel.getAlpha() / 100));
				break;
			}
			case "Single point":
			{
				ctr.setCrossover(new SinglePointCrossover());
				break;
			}
			case "Uniform":
			{
				ctr.setCrossover(new UniformCrossover(leftPanel.getCrossoverPctg() / 100));
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
			case "Generic":
			{
				ctr.setMutation(new GenericMutation());
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
		}
	}
	
	/**
	 * Set the evaluation function
	 */
	private void setEvaluationFunction() {
		switch (bottomPanel.getEvaluationFunctionType())
		{
			case "Funcion1":
			{
				ctr.setEvaluationFunction(new Funcion1());
				break;
			}
			case "Holder Table":
			{
				ctr.setEvaluationFunction(new HolderTable());
				break;
			}
			case "Michalewicz":
			{
				ctr.setEvaluationFunction(new Michalewicz(bottomPanel.getDimensions()));
				break;
			}
			case "Mishra Bird":
			{
				ctr.setEvaluationFunction(new MishraBird());
				break;
			}
		}
	}
	
	/**
	 * Set the representation
	 */
	private void setRepresentation() {
		switch (bottomPanel.getRepresentationType())
		{
			case "Binary":
			{
				ctr.setChromosomeType(ChromosomeType.BINARYCHROMOSOME);
				binarySettings();
				break;
			}
			case "Real":
			{
				ctr.setChromosomeType(ChromosomeType.REALCHROMOSOME);
				realSettings();
				break;
			}
		}
		
		numGenesSettings();
	}
	
	/**
	 * Set the number of genes
	 */
	private void numGenesSettings() {
		switch (bottomPanel.getEvaluationFunctionType())
		{
			case "Funcion1":
			{
				ctr.setNumGenes(2);
				break;
			}
			case "Holder Table":
			{
				ctr.setNumGenes(2);
				break;
			}
			case "Michalewicz":
			{
				ctr.setNumGenes(bottomPanel.getDimensions());
				break;
			}
			case "Mishra Bird":
			{
				ctr.setNumGenes(2);
				break;
			}
		}
	}
	
	/**
	 * Real representation settings
	 */
	private void realSettings() {
		ArrayList<FenotypeFunction> fenotypes = new ArrayList<FenotypeFunction>();
		ArrayList<Integer> genesLengths = new ArrayList<Integer>();
		
		switch (bottomPanel.getEvaluationFunctionType())			
		{
			case "Funcion1":
			{
				genesLengths.add(1);
				fenotypes.add(new RealRepresentation(-10.0, 10.0));
				ctr.setGenesFenotypesFunctions(fenotypes);
				ctr.setGenesLengths(genesLengths);
				break;
			}
			case "Holder Table":
			{
				genesLengths.add(1);
				fenotypes.add(new RealRepresentation(-10.0, 10.0));
				ctr.setGenesFenotypesFunctions(fenotypes);
				ctr.setGenesLengths(genesLengths);
				break;
			}
			case "Michalewicz":
			{
				genesLengths.add(1);
				fenotypes.add(new RealRepresentation(0.0, Math.PI));
				ctr.setGenesFenotypesFunctions(fenotypes);
				ctr.setGenesLengths(genesLengths);
				break;
			}
			case "Mishra Bird":
			{
				genesLengths.add(1);
				genesLengths.add(1);
				fenotypes.add(new RealRepresentation(-10.0, 0.0));
				fenotypes.add(new RealRepresentation(-6.5, 0.0));
				ctr.setGenesFenotypesFunctions(fenotypes);
				ctr.setGenesLengths(genesLengths);
				break;
			}
		}
	}
	
	/**
	 * Binary representation settings
	 */
	private void binarySettings() {
		ArrayList<FenotypeFunction> fenotypes = new ArrayList<FenotypeFunction>();
		ArrayList<Integer> genesLengths = new ArrayList<Integer>();
		
		switch (bottomPanel.getEvaluationFunctionType())			
		{
			case "Funcion1":
			{
				genesLengths.add(computeBinaryGeneLength(-10.0, 10.0, bottomPanel.getPrecision()));
				fenotypes.add(new PrecisionRepresentation(-10.0, 10.0, genesLengths.get(0)));
				ctr.setGenesFenotypesFunctions(fenotypes);
				ctr.setGenesLengths(genesLengths);
				break;
			}
			case "Holder Table":
			{
				genesLengths.add(computeBinaryGeneLength(-10.0, 10.0, bottomPanel.getPrecision()));
				fenotypes.add(new PrecisionRepresentation(-10.0, 10.0, genesLengths.get(0)));
				ctr.setGenesFenotypesFunctions(fenotypes);
				ctr.setGenesLengths(genesLengths);
				break;
			}
			case "Michalewicz":
			{
				genesLengths.add(computeBinaryGeneLength(0.0, Math.PI, bottomPanel.getPrecision()));
				fenotypes.add(new PrecisionRepresentation(0.0, Math.PI, genesLengths.get(0)));
				ctr.setGenesFenotypesFunctions(fenotypes);
				ctr.setGenesLengths(genesLengths);
				break;
			}
			case "Mishra Bird":
			{
				genesLengths.add(computeBinaryGeneLength(-10.0, 0.0, bottomPanel.getPrecision()));
				genesLengths.add(computeBinaryGeneLength(-6.5, 0.0, bottomPanel.getPrecision()));
				fenotypes.add(new PrecisionRepresentation(-10.0, 0.0, genesLengths.get(0)));
				fenotypes.add(new PrecisionRepresentation(-6.5, 0.0, genesLengths.get(1)));
				ctr.setGenesFenotypesFunctions(fenotypes);
				ctr.setGenesLengths(genesLengths);
				break;
			}
		}
	}
	
	/**
	 * Size of a binary gene
	 * 
	 * @param precision
	 * @param min
	 * @param max
	 * @return
	 */
	private int computeBinaryGeneLength(double min, double max, double precision) {
		return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
	}
}
