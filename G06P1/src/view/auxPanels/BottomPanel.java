package view.auxPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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
import model.evaluationFunctions.EvaluationFunctionType;
import model.fenotypes.FenotypeType;

public class BottomPanel extends JPanel implements GenAlgObserver{
	private Controller ctr;
	
	private JComboBox<String> functionSelectionCombo;
	private DefaultComboBoxModel<String> functionSelectionComboModel; //Combo box to select the representation
	
	private JComboBox<String> repSelectionCombo;
	private DefaultComboBoxModel<String> repSelectionComboModel; //Combo box to select the representation
	
	private JPanel minimizationPanel; //Panel where the check box to indicate if the elitism is applied will be displayed
	private JCheckBox minimizationCheckBox; //Checkbox box to select minimization of maximization
	
	private JPanel repPrecisionPanel; //Panel where the spinner to select the precision will be displayed
	private JSpinner repPrecisionSpinner; //Spinner to select the precision of the representation
	
	private JPanel dimsPanel; //Panel where the spinner to select the dimension of the function
	private JSpinner dimsSpinner; //Spinner to select the dimension of the function
	
	private JButton runButton;
	
	private Color background = Color.GRAY;
	
	private int borderSize = 3;
	
	public BottomPanel(Controller ctr, JButton runButton) {
		this.runButton = runButton;
		this.ctr = ctr;
		initGUI();
		ctr.addObserver(this);
	}
	
	private void initGUI() {	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Top pannel
		JPanel topPanel = new JPanel();
		
		//Section to select the function
		functionSelectionComboModel = new DefaultComboBoxModel<String>();
		functionSelectionCombo = new JComboBox<String>(functionSelectionComboModel);
		
		functionSelectionCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(functionSelectionCombo.getSelectedItem() == EvaluationFunctionType.FUNCION1.toString()) {
					minimizationCheckBox.setSelected(false);
					repSelectionCombo.setSelectedItem(FenotypeType.PRECISION.toString());
					dimsPanel.setVisible(false);
				}
				else if(functionSelectionCombo.getSelectedItem() == EvaluationFunctionType.HOLDERTABLE.toString()) {
					minimizationCheckBox.setSelected(true);
					repSelectionCombo.setSelectedItem(FenotypeType.PRECISION.toString());
					dimsPanel.setVisible(false);
				}
				else if(functionSelectionCombo.getSelectedItem() == EvaluationFunctionType.MICHALEWICZ.toString()) {
					minimizationCheckBox.setSelected(true);
					repSelectionCombo.setSelectedItem(FenotypeType.REAL.toString());
					dimsPanel.setVisible(true);
				}
				else if(functionSelectionCombo.getSelectedItem() == EvaluationFunctionType.MISHRABIRD.toString()) {
					minimizationCheckBox.setSelected(true);
					repSelectionCombo.setSelectedItem(FenotypeType.PRECISION.toString());
					dimsPanel.setVisible(false);
				}
			}
		});
		
		JPanel selectFunctionPanel = new JPanel();
		createComboBoxArea(selectFunctionPanel, "Selecciona la función",functionSelectionCombo, 20);
		topPanel.add(selectFunctionPanel);
		topPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		
		//Section to select the number of dimensions
		dimsPanel = new JPanel();
		dimsSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
		createSpinnerSection(dimsPanel, "Dimensiones: ", dimsSpinner, 20);
		topPanel.add(dimsPanel);
		dimsPanel.setVisible(false);
		topPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		
		//Section to select minimization or maximization
		minimizationPanel = new JPanel();
		minimizationCheckBox = new JCheckBox();
		createCheckBoxSection(minimizationPanel, "Minimización: ", minimizationCheckBox, 20);
		topPanel.add(minimizationPanel);
		topPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		
		//Section to select the representation
		JPanel  repSelectionPanel = new JPanel();
		repSelectionComboModel = new DefaultComboBoxModel<String>();
		repSelectionCombo = new JComboBox<String>(repSelectionComboModel);
		
		repSelectionCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repSelectionCombo.getSelectedItem() == FenotypeType.PRECISION.toString()) {
					repPrecisionPanel.setVisible(true);
				}
				else {
					repPrecisionPanel.setVisible(false);
				}
			}
		});
		
		createComboBoxArea(repSelectionPanel, "Selecciona la representación", repSelectionCombo, 20);
		topPanel.add(repSelectionPanel);
		topPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		
		//Section to select the precision
		repPrecisionPanel = new JPanel();
		repPrecisionSpinner = new JSpinner(new SpinnerNumberModel(0.01, 0.0001, 1, 0.01));
		createSpinnerSection(repPrecisionPanel, "Precisión: ", repPrecisionSpinner, 20);
		topPanel.add(repPrecisionPanel);
		repPrecisionPanel.setVisible(false);
		topPanel.add(Box.createRigidArea(new Dimension(0, 40)));
		
		topPanel.setBackground(background);
		add(topPanel);
		
		//Buttons panel
		JPanel buttonsPanel = new JPanel();
		
		//A tool tip is added
		runButton.setToolTipText("run");
		runButton.setPreferredSize(new Dimension(35, 35));
		buttonsPanel.add(runButton);
		buttonsPanel.setBackground(background);
		
		add(buttonsPanel);
		
		setBackground(background);
		setBorder(BorderFactory.createMatteBorder(borderSize, 0, borderSize, 0, Color.BLACK));
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

//*********************************************************************************************
//Observer interface

	@Override
	public void onRegister() {
		functionSelectionComboModel.addAll(ctr.getEvaluationFunctionTypes());
		repSelectionComboModel.addAll(ctr.getFenotypeTypes());
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
	
//*********************************************************************************************
//Getters
	/**
	 * 
	 * @return the name of the evaluation function
	 */
	public String getEvaluationFunctionType() {
		return (String) functionSelectionCombo.getSelectedItem();
	}
	
	/**
	 * 
	 * @return the type of representation
	 */
	public String getRepresentationType() {
		return (String) repSelectionCombo.getSelectedItem();
	}
	
	/**
	 * 
	 * @return minimization
	 */
	public boolean getMinimization() {
		return minimizationCheckBox.isSelected();
	}
	
	/**
	 * 
	 * @return precision
	 */
	public double getPrecision() {
		return (double) repPrecisionSpinner.getValue();
	}
	
	
	/**
	 * 
	 * @return dimensions
	 */
	public int getDimensions() {
		return (int) dimsSpinner.getValue();
	}


}
