package simulator;

public class FourDSimulator {
	private CA _automaton;
	public FourDSimulator(CA automaton){
		_automaton = automaton;
	}
	public int[][][][][] simulate(int latticeSize, int[][][][] ic, int turns){
		int[][][][][] output = 
				new int[turns][latticeSize][latticeSize][latticeSize][latticeSize];
		output[0]=ic;
		for(int turn =1;turn<turns+1;turn++){
			oneIteration(latticeSize,output[turn],output[turn-1]);
		}
		return output;
	}
	private void oneIteration(int latticeSize,int[][][][] currentLattice, 
			int[][][][] previousLattice){
		for(int centerT=0; centerT<latticeSize;centerT++){
			for(int centerZ=0; centerZ<latticeSize;centerZ++){
				for(int centerY=0;centerY<latticeSize;centerY++){
					for(int centerX=0;centerX<latticeSize;centerX++){	
						changeState(latticeSize,currentLattice,previousLattice,
						centerX,centerY,centerZ,centerT);
					}
				}
			}
		}
	}//oneIteration
	private void changeState(int latticeSize, int[][][][] currentLattice, 
			int[][][][] previousLattice,int centerX,int centerY,int centerZ, int centerT){
		int neighbourhoodSize = (2*_automaton.getRadius() + 1);
		int leftMost = ((centerX-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		int topMost = ((centerY-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		int backMost = ((centerZ-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		int earliest = ((centerT-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		System.out.println("QQ"+leftMost+","+topMost);
		long rule=0;
		for(int cubePlane=0;cubePlane<neighbourhoodSize;cubePlane++){
			for(int plane=0;plane<neighbourhoodSize;plane++){
				for(int row=0;row<neighbourhoodSize;row++){
					for(int col=0;col<neighbourhoodSize;col++){
						rule+=(previousLattice[(cubePlane + earliest)%latticeSize][(plane+backMost)%latticeSize][(row+topMost)%latticeSize]
						       [(col+leftMost)%latticeSize]
						       <<(((neighbourhoodSize - plane)-1)*neighbourhoodSize*neighbourhoodSize +((neighbourhoodSize-row)-1)*neighbourhoodSize+col));
					}
				}
			}
		}
		currentLattice[centerT][centerZ][centerY][centerX]=_automaton.parseRule(rule);
	}
}
