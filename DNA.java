package geneticalgo;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class DNA {
	
	public ArrayList<Integer> genes;	
	public int geneLength;
	public int fitness;
	public ArrayList<Integer> target;
	public double mutationRate;

	public DNA() {
		genes = new ArrayList<Integer>();
		geneLength=0;
		fitness=0;
		makeGenes(geneLength);
		mutationRate=0;

	}
	public DNA(int num, double mRate) {
		genes = new ArrayList<Integer>();
		geneLength=num;
		fitness=0;
		makeGenes(geneLength);
		mutationRate=mRate;
	}
	
	public ArrayList<Integer> makeGenes(int val) {
		for(int i=0; i<val;i++) {
			double rand = Math.random()*val;
			genes.add(i, (int)rand);

		}
		System.out.println("genes: "+genes);
		return genes;

	}
	
	public int calcFitness(ArrayList<Integer> t){
		target = t;
		int distance=0;
		int score=0;
		for(int i=0; i<this.genes.size(); i++) {

			if(this.genes.get(i) == target.get(i)) {
				score++;
			}
			
		}
		fitness = score / target.size();
		System.out.println("fitness scores:" + this.fitness);
		return fitness;
	}
	
	public DNA crossover(DNA parent){
		DNA child = new DNA(genes.size(), mutationRate);
		int midpoint = (int) Math.random()*genes.size();
		for(int i=0; i<genes.size(); i++) {
			if(i > midpoint) {
				child.genes.set(i, genes.get(i));
			}
			else 
				child.genes.set(i, parent.genes.get(i));

		}
		return child;
	}
	
	
	public void mutate() {
		for(int i=0; i<genes.size();i++) {
			int rand = (int) Math.random()*genes.size();
			if(rand < mutationRate) 
				genes.set(i, (int)Math.random()*geneLength);
		}
		
	}


}
