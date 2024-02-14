package view.auxPanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class LeftPanel extends JPanel{
	
	private JButton menuButton;
	
	private JSpinner popSizeSpinner; //Spinner to select the population size
	private JSpinner numGenSpinner; //Spinner to select the number of generations
	private JSpinner crossPctgSpinner; //Spinner to select the percentage of crossover
	private JSpinner mutPctgSpinner; //Spinner to select the percentage of mutation
	private JSpinner repPrecisionSpinner; //Spinner to select the precision of the representation
	private JSpinner elitismPctgSpinner; //Spinner to select the percentage of elitism
	
	private JComboBox<String> selectionComboBox; //Combo box to select the selection method
	private JComboBox<String> crossoverComboBox; //Combo box to select the crossover method
	private JComboBox<String> mutationComboBox; //Combo box to select the mutation method
	
	private JCheckBox elitismCheckBox; //CheckBox to indicate if elitism is applied
	
	private Color background = Color.GRAY;
	
	private int borderSize = 3;
	
	public LeftPanel(int width, int height) {
		initGUI(width, height);
	}
	
	private void initGUI(int width, int height) {
		this.setLayout(new GridLayout(11,0));
		
		//The menu button is created and added to the panel
		menuButton = new JButton();
		menuButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons/menu.png")));
		menuButton.setText(null);
		menuButton.setMinimumSize(new Dimension(20, 20));
		menuButton.setOpaque(false);
		menuButton.setContentAreaFilled(false);
		menuButton.setBorderPainted(false);
		menuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(menuButton);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the population size
		SpinnerNumberModel model = new SpinnerNumberModel(100, 2, 100000, 1);
		add(createSpinnerSection("Tamaño de la población: ", popSizeSpinner, model));
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the number of generations
		model = new SpinnerNumberModel(100, 1, 100000, 1);
		add(createSpinnerSection("Número de generaciones: ", numGenSpinner, model));
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the percentage of crossover
		model = new SpinnerNumberModel(60, 0, 100, 1);
		add(createSpinnerSection("Porcentaje de cruces: ", crossPctgSpinner, model));
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the percentage of mutation
		model = new SpinnerNumberModel(5, 0, 100, 1);
		add(createSpinnerSection("Porcentaje de mutación: ", mutPctgSpinner, model));
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the precision of the representation
		model = new SpinnerNumberModel(0.001, 0, 1, 0.001);
		add(createSpinnerSection("Precision de la representacion: ", repPrecisionSpinner, model));
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the selection method
		add(createComboBoxArea("Método de Selección: ", selectionComboBox));
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the crossover method
		add(createComboBoxArea("Método de Cruce: ", crossoverComboBox));
		add(Box.createRigidArea(new Dimension(0, 30)));

		//Section to select the mutation method
		add(createComboBoxArea("Método de Mutacion: ", mutationComboBox));
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//Section to select the percentage of elitism		
		JPanel elitismPanel = new JPanel();
		elitismPanel.setLayout(new BoxLayout(elitismPanel, BoxLayout.Y_AXIS));
		JPanel checkBoxPanel = new JPanel();
		elitismCheckBox = new JCheckBox();
		checkBoxPanel.add(createLabel("Elitismo"));
		checkBoxPanel.add(elitismCheckBox);
		checkBoxPanel.setBackground(background);
		elitismPanel.add(checkBoxPanel);
		model = new SpinnerNumberModel(40, 0, 100, 1);
		elitismPanel.add(createSpinnerSection("Percentage: ", elitismPctgSpinner, model));
		elitismPanel.setBackground(background);
		add(elitismPanel);
		add(Box.createRigidArea(new Dimension(0, 30)));
		
		//The background and a border is set
		setBackground(background);
		setBorder(BorderFactory.createMatteBorder(borderSize, borderSize, borderSize, borderSize, Color.BLACK));
		
		setMinimumSize(new Dimension(width - borderSize, height - borderSize));
		setPreferredSize(new Dimension(width - borderSize, height - borderSize));
	}
	
	/**
	 * Create a panel with a label and a spinner
	 * 
	 * @param text string of the label
	 * @return panel with the label and a spinner
	 */
	private JPanel createSpinnerSection(String text, JSpinner spinner, SpinnerNumberModel model) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		//label
		JLabel label = createLabel(text);
		panel.add(label);
		
		//spinner
		spinner = new JSpinner(model);
		spinner.setPreferredSize(new Dimension(70, 20));
		spinner.setMaximumSize(new Dimension(70, 20));
		spinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(spinner);
		
		//Panel properties
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panel.setBackground(Color.BLUE);
		
		return panel;
	}
	
	/**
	 * Crates a panel with a label and a combo box
	 * @param text text text that will be displayed in the label
	 * @param comboBox
	 * @return panel with a label and a combo box
	 */
	private JPanel createComboBoxArea(String text, JComboBox<String> comboBox) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		//label
		JLabel label = createLabel(text);
		panel.add(label);
		
		//text field
		comboBox = new JComboBox<String>();
		comboBox.setPreferredSize(new Dimension(100, 20));
		comboBox.setMaximumSize(new Dimension(100, 20));
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(comboBox);
		
		//Panel properties
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panel.setBackground(background);
		
		return panel;
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
}
