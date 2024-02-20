package model.chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.fenotypes.FenotypeFunction;
import model.fitnessFunctions.FitnessFunction;
import model.genes.Gene;

/**
 * Class to represent a generic chromosome
 */
public abstract class Chromosome implements Comparable<Chromosome>{
	protected List<Gene> genes; //List of genes that form the chormosome
	protected List<FenotypeFunction> genesFenotypesFunctions; //List of functions to compute the fenotypes of each gene of the chromosome
	
	protected ArrayList<Double> genesFenotypes; //List of doubles that represent the fenotypes of the genes of the chromosome (list of the fenotypes of the genes that form the chromosome)
	protected Random random;
	
	protected int numGenes; //number of genes
	protected int chromosomeLength;
	protected List<Integer> genesLengths;
	
	protected double fitness; //fitness of the chromosome
	protected double score; //relative score (fitness_i/fitness_Total)
	protected double scoreAccumulated; //accumulated relative score
	
	public Chromosome() {
		
	}
	
	public Chromosome(int numGenes,  List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions) {
		this.numGenes = numGenes;
		this.genesFenotypesFunctions = genesFenotypesFunctions;
		this.genesLengths = genesLengths;
		
		random = new Random();
		
		genes = new ArrayList<Gene>();
		genesFenotypes = new ArrayList<Double>();
		
		//Compute the size of the chormosome
		for(int geneLength : genesLengths){
			chromosomeLength += geneLength;
		}
	}
	
	/**
	 * Method that initializes each gene of the chormosome randomly
	 */
	public void initializeChromosomeRandom() {
		for(int i = 0; i < numGenes; i++) {
			genes.get(i).initializeGeneRandom(random);
			genesFenotypes.add(genes.get(i).computeFenotype(genesFenotypesFunctions.get(i)));
		}
	}
	
	/**
	 * 
	 * @return list with the fenotypes of each gene of the chromosome
	 */
	private void computeGenesFenotypes(){
		for(int i = 0; i < numGenes; i++) {
			genesFenotypes.set(i, genes.get(i).computeFenotype(genesFenotypesFunctions.get(i)));
		}
	}
	
	/**
	 * Compute the fitness of the chormosome
	 * 
	 * @param fitnessFunction function to compute the fitness of the chormosome
	 */
	public void computeFitness(FitnessFunction fitnessFunction) {
		//Compute the fenotypes of the genes
		computeGenesFenotypes();
		fitness = fitnessFunction.apply(this);
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
			
			for(Gene gene : genes) {
				for(int i = 0; i < gene.getGeneLength(); i++) {
					if(posChromosome >= start && posChromosome <= end) {
						oldAlleles.add(gene.setAllele(i, alleles.get(posList)));
						posList += 1;
					}
					posChromosome += 1;
				}
			}
		}
		
		return oldAlleles;
	}
//*********************************************************************************
// Comparable
	
	public int compareTo(Chromosome c) {
		if(fitness < c.getFitness()) return -1;
		else if(fitness > c.getFitness()) return 1;
		else return 0;
	}
	
//*********************************************************************************
// Getters
	
	/**
	 * 
	 * @return value of the fitness of the chromosome
	 */
	public Double getFitness() {
		return fitness;
	}
	
	/**
	 * 
	 * @return value of the score of the chromosome
	 */
	public Double getScore() {
		return score;
	}
	
	/**
	 * 
	 * @return value of the accumulated score of the chromosome
	 */
	public Double getScoreAccumulated() {
		return scoreAccumulated;
	}
	
	/**
	*
	* @return value of the genes length
	*/
	public List<Gene> getGenes(){
		return genes;
	}
	
	/**
	*
	* @return value of the requested gen
	*/
	public Gene getGene(int index) {
		return genes.get(index);
	}
	
	/**
	*
	* @return fenotype of a gen in a determined position
	*/
	public Double getGeneFenotype(int index) {
		return genesFenotypes.get(index);
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
	* @return a list with the fenotype function of each gene of the chromosome
	*/
	public List<FenotypeFunction> getGenesFenotypesFunctions(){
		return genesFenotypesFunctions;
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
	

//*********************************************************************************
// Setters
	
	public void setGenes(ArrayList<Gene> genes) {
		this.genes = genes;
	}
	
	public void setGenesFenotypes(ArrayList<Double> genesFenotypes) {
		this.genesFenotypes = genesFenotypes;
	}
	
	public void setFitness(Double fitness) {
		this.fitness = fitness;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}
	
	public void setScoreAccumulated(Double scoreAccumulated) {
		this.scoreAccumulated = scoreAccumulated;
	}
	
	/**
	 * Given an index, the allele in that position is replaced for newAllele (if the modification generates an invalid gene the modification is reverted)
	 * 
	 * @param index of the newAllele
	 * @param newAllele to be set
	 */
	public void setAllele(int index, Object newAllele) {
		if(index > 0 && index < chromosomeLength) {
			int pos = 0;
			
			for(Gene gene : genes) {
				for(int i = 0; i < gene.getGeneLength(); i++) {
					if(pos == index) {
						gene.setAllele(i, newAllele);
						return;
					}
					pos++;
				}
			}
		}
	}
	
}
