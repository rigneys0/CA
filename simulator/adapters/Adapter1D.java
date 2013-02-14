package simulator.adapters;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import imageManipulation.AutomataDisplayer;
import imageManipulation.States;
import probabilityModel.GroupModel;
import problem.CriticalDensityProblem;
import simulator.CA;
import simulator.IC;
import simulator.ICGenerator;
import simulator.rawSimulators.OneDSimulator;

public class Adapter1D implements SimulatorAdapter{
	private CriticalDensityProblem _pcd;
	private OneDSimulator _sim1D;
	private byte[][] storage;
	private HashMap<Integer,byte[][]> _finalImageBlock;
	private byte _turns;
	private int _latticeSize; 
	private int _imageBlockCounter;
	public Adapter1D(){
		_sim1D = new OneDSimulator();
		_finalImageBlock=new HashMap<Integer,byte[][]>();
	}
	public void setUp(byte turns, int latticeSize){
		storage = new byte[turns+1][latticeSize];
		_latticeSize = latticeSize;
		_turns = turns;
	}
	public HashMap<Integer,byte[][]> getFinalOutput() {
		return _finalImageBlock;
	}
	@Override
	public boolean run(CA automaton,byte radius, byte states, IC ic) {
		storage[0] = Arrays.copyOf(ic.getOneDimensionIC(), _latticeSize);
		_pcd = new CriticalDensityProblem(states);
		_sim1D.setParameters(_latticeSize, _turns, radius, states);
		storage = _sim1D.simulate(storage,automaton);
		createFinalImageBlock(storage);
		return solvesProblem(storage[0],storage[_turns]);
	}
	private void createFinalImageBlock(byte[][] output){
		_finalImageBlock.remove(0);
		_finalImageBlock.put(0, output);
		_imageBlockCounter++;
			
	}
	private boolean solvesProblem(byte[] ic, byte[] finalRow) {
		return _pcd.solves1DProblem(ic, finalRow);
	}
	
	@Override
	public int getDimensions() {
		return 1;
	}
	public static void main(String[] args) throws IOException{
		CA aut = CA.newInstance();
		SimulatorAdapter ad1 = new Adapter1D();
		System.out.println(ad1.getClass().getCanonicalName());
		System.out.println(SimulatorAdapter.class.getCanonicalName());
		ICGenerator icgen = new ICGenerator(new GroupModel(2,2));
		IC ic = new IC();
		ic.set1D(icgen.getIC1D(149));
		ad1.run(aut,(byte)3,(byte)2, ic);
		HashMap<Integer,byte[][]> output = ad1.getFinalOutput();
		AutomataDisplayer.toImage(output.get(0), "jpeg", "testImage", States.TWO);
		for(int i=0;i<100;i++){
			for(int j=0;j<149;j++){
				System.out.print(output.get(0)[i][j]);
			}
			System.out.println();
		}
	}
}
