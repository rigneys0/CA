package simulator;


public class TwoDSimulator {
	private CA _automaton;
	public TwoDSimulator(CA automaton){
		_automaton = automaton;
	}
	public int[][][] simulate(int latticeSize,int[][] IC,int turns){
		int[][][] output = new int[turns+1][latticeSize][latticeSize];
		output[0]=IC;
		for(int turn =1;turn<turns+1;turn++){
			oneIteration(latticeSize,output[turn],output[turn-1]);
		}
		return output;
	}//Simulate
	private void oneIteration(int latticeSize,int[][] currentLattice, 
										int[][] previousLattice){
		for(int centerY=0;centerY<latticeSize;centerY++){
			for(int centerX=0;centerX<latticeSize;centerX++){
				changeState(latticeSize,centerX,centerY,currentLattice,previousLattice);
			}
		}
	}//oneIteration
	private void changeState(int latticeSize,int centerX,int centerY,
						int[][] currentLattice,int[][] previousLattice){
		int neighbourhoodSize = (2*_automaton.getRadius() + 1);
		int leftMost = ((centerX-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		int topMost = ((centerY-_automaton.getRadius())
				+ latticeSize)%latticeSize;
		System.out.println("QQ"+leftMost+","+topMost);
		long rule=0;
		for(int row=0;row<neighbourhoodSize;row++){
			for(int col=0;col<neighbourhoodSize;col++){
				rule+=(previousLattice[(row+topMost)%latticeSize]
						[(col+leftMost)%latticeSize]
						<<((neighbourhoodSize-row)-1)*neighbourhoodSize +col);
			}
		}
		currentLattice[centerY][centerX]=_automaton.parseRule(rule);
	}//changeState
	public static void main(String[] args){
		ICGenerator icg = new ICGenerator(2);
		int[][] ic = icg.getIC2D(5);
		CA automaton = new CA(5,1,2);
		TwoDSimulator newAutomata = new TwoDSimulator(automaton);
		int[][][] result = newAutomata.simulate(5, ic, 5);
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				for(int k=0;k<5;k++){
					System.out.print(+result[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
	}
}