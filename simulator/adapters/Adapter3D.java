package simulator.adapters;

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
	@Override
	public boolean run(int turns, int latticeSize, CA automaton,
			byte radius, byte states,IC ic) {
		reset();
		_pcd = CriticalDensityProblem.getInstance(states);
		byte[][][][] output = _sim3D.simulate(ic.getThreeDimensionIC()
				,automaton);
		createFinalImageBlock(output[turns]);
		return solvesProblem(ic.getThreeDimensionIC(),output[turns]);
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
