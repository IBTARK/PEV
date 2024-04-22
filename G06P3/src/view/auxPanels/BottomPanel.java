package view.auxPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import control.Controller;
import model.GenAlgObserver;
import model.representation.Representation;
import model.representation.RepresentationType;

public class BottomPanel extends JPanel implements GenAlgObserver{
	private Controller ctr;
	
	private JPanel problemPanel; //Panel to select the problem
	private JComboBox<String> representationCombo;
	private DefaultComboBoxModel<String> representationComboModel; //Combo box to select the representation
	
	private JPanel gardenDimensionPanel; //Panel to select the number of columns and rows of the garden
	private JSpinner numColsSpinner;
	private JSpinner numRowsSpinner;
	
	private JButton changeCenterPanelButton;
	private JButton runButton;
	
	private Color background = Color.GRAY;
	
	private int borderSize = 3;
	
	public BottomPanel(Controller ctr, JButton runButton, JButton changeCenterPanelButton) {
		this.runButton = runButton;
		this.changeCenterPanelButton = changeCenterPanelButton;
		this.ctr = ctr;
		initGUI();
		ctr.addObserver(this);
	}
	
	private void initGUI() {	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Top pannel
		JPanel topPanel = new JPanel();
		
		//Section to select the representation
		problemPanel = new JPanel();
		representationComboModel = new DefaultComboBoxModel<String>();
		representationCombo = new JComboBox<String>(representationComboModel);
		//default
		representationComboModel.setSelectedItem(RepresentationType.MOWERTREE.toString());
		createComboBoxArea(problemPanel, "Selecciona la representación", representationCombo, 20);
		topPanel.add(problemPanel);
		topPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		topPanel.setBackground(background);
		//problemPanel.setVisible(true);
		add(topPanel);
		
		//Section to select the dimensions of the garden
		gardenDimensionPanel = new JPanel();
		gardenDimensionPanel.setLayout(new BoxLayout(gardenDimensionPanel, BoxLayout.X_AXIS));
		gardenDimensionPanel.add(createLabel("Número de columnas: "));
		//Space between label and spinner
		gardenDimensionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		numColsSpinner = new JSpinner(new SpinnerNumberModel(8, 2, 100000, 1));
		numColsSpinner.setPreferredSize(new Dimension(50, 20));
		numColsSpinner.setMaximumSize(new Dimension(50, 20));
		numColsSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		gardenDimensionPanel.add(numColsSpinner);
		//Space between spinner and label
		gardenDimensionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		gardenDimensionPanel.add(createLabel("Número de filas: "));
		//Space between label and spinner
		gardenDimensionPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		numRowsSpinner = new JSpinner(new SpinnerNumberModel(8, 2, 100000, 1));
		numRowsSpinner.setPreferredSize(new Dimension(50, 20));
		numRowsSpinner.setMaximumSize(new Dimension(50, 20));
		numRowsSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		gardenDimensionPanel.add(numRowsSpinner);
		gardenDimensionPanel.setVisible(false);
		
		gardenDimensionPanel.setBackground(background);
		add(gardenDimensionPanel);
		
		
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		//Buttons panel
		JPanel buttonsPanel = new JPanel();
		
		//A tool tip is added
		runButton.setToolTipText("run");
		runButton.setPreferredSize(new Dimension(35, 35));
		buttonsPanel.add(runButton);
		buttonsPanel.setBackground(background);
		
		//Button to change the center panel
		changeCenterPanelButton.setPreferredSize(new Dimension(35, 35));
		buttonsPanel.add(changeCenterPanelButton);
		
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
	
	public void hideGardenDimsPanel(Boolean hide) {
		if(hide) {
			gardenDimensionPanel.setVisible(false);
		}
		else {
			gardenDimensionPanel.setVisible(true);
		}
	}

//*********************************************************************************************
//Observer interface

	@Override
	public void onRegister() {
		representationComboModel.addAll(ctr.getRepresentationTypes());
	}

	@Override
	public void onGenCompleted(int generation, double absoluteBest, double generationBest, double meanGeneration) {
		
	}
	
	@Override
	public void onAlgFinished(Representation c) {
		
	}
	
	@Override
	public void onFirstGen() {
		
	}
	
	@Override
	public void remove() {
		ctr.removeObserver(this);
	}
	
//*********************************************************************************************
//Getters
	
	public String getRepresentation() {
		return (String) representationCombo.getSelectedItem();
	}
	
	public int getNumCols() {
		return (int) numColsSpinner.getValue();
	}
	
	public int getNumRows() {
		return (int) numRowsSpinner.getValue();
	}
}
