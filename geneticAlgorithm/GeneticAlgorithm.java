package geneticAlgorithm;

import probabilityModel.GroupModel;
import probabilityModel.Model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import simulator.CA;
import simulator.IC;
import simulator.ICGenerator;
import simulator.adapters.SimulatorAdapterFactory;

public class GeneticAlgorithm {
	private ExecutorService threadPool = null;
	public GeneticAlgorithm(){
		threadPool = Executors.newFixedThreadPool(100);
	}
	public void run(int generations){
		for(int index =0; index < generations; index++){
			
		}
	}
	public void InterateGeneration(byte radius, byte states, int latticeSize,
			byte dimensions, int populationSize, int parameterSpaceSize, int automataGenerations) throws Exception {
		
		Simulation[] sims = new Simulation[populationSize];
		IC[] ics = new IC[parameterSpaceSize];
		CA[] automata = new CA[populationSize];
		setUpParameterSpace(ics, latticeSize, dimensions, parameterSpaceSize, states);
		setUpPopulation(sims, automata, ics, dimensions, automataGenerations, latticeSize, radius, populationSize, states);
		//System.out.println("QQ");
		for(int index=0; index<populationSize; index++)
		{
			threadPool.submit(sims[index]);
		}
		System.out.println("y");
		threadPool.shutdown();
		threadPool.awaitTermination(1000, TimeUnit.MINUTES);
		System.out.println("x");
		for(int index=0; index<populationSize; index++)
		{
			System.out.println("Thread " + index + " solved  "+
					automata[index].problemsSolved());
		}
	}
	private void setUpPopulation(Simulation[] sims, CA[] automata,IC[] ics, byte dimensions, int automataGenerations,int latticeSize, byte radius, int populationSize, byte states){
		SimulatorAdapterFactory saf = SimulatorAdapterFactory.getInstance();
		for(int index=0;index<populationSize;index++){
			sims[index] = new Simulation();
			automata[index]=new CA(states);
			sims[index].setUp(saf.getSimulator(dimensions), (byte)automataGenerations, latticeSize,
					automata[index], radius, states, ics,index);
			
		}
	}
	private void setUpParameterSpace(IC[] ics, int latticeSize, byte dimensions, int parameterSpaceSize, byte states) throws Exception{
		String arrayName = "";
		for(int index = 0; index<dimensions; index++){
			arrayName+="[";
		}
		arrayName+="B";
		for(int index=0; index< parameterSpaceSize;index++){
			ics[index] = new IC();
			Model probModel = new GroupModel(states,(byte)1,
					new byte[]{1,1,1,1,0});
			ICGenerator icGen = new ICGenerator(probModel);
			byte[] ic = icGen.getIC1D(latticeSize);
			icGen.getClass().getMethod("getIC"+dimensions+"D", int.class).invoke(icGen,latticeSize);
			ics[index].getClass().getMethod("set"+dimensions+"D", Class.forName(arrayName)).invoke(ics[index],ic);
			System.out.println(index);
		}
	}
	public static void main(String[] args) throws Exception{
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ga.InterateGeneration((byte)6,(byte)2,149,(byte)1,100,10000,100);
	}
}
