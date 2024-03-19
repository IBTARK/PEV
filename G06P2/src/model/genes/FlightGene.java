package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.airport.FlightType;
import model.fenotypes.FenotypeFunction;

public class FlightGene extends Gene{

	private String id;
	private FlightType type;
	private ArrayList<Integer> tels;
	
	
	public FlightGene(int numFlight, String id, FlightType type, ArrayList<Integer> tels, FenotypeFunction fenotypeFunction) {
		super(1, fenotypeFunction);
		alleles.add(numFlight);
		this.id  = id;
		this.type = type;		
		this.tels = tels;
	}
	
	/**
	 * Returns the allele of the gene
	 */
	public Integer getAllele() {
		return (int) alleles.get(0);
	}
	
	public String getId(){
		return id;
	}
	
	public FlightType getType() {
		return type;
	}
	
	public int getTel(int pista){
		return tels.get(pista);
	}
	
	public ArrayList<Integer> getTels(){
		return tels;
	}
	
	public void setAllele(int numFlight){
		alleles.set(0, numFlight);
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setType(FlightType type) {
		this.type = type;
	}
	
	public void setTel(int pista, int tel){
		tels.set(pista, tel);
	
	}
	
	public void setTels(ArrayList<Integer> tels){
		this.tels = tels;
	}

	@Override
	public Double initializeGeneRandom(Random random) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean valid() {
		return true;
	}

	@Override
	public Gene clone() {
		ArrayList<Integer> newTel = new ArrayList<Integer>();
		for(int i = 0; i < tels.size(); i++) {
			newTel.add(Integer.valueOf(tels.get(i)));
		}
		
		FlightGene clone = new FlightGene(Integer.valueOf((int) alleles.get(0)), new String(id), type, newTel, fenotypeFunction.clone());
		
		return clone;
	}
	
}
