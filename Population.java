import java.util.*;
import java.lang.Math;


public class Population extends DNA{
	public static int populationSize;
	public ArrayList<ArrayList<Integer> > population;
	public ArrayList<ArrayList<Integer>> matingPool;
	private ArrayList<ArrayList<Integer>> highFitness;
	private ArrayList<ArrayList<Integer>> lowFitness;
	public int generations;
	
	private DNA parentA;
	private DNA parentB;

	public Population(int num, int size, double mRate) {
		super(num,  mRate);
		population = new ArrayList<ArrayList<Integer>>();
		populationSize = size;
		matingPool = new ArrayList<ArrayList<Integer>>();
		highFitness = new ArrayList<ArrayList<Integer>>();
		lowFitness = new ArrayList<ArrayList<Integer>>();
	}

	
	public ArrayList<ArrayList<Integer>> createPop(){
		for(int i=0;i<population.size(); i++) {
			population.set(i, super.makeGenes(geneLength));

		}
		System.out.println("population: "+population);
		return population;
		
	}

	public void calcPopFitness(ArrayList<Integer> x) {
			calcFitness(x);
	}

	public void naturalSelection() {
		int maxFit=0;

		//set maxfit
		for(int i=0; i<population.size();i++) {
			if(fitness > maxFit) 
				maxFit = this.fitness;

		}

		//mating pool
		for(int i = 0; i < this.population.size()-1; i++) {

			int distance = (int) Math.sqrt( ( Math.abs(this.genes.get(i) - target.get(i)) * Math.abs(this.genes.get(i) - target.get(i)) + this.genes.get(i+1) - target.get(i+1) * this.genes.get(i+1) - target.get(i+1)) );
			int n = (int)Math.floor(distance*100);
			for (int j = 0; j < n; j++) {
				matingPool.add(this.population.get(i));

			}
		}

		int count=0;
		for(int i=0; i<matingPool.size();i++) {
			if(this.genes.get(i) == this.target.get(i)) {
				count++;
			}
			if(count>=geneLength/2)
				highFitness.add(this.genes);
			else
				lowFitness.add(this.genes);
		}

	}

	public void generate() {
		for(int i=0; i<population.size(); i++) {
			population.set(i, null);

		}
		for(int j=0; j<population.size();j++) {
			population.add(matingPool.get(j));
			//random # to child
			int a = (int)Math.random()*highFitness.size();
			int b = (int)Math.random()*highFitness.size();

			parentA = new DNA(matingPool.get(a).get(a), mutationRate);
			parentB = new DNA(matingPool.get(b).get(b), mutationRate);

			DNA child = new DNA();
			child = parentA.crossover(parentB);
			population.set(j, child.genes);

		}

		this.generations++;

	}

	public int evaluate() {
		boolean finished;
		int record=0;
		int index=0;
		for(int i=0; i<population.size(); i++) {
			if(calcFitness(population.get(i)) > record) {
				index=i;
				record = calcFitness(population.get(i));
			}

		}
		return record;

	}

	public void runAll() {
		createPop();
		for(int i=0; i<population.size();i++) {
			calcFitness(population.get(i));
		}
		naturalSelection();
		generate();
		//parentA.crossover(parentB);
		mutate();
		
		
	}



}
