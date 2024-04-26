package model.listRep.chromosomes;

import java.util.ArrayList;
import java.util.List;

import model.fenotypes.FenotypeFunction;
import model.listRep.genes.Gene;
import model.listRep.genes.MowerGene;
import resources.Pair;

public class MowerChromosome extends Chromosome{
	
	private String fenotype; 
	
	private ArrayList<Pair<Integer, Integer>> path;
	private ArrayList<Integer> orientationPath;
	private ArrayList<ArrayList<Boolean>> garden;
	

	public MowerChromosome(int numGenes, FenotypeFunction fenotype) {
		super(numGenes, 1, fenotype);
		
		for(int i = 0; i < numGenes; i++) {
			genes.add(new MowerGene());
		}
	}

	@Override
	public MowerChromosome clone() {
		int cloneNumGenes = Integer.valueOf(numGenes);
		
		//Deep copy of the genesLengths and genesFenotypesFunctions lists
		ArrayList<Gene> cloneGenes = new ArrayList<Gene>();
		for(int i = 0; i < numGenes; i++) {
			cloneGenes.add(genes.get(i).clone());
		}
		
		MowerChromosome clone = new MowerChromosome(cloneNumGenes, fenotypeFunction.clone());
		
		//Set the genes and their fenotypes
		clone.setGenes(cloneGenes);
		
		//Set of the fitness, score and scoreAccumulated
		clone.setFenotype(new String(fenotype));
		clone.setEvaluation(Double.valueOf(evaluation));
		clone.setFitness(Double.valueOf(fitness));
		clone.setScore(Double.valueOf(score));
		clone.setScoreAccumulated(Double.valueOf(scoreAccumulated));
		
		ArrayList<Pair<Integer, Integer>> newPath = new ArrayList<Pair<Integer, Integer>>();
		ArrayList<Integer> newOrientationPath = new ArrayList<Integer>();
		for(Pair<Integer, Integer> pos : path) {
			newPath.add(new Pair<Integer, Integer>(pos.getFirst(), pos.getSecond()));
		}
		for(int i = 0; i < orientationPath.size(); i++) {
			newOrientationPath.add(orientationPath.get(i));
		}
		
		clone.setPath(newPath);
		clone.setOrientationPath(newOrientationPath);
		
		return clone;
	}
	
//----GETTERS----
	/**
	*
	* @return value of the genes length
	*/
	public List<Gene> getGenes(){
		return genes;
	}
	
	/**
	*
	* @return value of the genes fenotype
	*/
	public String getFenotype(){
		return fenotype;
	}
	
	/**
	*
	* @return value of the requested gene
	*/
	public Gene getGene(int index) {
		return genes.get(index);
	}
	
	/**
	*
	* @return fenotype of a gene in a determined position
	*/
	public Double getGeneFenotype(int index) {
		return genes.get(index).getFenotype();
	}
	
	/**
	*
	* @return the number of genes of the chormosome
	*/
	public int getNumGenes(){
		return numGenes;
	}
	
	/**
	*
	* @return the number of genes of the chormosome
	*/
	public int getChromosomeLength(){
		return chromosomeLength;
	}
	
	/**
	*
	* @return a list with the number of alleles of each gene of the chromosome
	*/
	public List<Integer> getGenesLengths(){
		return genesLengths;
	}
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<Object> getAlleles(int start, int end) {
		ArrayList<Object> alleles = null;
		
		if(start >= 0 && start < chromosomeLength && end >= start && end >= 0 && end < chromosomeLength) {
			int pos = 0;
			alleles = new ArrayList<Object>();
			
			for(Gene gene : genes) {
				for(int i = 0; i < gene.getGeneLength(); i++) {
					if(pos >= start && pos <= end) {
						alleles.add(gene.getAllele(i));
					}
					pos += 1;
				}
			}
		}
		
		return alleles;
	}
	
	public Object getAllele(int index){
		if(index >= 0 && index < chromosomeLength) {
			int pos = 0;
			
			for(Gene gene : genes) {
				for(int i = 0; i < gene.getGeneLength(); i++) {
					if(pos == index) {
						return gene.getAllele(i);
					}
					pos++;
				}
			}
		}
		
		return null;
	}
	
	public ArrayList<Pair<Integer, Integer>> getPath(){
		return path;
	}
	
	public ArrayList<Integer> getOrientationPath(){
		return orientationPath;
	}
	
	public ArrayList<ArrayList<Boolean>> getGarden(){
		return garden;
	}
	
//----SETTERS----
	public void setPath(ArrayList<Pair<Integer, Integer>> path){
		this.path = path;
	}
	
	public void setOrientationPath(ArrayList<Integer> orientationPath){
		this.orientationPath = orientationPath;
	}
	
	public void setGarden(ArrayList<ArrayList<Boolean>> garden) {
		this.garden = garden;
	}
	
	public void setFenotype(String fenotype){
		this.fenotype = fenotype;
	}
	
	public void setGenes(ArrayList<Gene> genes) {
		numGenes = genes.size();
		this.genes = genes;
	}
	
	public void setAllele(int index, Object newAllele) {
		if(index >= 0 && index < chromosomeLength) {
			int pos = 0;
			
			for(int j = 0; j < numGenes; j++) {
				for(int i = 0; i < genes.get(j).getGeneLength(); i++) {
					if(pos == index) {
						genes.get(j).setAllele(i, newAllele);//The new fenotype is computed
						return;
					}
					pos++;
				}
			}
		}
	}

}
