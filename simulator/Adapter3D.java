package simulator;

import problem.Problem;

public class Adapter3D implements SimulatorAdapter{

	private ICGenerator _icGen;
	private ThreeDSimulator _sim3D;
	private int[][][] _currentImageBlock;
	private int _imageBlockCounter;
	public Adapter3D(ICGenerator icGenerator){
		_icGen = icGenerator;
		_sim3D = new ThreeDSimulator();
		_currentImageBlock=null;
		_imageBlockCounter=0;
	}
	@Override
	public int[][] getNextImageOutput() {
		return _currentImageBlock[_imageBlockCounter];
	}
	@Override
	public void run(int turns, int latticeSize, CA automaton) {
		int[][][] ic = _icGen.getIC3D(latticeSize);
		_currentImageBlock=
				_sim3D.simulate(latticeSize, ic, turns,automaton)[turns];
	}
	@Override
	public boolean solvesProblem(Problem<String> p) {
		for(int i=0;i<_currentImageBlock.length;i++){
			for(int j=0;j<_currentImageBlock.length;j++){
				for(int k=0;k<_currentImageBlock.length;k++){
					if(""+_currentImageBlock[i][j][k]
							!=p.getSolutionToAchieve()){
						return false;
					}
				}
			}
		}
		return true;
	}
}
