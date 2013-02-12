package simulator.adapters;

import java.util.HashMap;

import problem.CriticalDensityProblem;
import simulator.CA;
import simulator.IC;
import simulator.rawSimulators.TwoDSimulator;

public class Adapter2D implements SimulatorAdapter {

	private TwoDSimulator _sim2D;
	private HashMap<Integer,byte[][]> _finalImageBlock;
	private CriticalDensityProblem _pcd;
	private int _imageBlockCounter;
	public Adapter2D(){
		_sim2D = new TwoDSimulator();
		_finalImageBlock=new HashMap<Integer,byte[][]>();
	}
	@Override
	public int getDimensions(){
		return 2;
	}
	@Override
	public HashMap<Integer,byte[][]> getFinalOutput() {
		return _finalImageBlock;
	}
	@Override
	public boolean run(int turns, int latticeSize, CA automaton,byte radius,
			byte states, IC ic) {
		reset();
		_pcd = CriticalDensityProblem.getInstance(states);
		byte[][][] output = _sim2D.simulate(ic.getTwoDimensionIC(),automaton);
		createFinalImageBlock(output[turns]);
		return solvesProblem(ic.getTwoDimensionIC(),output[turns]);
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
