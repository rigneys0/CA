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
	public void run(int generations, ParameterSet ps, Hybridizer geneticBit) throws Exception{
		IC[] ics = new IC[ps.getParameterSpaceSize()];
		Simulation[] sims = new Simulation[ps.getPopulationSize()];
		CA[] population = new CA[ps.getPopulationSize()];
		for(int index =0; index < generations; index++){
			System.out.println("GENERATION "+index);
			setUpParameterSpace(ics, ps);
			setUpPopulation(sims, population, ics, ps);
			iterateGeneration(ics, sims, population, ps);
			geneticBit.hybridize(population, (byte)2,ps);
			threadPool = Executors.newFixedThreadPool(100);
		}
	}
	private void iterateGeneration(IC[] ics, Simulation[] sims,CA[] population,ParameterSet ps) throws Exception {
		
		//System.out.println("QQ");
		for(int index=0; index<ps.getPopulationSize(); index++)
		{
			threadPool.submit(sims[index]);
		}
		System.out.println("y");
		threadPool.shutdown();
		threadPool.awaitTermination(1000, TimeUnit.MINUTES);
		System.out.println("x");
		for(int index=0; index<ps.getPopulationSize(); index++)
		{
			if(population[index].problemsSolved()!=0){
				System.out.println("Thread " + index + " solved  "+
					population[index].problemsSolved());
			}
		}
		
	}
	private void setUpPopulation(Simulation[] sims, CA[] population,IC[] ics, ParameterSet ps){
		SimulatorAdapterFactory saf = SimulatorAdapterFactory.getInstance();
		for(int index=0;index<ps.getPopulationSize();index++){
			sims[index] = new Simulation();
			population[index]=new CA(ps.getStates());
			sims[index].setUp(saf.getSimulator(ps.getDimensions()), 
					ps.getTurns(), ps.getLatticeSize(),population[index], 
					ps.getRadius(), ps.getStates(), ics,index);
		}
	}
	private void setUpParameterSpace(IC[] ics, ParameterSet ps) throws Exception{
		String arrayName = "";
		for(int index = 0; index<ps.getDimensions(); index++){
			arrayName+="[";
		}
		arrayName+="B";
		for(int index=0; index< ps.getParameterSpaceSize();index++){
			ics[index] = new IC();
			Model probModel = new GroupModel(ps.getStates(),ps.getGrouping(),ps.getProbabilities());
			ICGenerator icGen = new ICGenerator(probModel);
			//byte[][] ic = icGen.getIC1D(ps.getLatticeSize());
			Object ic = icGen.getClass().getMethod("getIC"+ps.getDimensions()+"D", int.class).invoke(icGen,ps.getLatticeSize());
			ics[index].getClass().getMethod("set"+ps.getDimensions()+"D", Class.forName(arrayName)).invoke(ics[index],ic);
		}
	}
	public static void main(String[] args) throws Exception{
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ParameterSet ps = new ParameterSet();
		ps.setDimensions(Byte.parseByte(args[0]));
		ps.setLatticeSize(Integer.parseInt(args[1]));
		ps.setParameterSpaceSize(Integer.parseInt(args[2]));
		ps.setPopulationSize(Integer.parseInt(args[3]));
		ps.setStates(Byte.parseByte(args[4]));
		ps.setRadius(Byte.parseByte(args[5]));
		ps.setTurns(Byte.parseByte(args[6]));
		ps.setGrouping(Byte.parseByte(args[7]));
		ps.setProbabilities(new byte[]{1,1,1,1,0});
		Hybridizer geneticBit  = new Hybridizer(Byte.parseByte(args[8]));
		ga.run(Byte.parseByte(args[9]),ps,geneticBit);
	}
}
