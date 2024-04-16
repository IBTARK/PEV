package model.listRep.chromosomes;

import java.util.ArrayList;
import java.util.List;

import model.Representation;
import model.evaluationFunctions.EvaluationFunction;
import model.listRep.fenotypes.FenotypeFunction;
import model.listRep.genes.Gene;

/**
 * Class to represent a generic chromosome
 */
public abstract class Chromosome extends Representation{
	protected List<Gene> genes; //List of genes that form the chormosome	
	
	protected int numGenes; //number of genes
	protected int chromosomeLength;
	protected List<Integer> genesLengths;
	
	public Chromosome(int numGenes,  List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions) {
		this.numGenes = numGenes;
		this.genesLengths = genesLengths;
		
		genes = new ArrayList<Gene>();
		
		//Compute the size of the chormosome
		chromosomeLength = genesLengths.get(0);
		
		if(genesFenotypesFunctions.size() == 1 && genesLengths.size() == 1 && numGenes > 1) { //All the genes have the same fenotype function
			for(int i = 1; i < numGenes; i++){
				chromosomeLength += genesLengths.get(0);
				genesLengths.add(Integer.valueOf(genesLengths.get(0)));
				genesFenotypesFunctions.add(genesFenotypesFunctions.get(0).clone());
			}
		}
		else { // Not all the genes have the same fenotype function
			for(int i = 1; i < numGenes; i++){
				chromosomeLength += genesLengths.get(i);
			}
		}
		
	}
	
	public Chromosome(int numGenes,  int genesLengths, List<FenotypeFunction> genesFenotypesFunctions) {
		this.numGenes = numGenes;
		this.genesLengths = new ArrayList<Integer>();
		this.genesLengths.add(genesLengths);
		
		genes = new ArrayList<Gene>();
		
		//Compute the size of the chormosome
		chromosomeLength = genesLengths;
		
		if(genesFenotypesFunctions.size() == 1 && numGenes > 1) { //All the genes have the same fenotype function
			for(int i = 1; i < numGenes; i++){
				chromosomeLength += genesLengths;
				this.genesLengths.add(Integer.valueOf(genesLengths));
				genesFenotypesFunctions.add(genesFenotypesFunctions.get(0).clone());
			}
		}
		else { // Not all the genes have the same fenotype function
			for(int i = 1; i < numGenes; i++){
				chromosomeLength += genesLengths;
			}
		}
		
	}
	
	/**
	 * Method that initializes each gene of the chromosome randomly
	 */
	public void initializeChromosomeRandom() {
		for(Gene g : genes) {
			g.initializeGeneRandom(random);
		}
	}
	
	/**
	 * 
	 * @return list with the fenotypes of each gene of the chromosome
	 */
	private void computeGenesFenotypes(){
		for(Gene g : genes) {
			g.computeFenotype();
		}
	}
	
	/**
	 * Compute the evaluation function of the chormosome (the fitness is also set to the same value as the evaluation)
	 * 
	 * @param evaluationFunction function to compute the evaluation of the chormosome
	 */
	public Double computeEvaluation(EvaluationFunction evaluationFunction) {
		//Compute the fenotypes of the genes
		computeGenesFenotypes();
		evaluation = evaluationFunction.apply(this);
		fitness = Double.valueOf(evaluation);
		
		return evaluation;
	}
	
	/**
	 * Compute the score and the accumulated score
	 * 
	 * @param totalFitness total fitness of the population
	 * @param previousAccumulated accumulated score of the previous choromosome of the population
	 */
	public void computeScoreAndAccumulated(Double totalFitness, Double previousAccumulated) {
		score = fitness / totalFitness;
		scoreAccumulated = previousAccumulated + score; 
	}
	
	/**
	 * Given a list of alleles and two indexes (start and end), the alleles indicated by the indexes are replaced by the given alleles
	 * 
	 * @param alleles list of alleles to replace
	 * @param start index of the first allele of the chromosome that has to be replaced
	 * @param end index of the last allele of the chromosome that has to be replaced
	 * 
	 * @return the alleles that have been replaced
	 */
	public ArrayList<Object> replaceAlleles(ArrayList<Object> alleles, int start, int end) {
		ArrayList<Object> oldAlleles = null;
		
		if(start >= 0 && start < chromosomeLength && end >= start && end >= 0 && end < chromosomeLength) {
			int posChromosome = 0, posList = 0;
			oldAlleles = new ArrayList<Object>();
			
			for(int j = 0; j < numGenes; j++) {
				for(int i = 0; i < genes.get(j).getGeneLength(); i++) {
					if(posChromosome >= start && posChromosome <= end) {
						oldAlleles.add(genes.get(j).setAllele(i, alleles.get(posList)));//The new fenotype of the gene is updated
						posList += 1;
					}
					posChromosome += 1;
				}
			}
		}
		
		return oldAlleles;
	}
	
//*********************************************************************************
// Cloneable
	public abstract Chromosome clone();
	

//*********************************************************************************
// Getters
	
	/**
	*
	* @return value of the genes length
	*/
	public List<Gene> getGenes(){
		return genes;
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
	
	/**
	 * 
	 * @param allele
	 * @return index of the first instance of allele in the chromosome (-1 if it doesnt exist)
	 */
	public int indexOf(Object allele) {
		
		int pos = 0;
		
		for(Gene g : genes) {
			for(int i = 0; i < g.getGeneLength(); i++) {
				pos++;
				if(g.getAllele(i).equals(allele)) {
					return pos;
				}
			}
		}
		
		return -1;
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
	

//*********************************************************************************
// Setters
	
	/**
	 * Sets the genes of the chromosomes updating the genesFenotypes list and the number of genes.
	 * 
	 * @param genes list of genes
	 */
	public void setGenes(ArrayList<Gene> genes) {
		numGenes = genes.size();
		this.genes = genes;
	}
	
	/**
	 * Given an index, the allele in that position is replaced for newAllele (if the modification generates an invalid gene the modification is reverted)
	 * Updates the fenotypes of the genes.
	 * 
	 * @param index of the newAllele
	 * @param newAllele to be set
	 */
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
