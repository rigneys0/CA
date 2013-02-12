package simulator.adapters;

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

	@Override
	public boolean run(int turns, int latticeSize, CA automata, byte radius,
			byte states,IC ic) {
		reset();
		_pcd = CriticalDensityProblem.getInstance(states);
		_finalImageBlock.clear();
		byte[][][][][] output = _sim4D.simulate(ic.getFourDimensionIC(),
				automata);
		createFinalImageBlock(output[turns]);
		return solvesProblem(ic.getFourDimensionIC(),output[turns]);
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
