public class main {
	public static void main(String args[]){
		Population one = new Population(10, 100, 10);
		one.createPop();
		for(int i=0; i<one.population.size();i++) {
			one.calcFitness(one.population.get(i));
		}
		one.naturalSelection();
		one.generate();
		one.mutate();
	}

}
