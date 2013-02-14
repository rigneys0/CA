package geneticAlgorithm;

import java.util.HashMap;
import simulator.CA;
import simulator.IC;
import simulator.adapters.SimulatorAdapter;

public class Simulation implements Runnable{
	private SimulatorAdapter _sim;
	private CA _automata;
	private byte _states;
	private byte _radius;
	private HashMap<Integer,byte[][]> _finalImageBlock;
	private int _numberSolved;
	private IC[] _configurations;
	private int _id =0;
	public Simulation(){
		_numberSolved=0;
	}
	public void setUp(SimulatorAdapter sim, byte turns,
			int latticeSize, CA automata, byte radius, byte states, 
			IC configurations[],int id){
		_automata = automata;
		_sim  = sim;
		_states = states;
		_radius = radius;
		_configurations = configurations;
		_sim.setUp(turns, latticeSize);
		_id = id;
	}
	public HashMap<Integer,byte[][]> getFinalImageOutput(){
		return _finalImageBlock;
	}
	@Override
	public void run(){
		for(int index=0; index<_configurations.length;index++){
			boolean solved =_sim.run(_automata,_radius, _states,_configurations[index]);
			if(solved){
				_automata.solvesProblem();
			}
		}
	}
}
