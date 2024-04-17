package model.fenotypes;

import model.treeRep.symbols.Symbol;
import model.treeRep.trees.TreeChromosome;
import model.treeRep.trees.TreeNode;

public class TreeFenotypeFunction extends FenotypeFunction<TreeChromosome>{

	private String fenotype;
	
	public TreeFenotypeFunction() {
		
	}
	
	
	@Override
	public void apply(TreeChromosome t) {
		fenotype = "";
		
		visitNode(t.getRoot());
		
		t.setFenotype(fenotype);
	}

	
	private void visitNode(TreeNode<Symbol> n) {
		if(n.getSymbol().getArity() == 0) { //leaf
			if(n.getSymbol().getSymbol() == "CONSTALEAT") {
				fenotype += "(" + n.getSymbol().getCol() + "," + n.getSymbol().getRow() + ")";
			}
			else {
				fenotype += "(" +  n.getSymbol().getSymbol() + ")";
			}
		}
		else {
			fenotype +=  " (" + n.getSymbol().getSymbol();
			visitNode(n.getChildren().get(0));
			if(n.getSymbol().getArity() == 2) {//Functions of arity 2
				fenotype += ",";
				visitNode(n.getChildren().get(1));
			}
			fenotype += ")";
		}
	}

	@Override
	public FenotypeFunction<TreeChromosome> clone() {
		return new TreeFenotypeFunction();
	}

}
