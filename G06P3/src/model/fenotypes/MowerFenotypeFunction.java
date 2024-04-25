package model.fenotypes;

import java.util.ArrayList;
import java.util.Random;

import model.listRep.chromosomes.MowerChromosome;

public class MowerFenotypeFunction extends FenotypeFunction<MowerChromosome>{

	/* GRAMÁTICA
	 * <op> ::= (progn <op> <op>) | (suma <op> <op>) | (salta <op>) | (avanza) | (izquierda) | cte
	 */
	
	private ArrayList<String> mappingStack;
	
	private String resultMapping;
	private Random r;
	
	private ArrayList<ArrayList<String>> productions; //producciones de las reglas
	
	public MowerFenotypeFunction() {
		r = new Random();
		
		mappingStack = new ArrayList<>();
		
		//set the grammar
		productions = new ArrayList<ArrayList<String>>();
		ArrayList<String> p = new ArrayList<String>();
		p.add("progn");
		p.add("op");
		p.add("op");
		productions.add(p);
		p = new ArrayList<String>();
		p.add("suma");
		p.add("op");
		p.add("op");
		productions.add(p);
		p = new ArrayList<String>();
		p.add("salta");
		p.add("op");
		productions.add(p);
		p = new ArrayList<String>();
		p.add("avanza");
		productions.add(p);
		p = new ArrayList<String>();
		p.add("izquierda");
		productions.add(p);
		p = new ArrayList<String>();
		p.add("cte");
		productions.add(p);
	}

	@Override
	public void apply(MowerChromosome c) {
		int allele;
		int result;
		ArrayList<String> elem;
		resultMapping = "";
		mappingStack.add("op");

		int j = 0;
		while(mappingStack.size() != 0) {
			String top = mappingStack.get(mappingStack.size()-1);
			mappingStack.remove(mappingStack.size()-1);
			
			if(top.equals("op") || top.equals("op)")) {
				allele = (int) c.getGene(j).getAllele(0);
				result = allele % productions.size();
				elem = productions.get(result);
				
				if(elem.size() == 1) { // es un terminal
					if(elem.get(0).equals("cte")) {
						resultMapping += "(" + r.nextInt(0,8) + "," + r.nextInt(0,8) + ")";
					}
					
					else {}
					resultMapping += "(" + elem.get(0) + ")";
					if(top.equals("op)")) {
						resultMapping += ")";
					}
				}
				else {
					mappingStack.add(elem.get(elem.size()-1)+")");
					for(int i = elem.size()-2; i >= 0; i--) {
						mappingStack.add(elem.get(i));
					}
				}
				j++;
			}
			else { //es una funcion
				resultMapping += top + "(";
			}
		}
	}

	@Override
	public FenotypeFunction<MowerChromosome> clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
