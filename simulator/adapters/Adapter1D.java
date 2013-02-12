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
	private int _imageBlockCounter;
	public Adapter1D(){
		_sim1D = new OneDSimulator();
		_finalImageBlock=new HashMap<Integer,byte[][]>();
	}
	public void setUp(int turns, int latticeSize){
		storage = new byte[turns+1][latticeSize];
	}
	public HashMap<Integer,byte[][]> getFinalOutput() {
		return _finalImageBlock;
	}
	@Override
	public synchronized boolean run(int turns, int latticeSize, CA automaton,
			byte radius, byte states, IC ic) {
		storage[0] = Arrays.copyOf(ic.getOneDimensionIC(), latticeSize);
		_pcd = CriticalDensityProblem.getInstance(states);
		_sim1D.setParameters(latticeSize, turns, radius, states);
		storage = _sim1D.simulate(storage,automaton);
		//createFinalImageBlock(output);
		return solvesProblem(ic.getOneDimensionIC(),storage[turns]);
	}
	private void createFinalImageBlock(byte[][] output){
		_finalImageBlock.put(_imageBlockCounter, output);
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
		ad1.run(100, 149, aut,(byte)3,(byte)2, ic);
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
