package view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainWindow extends JFrame{
	
	private int width = 900;
	private int height = 625;
	
	private MainPanel mainPanel;
	
	public MainWindow(){
		super("Algoritmo Genético Simple");
		
		initGUI();
	}
	
	private void initGUI() {
		
		//The main panel is created an set
		mainPanel = new MainPanel(width, height);
		setContentPane(mainPanel);
		
		setVisible(true);
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
