package simulator;

import java.io.IOException;

import imageManipulation.AutomataDisplayer;
import imageManipulation.States;
import probabilityModel.GroupModel;
import probabilityModel.Model;
import problem.Problem;

public class Adapter1D implements SimulatorAdapter{

	private ICGenerator _icGen;
	private OneDSimulator _sim1D;
	private int[][] _currentImageBlock;
	public Adapter1D(ICGenerator icGenerator){
		_icGen = icGenerator;
		_sim1D = new OneDSimulator();
		_currentImageBlock=null;
	}
	@Override
	public int[][] getNextImageOutput() {
		return _currentImageBlock;
	}
	@Override
	public void run(int turns, int latticeSize, CA automata) {
		int[] ic = _icGen.getIC1D(latticeSize);
		_currentImageBlock=_sim1D.simulate(latticeSize, ic, turns, automata);
	}
	@Override
	public boolean solvesProblem(Problem<String> p) {
		for(int i=0;i<_currentImageBlock.length;i++){
			if(""+_currentImageBlock[i]!=p.getSolutionToAchieve()){
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) throws IOException{
		Model icModel = new GroupModel(2,1);
		ICGenerator icGenerator = new ICGenerator(icModel); 
		CA aut = CA.newInstance(3, 2);
		SimulatorAdapter ad1 = new Adapter1D(icGenerator);
		System.out.println(ad1.getClass().getCanonicalName());
		System.out.println(SimulatorAdapter.class.getCanonicalName());
		ad1.run(100, 149, aut);
		int[][] output = ad1.getNextImageOutput();
		AutomataDisplayer.toImage(output, "jpeg", "testImage", States.TWO);
		for(int i=0;i<100;i++){
			for(int j=0;j<149;j++){
				System.out.print(output[i][j]);
			}
			System.out.println();
		}
	}
}
