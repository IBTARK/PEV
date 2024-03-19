package model.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.chromosomes.Chromosome;

/**
 * Class to implement the ordinal codification crossover
 */
public class OrdinalCodificationCrossover implements Crossover{
	
	private Random r;
	private ArrayList<Object> lista;

	public OrdinalCodificationCrossover(Chromosome c) {
		r = new Random();
		
		//lista = c.getAlleles(0, c.getChromosomeLength());
		//TODO ordenar según criterio
		//TODO eliminar
		lista = new ArrayList<Object>();
		lista.add(1.0);
		lista.add(2.0);
		lista.add(3.0);
		lista.add(4.0);
		lista.add(5.0);
		lista.add(6.0);
		lista.add(7.0);
		lista.add(8.0);
		lista.add(9.0);
	}
	
	@Override
	public void cross(Chromosome c1, Chromosome c2) {
		ArrayList<Integer> cod1 = codificar(c1);
		ArrayList<Integer> cod2 = codificar(c2);
		
		int pos = r.nextInt(0, c1.getChromosomeLength()); //TODO 0 ó 1 primera pos. ver PMX
		
		//TODO eliminar
		pos = 4;
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
			int ind = copia.indexOf((Double)a);
			codificado.add(ind+1);
			copia.remove((Object)a);
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
			c.setAllele(i, d);
			copia.remove((Object)d);
		}
	}
}
