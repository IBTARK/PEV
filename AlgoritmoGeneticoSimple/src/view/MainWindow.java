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
		mainPanel = new MainPanel(width, height - 36);
		setContentPane(mainPanel);
		
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    pack(); // Ajusta el tamaño del marco para que se ajuste a su contenido
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    setVisible(true); // Hacer visible el marco después de configurar todo
	}
}
