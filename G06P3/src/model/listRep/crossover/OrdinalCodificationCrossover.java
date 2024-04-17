package model.listRep.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.crossover.Crossover;
import model.listRep.chromosomes.Chromosome;
import model.representation.Representation;

/**
 * Class to implement the ordinal codification crossover
 */
public class OrdinalCodificationCrossover implements Crossover{
	
	private Random r;
	private ArrayList<Object> lista;

	public OrdinalCodificationCrossover(int numFlights) {
		r = new Random();
		
		lista = new ArrayList<Object>();
		for(int i = 0; i < numFlights; i++) {
			lista.add((double)i+1);
		}
	}
	
	@Override
	public void cross(Representation co1, Representation co2) {
		Chromosome c1 = (Chromosome) co1;
		Chromosome c2 = (Chromosome) co2;
		
		ArrayList<Integer> cod1 = codificar(c1);
		ArrayList<Integer> cod2 = codificar(c2);
		
		int pos = r.nextInt(0, c1.getChromosomeLength()); //TODO 0 � 1 primera pos. ver PMX
		
		//TODO eliminar
		//pos = 4;
		
		//exchange section
		for(int i = 0; i < pos; i++) {
			int elem = cod1.get(i);
			cod1.set(i, cod2.get(i));
			cod2.set(i, elem);
		}
		
		decodificar(cod1, c1);
		decodificar(cod2, c2);
	}
	
	private ArrayList<Integer> codificar(Chromosome c){
		ArrayList<Object> copia = new ArrayList<Object>();
		ArrayList<Integer> codificado = new ArrayList<Integer>();
		for(int i = 0; i < lista.size(); i++) {
			copia.add(lista.get(i));
		}
		
		for(int i = 0; i < c.getChromosomeLength(); i++) {
			Object a = c.getAllele(i);
			Integer el = (Integer)a;
			int ind = copia.indexOf(el.doubleValue());
			codificado.add(ind+1);
			copia.remove(el.doubleValue());
		}
		return codificado;
	}
	
	private void decodificar(ArrayList<Integer> l, Chromosome c){
		ArrayList<Object> copia = new ArrayList<Object>();
		for(int i = 0; i < lista.size(); i++) {
			copia.add(lista.get(i));
		}
		
		for(int i = 0; i < l.size(); i++) {
			int a = l.get(i)-1;
			Double d = (Double)copia.get(a); //TODO eliminar el casting
			c.setAllele(i, d.intValue());
			copia.remove((Object)d);
		}
	}
}
