import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import control.Controller;
import model.GeneticAlgorithm;
import view.MainWindow;

public class Main {
	public static void main(String[] args) {
		GeneticAlgorithm alg = new GeneticAlgorithm();
		
		Controller ctr = new Controller(alg);
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new MainWindow(ctr);
				} 
				catch (Exception e) 
				{
					System.out.println(e);
				}
				
			}
		});
		
	}
}
