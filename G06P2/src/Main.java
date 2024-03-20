import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import control.Controller;
import model.GeneticAlgorithm;
import model.chromosomes.AirportChromosome;
import model.chromosomes.RealChromosome;
import model.crossover.CycleCrossover;
import model.crossover.IJCrossover;
import model.crossover.OrderPriorityOrderCrossover;
import model.crossover.OrdinalCodificationCrossover;
import model.crossover.PMXCrossover;
import model.evaluationFunctions.Funcion1;
import model.fenotypes.AirportRepresentation;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.RealRepresentation;
import model.mutation.ExchangeMutation;
import model.mutation.HeuristicMutation;
import model.mutation.IJMutation;
import model.mutation.InsertionMutation;
import model.mutation.InversionMutation;
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
