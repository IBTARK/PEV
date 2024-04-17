import model.treeRep.crossover.TreeCrossover;
import model.treeRep.mutation.FunctionalMutation;
import model.treeRep.mutation.PermutationMutation;
import model.treeRep.mutation.SubTreeMutation;
import model.treeRep.mutation.TerminalMutation;
import model.treeRep.symbols.MowerSymbols;
import model.treeRep.symbols.Symbols;
import model.treeRep.trees.MowerTree;
import model.treeRep.trees.TreeChromosome;

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
		
		Symbols s = new MowerSymbols(8, 8);
		
		TreeChromosome t1 = new MowerTree(null, s, 2, 5);
		TreeChromosome t2 = new MowerTree(null, s, 2, 5);
		
		t1.fullInitialization(3);
		t2.growInitialization(3);
		
		TreeCrossover tc = new TreeCrossover(0.9);
		
		tc.cross(t1, t2);
		
		FunctionalMutation fm = new FunctionalMutation();
		fm.mutate(t1);
		
		TerminalMutation tm = new TerminalMutation();
		tm.mutate(t2);
		
		PermutationMutation pm = new PermutationMutation();
		pm.mutate(t1);
		
		SubTreeMutation sm = new SubTreeMutation();
		sm.mutate(t2);
		
		System.out.print("Hola");
	}
}
