package geneticAlgorithm;

import probabilityModel.GroupModel;
import probabilityModel.Model;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import simulator.CA;
import simulator.IC;
import simulator.ICGenerator;
import simulator.adapters.SimulatorAdapter;
import simulator.adapters.SimulatorAdapterFactory;

public class GeneticAlgorithm {
	private ExecutorService threadPool = null;
	public GeneticAlgorithm(){
		threadPool = Executors.newFixedThreadPool(7000);
	}
	public void fork() throws Exception {
		byte radius = 3;
		byte states = 2;
		SimulatorAdapterFactory saf = SimulatorAdapterFactory.getInstance();
		Simulation[][] sims = new Simulation[100][10000];
		Model probModel = new GroupModel(2, 6);
		ICGenerator icGen = new ICGenerator(probModel);
		SimulatorAdapter[] simAdapts = new SimulatorAdapter[100];
		CA[] automata = new CA[100];
		for(int index=0;index<100;index++){
			simAdapts[index] = saf.getSimulator(1);
			automata[index]=CA.newInstance();
		}
		for(int index=0; index<10000;index++){
			IC someIC = new IC();
			someIC.set1D(icGen.getIC1D(149));
			for(int automataIndex=0; automataIndex<100;automataIndex++){
				Simulation simInstance =  new Simulation();
				simInstance.setUp(simAdapts[automataIndex], 149, 100,
						automata[automataIndex], radius,states,someIC);
				sims[automataIndex][index]=simInstance;
			}
			System.out.println(index);
		}
		System.out.println("QQ");
		for(int index=0; index<100; index++)
		{
			for(int index2=0; index2 < 10000;index2++){
				threadPool.submit(sims[index][index2]);
			}
		}
		System.out.println("y");
		threadPool.shutdown();
		threadPool.awaitTermination(1000, TimeUnit.MINUTES);
		System.out.println("x");
		int indexTotal = 0;
		for(int index=0; index<100; index++)
		{
			for(int index2=0; index2 < 10000;index2++){
				indexTotal+=sims[index][index2].getNumberSolved();
			}
		}
		System.out.println(indexTotal);
	}
	public static void main(String[] args) throws Exception{
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ga.fork();
	}
}
