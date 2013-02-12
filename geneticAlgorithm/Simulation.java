package geneticAlgorithm;

import java.util.HashMap;
import simulator.CA;
import simulator.IC;
import simulator.adapters.SimulatorAdapter;

public class Simulation implements Runnable{
	private SimulatorAdapter _sim;
	private byte _turns;
	private int _latticeSize;
	private CA _automata;
	private byte _states;
	private byte _radius;
	private HashMap<Integer,byte[][]> _finalImageBlock;
	private int _numberSolved;
	private IC[] _configurations;
	private int _id;
	public Simulation(){
		_numberSolved=0;
		_id=0;
		//_finalImageBlock = new HashMap<Integer,byte[][]>();
	}
	public void setUp(SimulatorAdapter sim, byte turns,
			int latticeSize, CA automata, byte radius, byte states, 
			IC configurations[],int id){
		_sim = sim;
		_turns = turns;
		_automata = automata;
		_latticeSize = latticeSize;
		_states = states;
		_radius = radius;
		//_finalImageBlock.clear();
		_configurations = configurations;
		_sim.setUp(turns, latticeSize);
		_id = id;
	}
	public int getNumberSolved(){
		return _numberSolved;
	}
	public HashMap<Integer,byte[][]> getFinalImageOutput(){
		return _finalImageBlock;
	}
	@Override
	public void run(){
		System.out.print(_id + " ");
		for(int index=0; index<_configurations.length;index++){
			boolean solved = 
					_sim.run(_automata,_radius, _states,
					_configurations[index]);
			//System.out.println(index);
			if(solved){
				System.out.println("Solved: "+_id + " " + index);
				_numberSolved++;
			}
		}
		System.out.println("FF"+_id);
			
	}
}
