package view.auxPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import control.Controller;
import model.GenAlgObserver;
import model.chromosomes.Chromosome;
import model.crossover.CrossoverType;
import model.selection.SelectionType;

public class LeftPanel extends JPanel implements GenAlgObserver{
	private Controller ctr;
	
	private JButton menuButton;
	
	private JPanel popSizePanel; //Panel where the spinner to select the population will be displayed
	private JSpinner popSizeSpinner; //Spinner to select the population size
	
	private JPanel numGenPanel; //Panel where the spinner to select the number of generations will be displayed
	private JSpinner numGenSpinner; //Spinner to select the number of generations
	
	private JPanel crossPctgPanel; //Panel where the spinner to select the percentage of crossover will be displayed
	private JSpinner crossPctgSpinner; //Spinner to select the percentage of crossover
	
	private JPanel mutPctgPanel; //Panel where the spinner to select the percentage of mutation will be displayed
	private JSpinner mutPctgSpinner; //Spinner to select the percentage of mutation
	
	private JPanel selectionPanel; //Panel where the combo box to select the selection method will be displayed
	private DefaultComboBoxModel<String> selectionModel;
	private JComboBox<String> selectionComboBox; //Combo box to select the selection method
	
	private JPanel selectKPanel;
	private JSpinner selectKSpinner;
	
	private JPanel selectTruncPanel;
	private JSpinner selectTruncSpinner;
	
	private JPanel probabilisticPanel; 
	private JCheckBox probabilisticCheckBox; //CheckBox to indicate if the selecton is probabilistic
	
	private JPanel crossoverPanel; //Panel where the combo box to select the crossover method will be displayed
	private DefaultComboBoxModel<String> crossoverModel;
	private JComboBox<String> crossoverComboBox; //Combo box to select the crossover method
	
	private JPanel selectAlphaPanel;
	private JSpinner selectAlphaSpinner;
	
	private JPanel mutationPanel; //Panel where the combo box to select the mutation method will be displayed
	private DefaultComboBoxModel<String> mutationModel;
	private JComboBox<String> mutationComboBox; //Combo box to select the mutation method
	
	private JPanel elitismPanel; //Panel where the check box to indicate if the elitism is applied will be displayed
	private JCheckBox elitismCheckBox; //CheckBox to indicate if elitism is applied
	
	private JPanel elitismPctgPanel; //Panel where the spinner to select the percentage of elitism will be displayed
	private JSpinner elitismPctgSpinner; //Spinner to select the percentage of elitism
	
	private Color background = Color.GRAY;
	
	private int borderSize = 3;

	
	public LeftPanel(Controller ctr, JButton menuButton, int width, int height) {
		this.ctr = ctr;
		this.menuButton = menuButton;
		initGUI(width, height);
		ctr.addObserver(this);
	}
	
	private void initGUI(int width, int height) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//The menu button is added to the panel
		menuButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons/menu.png")));
		menuButton.setText(null);
		int menuButtonWidth = 24;
		menuButton.setMinimumSize(new Dimension(menuButtonWidth, 24));
		menuButton.setMaximumSize(new Dimension(menuButtonWidth, 24));
		menuButton.setPreferredSize(new Dimension(menuButtonWidth, 24));
		menuButton.setOpaque(false);
		menuButton.setContentAreaFilled(false);
		menuButton.setBorderPainted(false);
		menuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(menuButton);
		
		//Spacing
		add(Box.createRigidArea(new Dimension(width - menuButtonWidth, 0)));
		
		//Section to select the population size
		popSizePanel = new JPanel();
		popSizeSpinner = new JSpinner(new SpinnerNumberModel(100, 2, 100000, 1));
		createSpinnerSection(popSizePanel, "Tamaño de la población: ", popSizeSpinner, 43);
		this.add(popSizePanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the number of generations
		numGenPanel = new JPanel();
		numGenSpinner = new JSpinner(new SpinnerNumberModel(100, 1, 100000, 1));
		createSpinnerSection(numGenPanel, "Número de generaciones: ", numGenSpinner, 35);
		add(numGenPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the percentage of crossover
		crossPctgPanel = new JPanel();
		crossPctgSpinner = new JSpinner(new SpinnerNumberModel(60.0, 0.0, 100.0, 1.0));
		//Configure the editor to display doubles
        crossPctgSpinner.setEditor(new JSpinner.NumberEditor(crossPctgSpinner, "0.0"));
		createSpinnerSection(crossPctgPanel, "Probabilidad de cruce: ", crossPctgSpinner, 56);
		add(crossPctgPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the percentage of mutation
		mutPctgPanel = new JPanel();
		mutPctgSpinner = new JSpinner(new SpinnerNumberModel(5.0, 0.0, 100.0, 1.0));
		//Configure the editor to display doubles
		mutPctgSpinner.setEditor(new JSpinner.NumberEditor(mutPctgSpinner, "0.0"));
		createSpinnerSection(mutPctgPanel, "Probabilidad de mutación: ", mutPctgSpinner, 32);
		add(mutPctgPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the selection method
		selectionPanel = new JPanel();
		selectionModel = new DefaultComboBoxModel<String>();
		selectionComboBox = new JComboBox<String>(selectionModel);
		
		selectionComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectionComboBox.getSelectedItem() == SelectionType.REMAIN.toString() || selectionComboBox.getSelectedItem() == SelectionType.TOURNAMENT.toString()) {
					selectKPanel.setVisible(true);
				}
				else {
					selectKPanel.setVisible(false);
				}
				
				if(selectionComboBox.getSelectedItem() == SelectionType.TOURNAMENT.toString()) {
					probabilisticPanel.setVisible(true);
				}
				else {
					probabilisticPanel.setVisible(false);
				}
				
				if(selectionComboBox.getSelectedItem() == SelectionType.TRUNCATION.toString()) {
					selectTruncPanel.setVisible(true);
				}
				else {
					selectTruncPanel.setVisible(false);
				}
			}
		});
		
		createComboBoxArea(selectionPanel, "Método de Selección: ", selectionComboBox, 13);
		add(selectionPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the value of k
		selectKPanel = new JPanel();
		selectKSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 100, 1));
		createSpinnerSection(selectKPanel, "k: ", selectKSpinner, 178);
		add(selectKPanel);
		selectKPanel.setVisible(false);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the value of trunc
		selectTruncPanel = new JPanel();
		selectTruncSpinner = new JSpinner(new SpinnerNumberModel(3.0, 1.0, 100.0, 1.0));
		//Configure the editor to display doubles
		selectTruncSpinner.setEditor(new JSpinner.NumberEditor(selectTruncSpinner, "0.0"));
		createSpinnerSection(selectTruncPanel, "trunc: ", selectTruncSpinner, 154);
		add(selectTruncPanel);
		selectTruncPanel.setVisible(false);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to indicate probabilistic
		probabilisticPanel = new JPanel();
		probabilisticCheckBox = new JCheckBox();
		createCheckBoxSection(probabilisticPanel, "Probabilista: ", probabilisticCheckBox, 143);
		add(probabilisticPanel);
		probabilisticPanel.setVisible(false);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		
		//Section to select the crossover method
		crossoverPanel = new JPanel();
		crossoverModel = new DefaultComboBoxModel<String>();
		crossoverComboBox = new JComboBox<String>(crossoverModel);
		
		crossoverComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(crossoverComboBox.getSelectedItem() == CrossoverType.ARITHMETIC.toString()|| crossoverComboBox.getSelectedItem() == CrossoverType.BLXALPHA.toString()) {
					selectAlphaPanel.setVisible(true);
				}
				else {
					selectAlphaPanel.setVisible(false);
				}
			}
		});
		
		createComboBoxArea(crossoverPanel, "Método de Cruce: ", crossoverComboBox, 33);
		add(crossoverPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the value of alpha
		selectAlphaPanel = new JPanel();
		selectAlphaSpinner = new JSpinner(new SpinnerNumberModel(60.0, 0.0, 100.0, 1.0));
		//Configure the editor to display doubles
		selectAlphaSpinner.setEditor(new JSpinner.NumberEditor(selectAlphaSpinner, "0.0"));
		createSpinnerSection(selectAlphaPanel, "Alpha: ", selectAlphaSpinner, 152);
		add(selectAlphaPanel);
		selectAlphaPanel.setVisible(false);
		add(Box.createRigidArea(new Dimension(0, 30)));

		//Section to select the mutation method
		mutationPanel = new JPanel();
		mutationModel = new DefaultComboBoxModel<String>();
		mutationComboBox = new JComboBox<String>(mutationModel);
		createComboBoxArea(mutationPanel, "Método de Mutacion: ", mutationComboBox, 12);
		add(mutationPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		
		//Section to select the percentage of elitism
		elitismPanel = new JPanel();
		elitismCheckBox = new JCheckBox();
		createCheckBoxSection(elitismPanel, "Elitismo: ", elitismCheckBox, 165);
		//Action listener of the check box
		elitismCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(elitismCheckBox.isSelected()) elitismPctgPanel.setVisible(true);
				else elitismPctgPanel.setVisible(false);
			}
		});
		add(elitismPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		elitismPctgPanel = new JPanel();
		elitismPctgSpinner = new JSpinner(new SpinnerNumberModel(10.0, 0.0, 100.0, 1.0));
		//Configure the editor to display doubles
		elitismPctgSpinner.setEditor(new JSpinner.NumberEditor(elitismPctgSpinner, "0.0"));
		createSpinnerSection(elitismPctgPanel, "Porcentaje de elitismo: ", elitismPctgSpinner, 50);
		elitismPctgPanel.setVisible(false); //By default is invisible
		add(elitismPctgPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//The background and a border is set
		setBackground(background);
		setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.BLACK));
		
		setPreferredSize(new Dimension(width - borderSize, height - borderSize));
	}
	
	/**
	 * Create a panel with a label and a spinner
	 * 
	 * @param panel panel where the label and the combo box will be displayed
	 * @param text string of the label
	 * @param spinner spinner to be added
	 * @param model of the spinner
	 * @param pixelsBtwnLabelSpinner pixels between the label and the spinner
	 */
	private void createSpinnerSection(JPanel panel, String text, JSpinner spinner, int pixelsBtwnLabelSpinner) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		//label
		JLabel label = createLabel(text);
		panel.add(label);
		
		//Space between label and spinner
		panel.add(Box.createRigidArea(new Dimension(pixelsBtwnLabelSpinner, 0)));
		
		//Spinner
		spinner.setPreferredSize(new Dimension(50, 20));
		spinner.setMaximumSize(new Dimension(50, 20));
		spinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(spinner);
		
		//Panel properties
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panel.setBackground(background);
	}
	
	/**
	 * Crates a panel with a label and a combo box
	 * 
	 * @param panel panel where the label and the combo box will be displayed
	 * @param text text text that will be displayed in the label
	 * @param comboBox
	 * @param pixelsBtwnLabelCombo pixels between the label and the combo box
	 */
	private void createComboBoxArea(JPanel panel, String text, JComboBox<String> comboBox, int pixelsBtwnLabelCombo) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		//label
		JLabel label = createLabel(text);
		panel.add(label);
		
		//Space between label and combo box
		panel.add(Box.createRigidArea(new Dimension(pixelsBtwnLabelCombo, 0)));
		
		//Combo box
		comboBox.setPreferredSize(new Dimension(100, 20));
		comboBox.setMaximumSize(new Dimension(100, 20));
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(comboBox);
		
		//Panel properties
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panel.setBackground(background);
	}
	
	/**
	 * Create a panel with a label and a checkbox
	 * 
	 * @param panel panel where the label and the check box will be displayed
	 * @param text string of the label
	 * @param checkbox check box to be added
	 * @param pixelsBtwnLabelCheck pixels between the label and the check box
	 */
	private void createCheckBoxSection(JPanel panel, String text, JCheckBox checkbox, int pixelsBtwnLabelCheck) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		//label
		JLabel label = createLabel(text);
		panel.add(label);
		
		//Space between label and spinner
		panel.add(Box.createRigidArea(new Dimension(pixelsBtwnLabelCheck, 0)));
		
		//Check box
		checkbox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(checkbox);
		
		//Panel properties
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panel.setBackground(background);
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
	
	/**
	 * @param visible true if the panels are set to visible
	 */
	public void setPanelsVisible(boolean visible) {
		popSizePanel.setVisible(visible); 
		numGenPanel.setVisible(visible);
		crossPctgPanel.setVisible(visible);
		mutPctgPanel.setVisible(visible);
		selectionPanel.setVisible(visible);
		crossoverPanel.setVisible(visible); 
		mutationPanel.setVisible(visible);
		elitismPanel.setVisible(visible);
	}

//*********************************************************************************************
//Getters
	
	/**
	 * 
	 * @return border size in pixels
	 */
	public int getBorderSize() {
		return borderSize;
	}
	
	/**
	 * 
	 * @return population size
	 */
	public int getPopulationSize() {
		return (int) popSizeSpinner.getValue();
	}
	
	/**
	 * 
	 * @return the number of generations
	 */
	public int getGenerations() {
		return (int) numGenSpinner.getValue();
	}
	
	/**
	 * 
	 * @return probability of crossover [0,100]
	 */
	public double getCrossoverPctg() {
		return (double) crossPctgSpinner.getValue();
	}
	
	/**
	 * 
	 * @return probability of mutation [0,100]
	 */
	public double getMutationPctg() {
		return (double) mutPctgSpinner.getValue();
	}
	
	/**
	 * 
	 * @return selected selection type
	 */
	public String getSelectionType() {
		return (String) selectionComboBox.getSelectedItem();
	}
	
	/**
	 * 
	 * @return the value of k
	 */
	public int getK() {
		return (int) selectKSpinner.getValue();
	}
	
	/**
	 * 
	 * @return the value of trunc
	 */
	public double getTruncation() {
		return (double) selectTruncSpinner.getValue();
	}
	
	/**
	 * 
	 * @return probabilistic
	 */
	public boolean getProbabilistic() {
		return probabilisticCheckBox.isSelected();
	}
	
	/**
	 * 
	 * @return selected crossover type
	 */
	public String getCrossoverType() {
		return (String) crossoverComboBox.getSelectedItem();
	}
	
	/**
	 * 
	 * @return value of alpha [0,100]
	 */
	public double getAlpha() {
		return (double) selectAlphaSpinner.getValue();
	}
	
	/**
	 * 
	 * @return selected mutation type
	 */
	public String getMutationType() {
		return (String) mutationComboBox.getSelectedItem();
	}
	
	/**
	 * 
	 * @return the percentage of elitism [0,100]
	 */
	public double getElitismPctg() {
		if(elitismCheckBox.isSelected()) {
			return (double) elitismPctgSpinner.getValue();
		}
		else return 0.0;
	}

//*********************************************************************************************
//Observer interface

	@Override
	public void onRegister() {
		selectionModel.addAll(ctr.getSelectionTypes());
		crossoverModel.addAll(ctr.getCrossoverTypes());
		mutationModel.addAll(ctr.getMutationTypes());
	}

	@Override
	public void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration) {
		
	}

	@Override
	public void onAlgFinished(Chromosome c) {
		
	}

	@Override
	public void onFirstGen() {
		
	}
}
