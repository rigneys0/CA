package simulator;

import problem.Problem;

public class Adapter4D implements SimulatorAdapter{
	private ICGenerator _icGen;
	private FourDSimulator _sim4D;
	private int[][][][] _currentImageBlock;
	private int _imageBlockCounter;
	public Adapter4D(ICGenerator icGenerator){
		_icGen = icGenerator;
		_sim4D = new FourDSimulator();
		_currentImageBlock=null;
		_imageBlockCounter=0;
	}
	@Override
	public int[][] getNextImageOutput() {
		int i = _imageBlockCounter/_currentImageBlock.length;
		int j = _imageBlockCounter%_currentImageBlock.length;
		return _currentImageBlock[i][j];
	}

	@Override
	public void run(int turns, int latticeSize, CA automata) {
		int[][][][] ic = _icGen.getIC4D(latticeSize);
		_currentImageBlock=_sim4D.simulate(latticeSize, ic, turns,automata)
				[turns];
	}
	@Override
	public boolean solvesProblem(Problem<String> p) {
		for(int i=0;i<_currentImageBlock.length;i++){
			for(int j=0;j<_currentImageBlock.length;j++){
				for(int k=0;k<_currentImageBlock.length;k++){
					for(int l=0;l<_currentImageBlock.length;l++){
					if(""+_currentImageBlock[i][j][k][l]
							!=p.getSolutionToAchieve()){
						return false;
					}
				}
			}
		}
	}
	return true;
	}
}
