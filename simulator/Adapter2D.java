package simulator;

import problem.Problem;

public class Adapter2D implements SimulatorAdapter {

	private ICGenerator _icGen;
	private TwoDSimulator _sim2D;
	private int[][] _currentImageBlock;
	public Adapter2D(ICGenerator icGenerator){
		_icGen = icGenerator;
		_sim2D = new TwoDSimulator();
		_currentImageBlock=null;
	}
	@Override
	public int[][] getNextImageOutput() {
		return _currentImageBlock;
	}

	@Override
	public void run(int turns, int latticeSize, CA automaton) {
		int[][] ic = _icGen.getIC2D(latticeSize);
		_currentImageBlock=_sim2D.simulate(latticeSize, ic, turns,automaton)[turns];
	}
	@Override
	public boolean solvesProblem(Problem<String> p) {
		for(int i=0;i<_currentImageBlock.length;i++){
			for(int j=0;j<_currentImageBlock.length;j++){
				if(""+_currentImageBlock[i][j]!=p.getSolutionToAchieve()){
					return false;
				}
			}
		}
		return true;
	}
    public static void main(String[] args){
    	
    }

}
