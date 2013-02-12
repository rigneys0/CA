package simulator.adapters;

import java.util.Arrays;
import java.util.HashMap;

import problem.CriticalDensityProblem;
import simulator.CA;
import simulator.IC;
import simulator.rawSimulators.ThreeDSimulator;

public class Adapter3D implements SimulatorAdapter{

	private ThreeDSimulator _sim3D;
	private HashMap<Integer,byte[][]> _finalImageBlock;
	private CriticalDensityProblem _pcd;
	private int _imageBlockCounter;
	private byte _turns;
	private int _latticeSize; 
	private byte[][][][] _storage;
	public Adapter3D(){
		_sim3D = new ThreeDSimulator();
		_finalImageBlock=new HashMap<Integer,byte[][]>();
	}
	@Override
	public int getDimensions(){
		return 3;
	}
	@Override
	public HashMap<Integer,byte[][]> getFinalOutput() {
		return _finalImageBlock;
	}
	public void setUp(byte turns, int latticeSize){
		_storage = new byte[turns+1][latticeSize][latticeSize][latticeSize];
	}
	@Override
	public boolean run(CA automaton,byte radius, byte states,IC ic) {
		reset();
		_storage[0] = Arrays.copyOf(ic.getThreeDimensionIC(), _latticeSize);
		_pcd = CriticalDensityProblem.getInstance(states);
		_storage = _sim3D.simulate(ic.getThreeDimensionIC(),automaton);
		createFinalImageBlock(_storage[_turns]);
		return solvesProblem(_storage[0],_storage[_turns]);
	}
	private boolean solvesProblem(byte[][][] ic, byte[][][] finalRow) {
		return _pcd.solves3DProblem(ic, finalRow);
	}
	private void reset(){
		_imageBlockCounter = 0;
		_finalImageBlock.clear();
		_imageBlockCounter=0;
	}
	private void createFinalImageBlock(byte[][][] output){
			for(byte[][] twoDBlock : output){
				_finalImageBlock.put(_imageBlockCounter, twoDBlock);
				_imageBlockCounter++;
			}
	}
}
