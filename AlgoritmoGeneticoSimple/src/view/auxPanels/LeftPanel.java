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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class LeftPanel extends JPanel{
	
	private JButton menuButton;
	
	private JPanel popSizePanel; //Panel where the spinner to select the population will be displayed
	private JSpinner popSizeSpinner; //Spinner to select the population size
	
	private JPanel numGenPanel; //Panel where the spinner to select the number of generations will be displayed
	private JSpinner numGenSpinner; //Spinner to select the number of generations
	
	private JPanel crossPctgPanel; //Panel where the spinner to select the percentage of crossover will be displayed
	private JSpinner crossPctgSpinner; //Spinner to select the percentage of crossover
	
	private JPanel mutPctgPanel; //Panel where the spinner to select the percentage of mutation will be displayed
	private JSpinner mutPctgSpinner; //Spinner to select the percentage of mutation
	
	private JPanel repPrecisionPanel; //Panel where the spinner to select the precision will be displayed
	private JSpinner repPrecisionSpinner; //Spinner to select the precision of the representation
	
	private JPanel elitismPctgPanel; //Panel where the spinner to select the percentage of elitism will be displayed
	private JSpinner elitismPctgSpinner; //Spinner to select the percentage of elitism
	
	private JPanel selectionPanel; //Panel where the combo box to select the selection method will be displayed
	private JComboBox<String> selectionComboBox; //Combo box to select the selection method
	
	private JPanel crossoverPanel; //Panel where the combo box to select the crossover method will be displayed
	private JComboBox<String> crossoverComboBox; //Combo box to select the crossover method
	
	private JPanel mutationPanel; //Panel where the combo box to select the mutation method will be displayed
	private JComboBox<String> mutationComboBox; //Combo box to select the mutation method
	
	private JPanel elitismPanel; //Panel where the check box to indicate if the elitism is applied will be displayed
	private JCheckBox elitismCheckBox; //CheckBox to indicate if elitism is applied
	
	private Color background = Color.GRAY;
	
	private int borderSize = 3;

	
	public LeftPanel(JButton menuButton, int width, int height) {
		this.menuButton = menuButton;
		initGUI(width, height);
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
		crossPctgSpinner = new JSpinner(new SpinnerNumberModel(60, 0, 100, 1));
		createSpinnerSection(crossPctgPanel, "Porcentaje de cruces: ", crossPctgSpinner, 60);
		add(crossPctgPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the percentage of mutation
		mutPctgPanel = new JPanel();
		mutPctgSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 100, 1));
		createSpinnerSection(mutPctgPanel, "Porcentaje de mutación: ", mutPctgSpinner, 42);
		add(mutPctgPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the precision of the representation
		repPrecisionPanel = new JPanel();
		repPrecisionSpinner = new JSpinner(new SpinnerNumberModel(0.001, 0, 1, 0.001));
		createSpinnerSection(repPrecisionPanel, "Precision de la representacion: ", repPrecisionSpinner, 2);
		add(repPrecisionPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the selection method
		selectionPanel = new JPanel();
		selectionComboBox = new JComboBox<String>();
		createComboBoxArea(selectionPanel, "Método de Selección: ", selectionComboBox, 13);
		add(selectionPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the crossover method
		crossoverPanel = new JPanel();
		crossoverComboBox = new JComboBox<String>();
		createComboBoxArea(crossoverPanel, "Método de Cruce: ", crossoverComboBox, 33);
		add(crossoverPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));

		//Section to select the mutation method
		mutationPanel = new JPanel();
		mutationComboBox = new JComboBox<String>();
		createComboBoxArea(mutationPanel, "Método de Mutacion: ", mutationComboBox, 12);
		add(mutationPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		
		//Section to select the percentage of elitism
		elitismPanel = new JPanel();
		elitismCheckBox = new JCheckBox();
		createCheckBoxSection(elitismPanel, "Elitismo: ", elitismCheckBox, 0);
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
		elitismPctgSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 100, 1));
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
		repPrecisionPanel.setVisible(visible); 
		selectionPanel.setVisible(visible);
		crossoverPanel.setVisible(visible); 
		mutationPanel.setVisible(visible);
		elitismPanel.setVisible(visible);
	}
	
	public int getBorderSize() {
		return borderSize;
	}
}
