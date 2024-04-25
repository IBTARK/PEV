import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import control.Controller;
import model.GeneticAlgorithm;
import model.evaluationFunctions.GrammarEvaluation;
import model.listRep.chromosomes.MowerChromosome;
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
		//SUMA (PROGN (PROGN (SUMA(IZQUIERDA),(AVANZA)),(2,7)),(2,7)), (SUMA(AVANZA),(AVANZA))
		/*String text = "suma(suma(salta(suma(suma(salta(0,7))(salta(0,7)))(salta(0,7))))(suma(salta(0,7))(salta(0,7))))(salta(suma(7,4)(4,1)))";
				//fitness 56
		
		GrammarEvaluation ge = new GrammarEvaluation(8,8);
		MowerChromosome c = new MowerChromosome(20, null);
		c.setFenotype(text);
		System.out.println(ge.apply(c));*/
	}
}
