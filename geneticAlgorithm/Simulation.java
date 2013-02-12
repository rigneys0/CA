package geneticAlgorithm;

import java.util.HashMap;
import simulator.CA;
import simulator.IC;
import simulator.adapters.SimulatorAdapter;

public class Simulation implements Runnable{
	private SimulatorAdapter _sim;
	private int _turns;
	private int _latticeSize;
	private CA _automata;
	private byte _states;
	private byte _radius;
	private HashMap<Integer,byte[][]> _finalImageBlock;
	private int _numberSolved;
	private IC _configuration;
	public Simulation(){
		_numberSolved=0;
		_finalImageBlock = new HashMap<Integer,byte[][]>();
	}
	public void setUp(SimulatorAdapter sim, int turns,
			int latticeSize, CA automata, byte radius, byte states, 
			IC configuration){
		_sim = sim;
		_turns = turns;
		_automata = automata;
		_latticeSize = latticeSize;
		_states = states;
		_radius = radius;
		_finalImageBlock.clear();
		_configuration = configuration;
		_sim.setUp(turns, latticeSize);
	}
	public int getNumberSolved(){
		return _numberSolved;
	}
	public HashMap<Integer,byte[][]> getFinalImageOutput(){
		return _finalImageBlock;
	}
	@Override
	public void run(){
			boolean solved = 
					_sim.run(_turns, _latticeSize, _automata,_radius, _states,
					_configuration);
			if(solved){
				_numberSolved++;
			}
	}
}
