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
	public void fork() throws Exception {
		byte radius = 3;
		byte states = 2;
		SimulatorAdapterFactory saf = SimulatorAdapterFactory.getInstance();
		Simulation[] sims = new Simulation[100];
		IC[] ics = new IC[10000];
		Model probModel = new GroupModel(2, 3);
		ICGenerator icGen = new ICGenerator(probModel);
		CA[] automata = new CA[100];
		for(int index=0; index<10000;index++){
			ics[index] = new IC();
			byte[] ic = icGen.getIC1D(149);
			ics[index].set1D(ic);
			
		}
		for(int index=0;index<100;index++){
			sims[index] = new Simulation();
			automata[index]=CA.newInstance();
			sims[index].setUp(saf.getSimulator(1), (byte)100, 149, automata[index], radius, states, ics,index);
		}
		
		//System.out.println("QQ");
		for(int index=0; index<100; index++)
		{
			threadPool.submit(sims[index]);
		}
		System.out.println("y");
		threadPool.shutdown();
		threadPool.awaitTermination(10000, TimeUnit.MINUTES);
		System.out.println("x");
		for(int index=0; index<100; index++)
		{
			System.out.println("Thread " + index + " solved  "+ automata[index].problemsSolved());
		}
	}
	public static void main(String[] args) throws Exception{
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ga.fork();
	}
}
