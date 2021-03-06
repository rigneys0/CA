package simulator.adapters;

import java.util.HashMap;

import simulator.CA;
import simulator.IC;

public interface SimulatorAdapter {	
	public HashMap<Integer,byte[][]> getFinalOutput();
	public boolean run(CA automata,byte radius, byte states,IC ic);
	public int getDimensions();
	public void setUp(byte turns, int latticeSize);
}
