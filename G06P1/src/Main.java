import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import control.Controller;
import model.GeneticAlgorithm;
import model.chromosomes.Chromosome;
import model.chromosomes.ChromosomeType;
import model.crossover.BLXAlphaCrossover;
import model.crossover.Crossover;
import model.evaluationFunctions.Michalewicz;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.RealRepresentation;
import model.mutation.GenericMutation;
import model.mutation.Mutation;
import model.selection.Selection;
import model.selection.TruncationSelection;
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
