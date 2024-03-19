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
		/*GeneticAlgorithm alg = new GeneticAlgorithm();
		
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
		});*/
		 
		//read the files
		int problem = 12;
		String fileF = ".\\src\\datos";
		String fileT = ".\\src\\datos";
		//if(caso 1) {
			fileF += "\\vuelos1.txt";
		//}
		//else if(caso 2){
			fileT += "\\TEL1.txt";
		/*}
		else if(caso 3){
			fileF += "\\vuelos2.txt";
		}
		else {
			fileT += "\\TEL2.txt";
		}*/
		
		//read flights
		int i = 1;
		HashMap<Integer, ArrayList<String>> flights = new HashMap<Integer, ArrayList<String>>();
        
		File file = new File(fileF);
        Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] elements = line.split("\t");
            
            ArrayList<String> elems = new ArrayList<String>();
            elems.add(elements[0]);
            elems.add(elements[1]);
            flights.put(i, elems);
            i++;
        }
        scanner.close();
        
        //read tels
        HashMap<Integer, ArrayList<Integer>> tels = new HashMap<Integer, ArrayList<Integer>>();
		file = new File(fileT);
        scanner = null;
        for(int j = 0; j < problem; j++) {
        	ArrayList<Integer> elems = new ArrayList<Integer>();
        	tels.put(j, elems);
        }
        
        try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        while (scanner.hasNextLine()) {
        	String line = scanner.nextLine();
            String[] elems = line.split("\t");
            for(int j = 0; j < problem; j++) {
            	tels.get(j).add(Integer.valueOf(elems[j].strip()));
            }
        }
        scanner.close();
        
        ArrayList<FenotypeFunction> fenotypes = new ArrayList<FenotypeFunction>();
		fenotypes.add(new AirportRepresentation(1.0, Double.valueOf(problem)));
		
        AirportChromosome airportChromosome = new AirportChromosome(flights, tels, fenotypes);
        AirportChromosome airportChromosome1 = new AirportChromosome(flights, tels, fenotypes);
		/*ArrayList<Integer> lengths = new ArrayList<Integer>();
		lengths.add(1);
		//ArrayList<FenotypeFunction> fenotypes = new ArrayList<FenotypeFunction>();
		fenotypes.add(new RealRepresentation(1.0, 9.0));
		
		RealChromosome c1 = new RealChromosome(9, lengths , fenotypes), c2 = new RealChromosome(9, lengths , fenotypes);
		
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
		
		IJMutation ij = new IJMutation();
		
		ij.mutate(c2);*/
        ArrayList<Object> values2 = new ArrayList<Object>();
		values2.add(4);
		values2.add(5);
		values2.add(2);
		values2.add(1);
		values2.add(8);
		values2.add(7);
		values2.add(6);
		values2.add(9);
		values2.add(3);
		values2.add(10);
		values2.add(11);
		values2.add(12);
		
        airportChromosome1.replaceAlleles(values2, 0, 11);
        IJCrossover im = new IJCrossover();
		
		im.cross(airportChromosome, airportChromosome1);
		
		System.out.print("Hola");
	}
}
