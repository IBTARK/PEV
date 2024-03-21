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
import model.crossover.CycleCrossover;
import model.crossover.IJCrossover;
import model.crossover.OrderCrossover;
import model.crossover.OrderPrioritaryPositionsCrossover;
import model.crossover.OrderPriorityOrderCrossover;
import model.crossover.OrdinalCodificationCrossover;
import model.crossover.PMXCrossover;
import model.evaluationFunctions.AirportFunction;
import model.fenotypes.AirportRepresentation;
import model.fenotypes.FenotypeFunction;
import model.mutation.ExchangeMutation;
import model.mutation.HeuristicMutation;
import model.mutation.IJMutation;
import model.mutation.InsertionMutation;
import model.mutation.InversionMutation;
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
				topPanel.setTitle("Airport");
				//Adjust the genetic algorithms settings
				setGeneralSettings();
				centerPanel.newEvolutionGraph(leftPanel.getGenerations());
				setEvaluationFunction();
				setSelection();
				setCrossover();
				setMutation();
				setProblem();
				ctr.setMinimization(true); //TODO revisar
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
			case "Order":
			{
				ctr.setCrossover(new OrderCrossover());
				break;
			}
			case "PMX":
			{
				ctr.setCrossover(new PMXCrossover());
				break;
			}
			case "Order prioritary positions":
			{
				ctr.setCrossover(new OrderPrioritaryPositionsCrossover(leftPanel.getNumPositionsCrossover()));
				break;
			}
			case "Order priority":
			{
				ctr.setCrossover(new OrderPriorityOrderCrossover(leftPanel.getNumPositionsCrossover()));
				break;
			}
			case "Ordinal codification":
			{
				ctr.setCrossover(new OrdinalCodificationCrossover(bottomPanel.getProblem().intValue()));
				break;
			}
			case "Cycle":
			{
				ctr.setCrossover(new CycleCrossover());
				break;
			}
			case "IJ":
			{
				ctr.setCrossover(new IJCrossover());
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
			case "Inversion":
			{
				ctr.setMutation(new InversionMutation());
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
			case "Insertion":
			{
				ctr.setMutation(new InsertionMutation(leftPanel.getNumInsertions()));
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
			case "Heuristic":
			{
				ctr.setMutation(new HeuristicMutation(leftPanel.getNumPositions(), ctr.getFitnessFunction()));
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
			case "Exchange":
			{
				ctr.setMutation(new ExchangeMutation());
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
			case "IJ":
			{
				ctr.setMutation(new IJMutation());
				ctr.setMutationProb(leftPanel.getMutationPctg() / 100);
				break;
			}
		}
	}
	
	/**
	 * Set the problem type
	 */
	private void setProblem() {
		Double im = bottomPanel.getProblem();
		switch (bottomPanel.getProblem().intValue())
		{
			case 12:
			{
				ctr.setNumFlights(12);
				ctr.setNumTracks(3);
				ctr.setMinimization(true);
				break;
			}
			case 25:
			{
				ctr.setNumFlights(25);
				ctr.setNumTracks(5);
				ctr.setMinimization(true);
				break;
			}
		}
	}
	
	/**
	 * Set the evaluation function
	 */
	private void setEvaluationFunction() {
		ctr.setEvaluationFunction(new AirportFunction());
	}
	
	/**
	 * Set the representation
	 */
	private void setRepresentation() {
		ctr.setChromosomeType(ChromosomeType.AIRPORTCHROMOSOME);
		airportSettings();		
		numGenesSettings();
	}
	
	/**
	 * Set the number of genes
	 */
	private void numGenesSettings() {
		ctr.setNumGenes(1); //TODO revisar
	}
	
	/**
	 * airport representation settings
	 */
	private void airportSettings() {
		ArrayList<FenotypeFunction> fenotypes = new ArrayList<FenotypeFunction>();
		ArrayList<Integer> genesLengths = new ArrayList<Integer>();
		
		
		genesLengths.add(1);
		fenotypes.add(new AirportRepresentation(1.0, bottomPanel.getProblem()));
		ctr.setGenesFenotypesFunctions(fenotypes);
		ctr.setGenesLengths(genesLengths);
	}
}
