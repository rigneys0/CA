package simulator.adapters;

import java.util.Arrays;
import java.util.HashMap;

import problem.CriticalDensityProblem;
import simulator.CA;
import simulator.IC;
import simulator.rawSimulators.FourDSimulator;

public class Adapter4D implements SimulatorAdapter{
	private FourDSimulator _sim4D;
	private HashMap<Integer,byte[][]> _finalImageBlock;
	private CriticalDensityProblem _pcd;
	private int _imageBlockCounter;
	private byte _turns;
	private int _latticeSize;
	private byte _storage[][][][][];
	public Adapter4D(){
		_sim4D = new FourDSimulator();
		_finalImageBlock=new HashMap<Integer,byte[][]>();
	}
	@Override
	public int getDimensions(){
		return 4;
	}
	@Override
	public HashMap<Integer,byte[][]> getFinalOutput() {
		return _finalImageBlock;
	}
	public void setUp(byte turns, int latticeSize){
		_turns = turns;
		_latticeSize = latticeSize;
		_storage = new byte[turns+1][latticeSize][latticeSize][latticeSize][latticeSize];
	}
	@Override
	public boolean run(CA automata, byte radius,byte states,IC ic) {
		reset();
		_storage[0] = Arrays.copyOf(ic.getFourDimensionIC(), _latticeSize);
		_pcd = CriticalDensityProblem.getInstance(states);
		_finalImageBlock.clear();
		_storage = _sim4D.simulate(_storage[0],automata);
		createFinalImageBlock(_storage[_turns]);
		return solvesProblem(_storage[0],_storage[_turns]);
	}
	private boolean solvesProblem(byte[][][][] ic, byte[][][][] finalRow) {
		return _pcd.solves4DProblem(ic, finalRow);
	}
	private void reset(){
		_imageBlockCounter = 0;
		_finalImageBlock.clear();
		_imageBlockCounter=0;
	}
	private void createFinalImageBlock(byte[][][][] finalHypercube){
		for(byte[][][] threeDBlock : finalHypercube){
			for(byte[][] twoDBlock : threeDBlock){
				_finalImageBlock.put(_imageBlockCounter, twoDBlock);
				_imageBlockCounter++;
			}
		}
	}
}
