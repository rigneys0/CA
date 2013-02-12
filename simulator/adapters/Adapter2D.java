package simulator.adapters;

import java.util.Arrays;
import java.util.HashMap;

import problem.CriticalDensityProblem;
import simulator.CA;
import simulator.IC;
import simulator.rawSimulators.TwoDSimulator;

public class Adapter2D implements SimulatorAdapter {

	private TwoDSimulator _sim2D;
	private HashMap<Integer,byte[][]> _finalImageBlock;
	private byte[][][] _storage;
	private CriticalDensityProblem _pcd;
	private int _imageBlockCounter;
	private byte _turns;
	private int _latticeSize; 
	public Adapter2D(){
		_sim2D = new TwoDSimulator();
		_finalImageBlock=new HashMap<Integer,byte[][]>();
	}
	@Override
	public int getDimensions(){
		return 2;
	}
	public void setUp(byte turns, int latticeSize){
		_storage = new byte[turns+1][latticeSize][latticeSize];
	}
	@Override
	public HashMap<Integer,byte[][]> getFinalOutput() {
		return _finalImageBlock;
	}
	@Override
	public boolean run(CA automaton,byte radius,byte states, IC ic) {
		reset();
		_storage[0] = Arrays.copyOf(ic.getTwoDimensionIC(), _latticeSize);
		_pcd = CriticalDensityProblem.getInstance(states);
		_storage = _sim2D.simulate(ic.getTwoDimensionIC(),automaton);
		createFinalImageBlock(_storage[_turns]);
		return solvesProblem(_storage[0],_storage[_turns]);
	}
	private boolean solvesProblem(byte[][] ic, byte[][] finalRow) {
		return _pcd.solves2DProblem(ic, finalRow);
	}
	private void reset(){
		_imageBlockCounter = 0;
		_finalImageBlock.clear();
		_imageBlockCounter=0;
	}
	private void createFinalImageBlock(byte[][] output){
		_finalImageBlock.put(_imageBlockCounter, output);
		_imageBlockCounter++;
	}
    public static void main(String[] args){
    	
    }

}
