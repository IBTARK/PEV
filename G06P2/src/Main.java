import java.util.ArrayList;

import model.chromosomes.RealChromosome;
import model.crossover.OrderCrossover;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.RealRepresentation;

public class Main {
	public static void main(String[] args) {
		/*
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
		*/
		
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		lengths.add(1);
		ArrayList<FenotypeFunction> fenotypes = new ArrayList<FenotypeFunction>();
		fenotypes.add(new RealRepresentation(1.0, 9.0));
		
		RealChromosome c1 = new RealChromosome(9,lengths , fenotypes), c2 = new RealChromosome(9,lengths , fenotypes);
		
		ArrayList<Object> values1 = new ArrayList<Object>();
		values1.add(1.0);
		values1.add(2.0);
		values1.add(3.0);
		values1.add(4.0);
		values1.add(5.0);
		values1.add(6.0);
		values1.add(7.0);
		values1.add(8.0);
		values1.add(9.0);
		
		ArrayList<Object> values2 = new ArrayList<Object>();
		values2.add(4.0);
		values2.add(5.0);
		values2.add(2.0);
		values2.add(1.0);
		values2.add(8.0);
		values2.add(7.0);
		values2.add(6.0);
		values2.add(9.0);
		values2.add(3.0);
		
		c1.initializeChromosomeRandom();
		c2.initializeChromosomeRandom();
		
		c1.replaceAlleles(values1, 0, 8);
		c2.replaceAlleles(values2, 0, 8);
		
		OrderCrossover oc = new OrderCrossover();
		
		oc.cross(c1, c2);
		
		System.out.print("Hola");
	}
}
