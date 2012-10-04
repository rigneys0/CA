package simulator;

public class ThreeDSimulator {
	private CA _automaton;
	public ThreeDSimulator(CA automaton){
		_automaton = automaton;
	}
	public int[][][][] simulate(int latticeSize, int[][][] ic, int turns){
		int[][][][] output = 
				new int[turns][latticeSize][latticeSize][latticeSize];
		output[0]=ic;
		for(int turn =1;turn<turns+1;turn++){
			oneIteration(latticeSize,output[turn],output[turn-1]);
		}
		return output;
	}
	private void oneIteration(int latticeSize,int[][][] currentLattice, 
			int[][][] previousLattice){
		for(int centerY=0;centerY<latticeSize;centerY++){
			for(int centerX=0;centerX<latticeSize;centerX++){
				for(int centerZ=0; centerZ<latticeSize;centerZ++){
					changeState(latticeSize,currentLattice,previousLattice,
						centerX,centerY,centerZ);
				}
			}
}
}//oneIteration
	private void changeState(int latticeSize, int[][][] currentLattice, 
			int[][][] previousLattice,int centerX,int centerY,int centerZ){
		int neighbourhoodSize = (2*_automaton.getRadius() + 1);
		int leftMost = ((centerX-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		int topMost = ((centerY-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		int backMost = ((centerZ-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		System.out.println("QQ"+leftMost+","+topMost);
		long rule=0;
		for(int row=0;row<neighbourhoodSize;row++){
			for(int col=0;col<neighbourhoodSize;col++){
				for(int plane=0;plane<neighbourhoodSize;plane++){
					rule+=(previousLattice[(row+topMost)%latticeSize]
						[(col+leftMost)%latticeSize][(plane+backMost)%latticeSize]
						<<((neighbourhoodSize-row)-1)*neighbourhoodSize +col);
				}
			}
		}
		currentLattice[centerY][centerX][centerZ]=_automaton.parseRule(rule);
	}
}
