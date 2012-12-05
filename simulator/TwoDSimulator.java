package simulator;

import probabilityModel.BitModel;


public class TwoDSimulator {
	public TwoDSimulator(){
	}
	public int[][][] simulate(int latticeSize,int[][] IC,int turns,
			CA automaton){
		int[][][] output = new int[turns+1][latticeSize][latticeSize];
		output[0]=IC;
		for(int turn =1;turn<turns+1;turn++){
			oneIteration(latticeSize,output[turn],output[turn-1],automaton);
		}
		return output;
	}//Simulate
	private void oneIteration(int latticeSize,int[][] currentLattice, 
								int[][] previousLattice,CA automaton){
		for(int centerY=0;centerY<latticeSize;centerY++){
			for(int centerX=0;centerX<latticeSize;centerX++){
				changeState(latticeSize,centerX,centerY,currentLattice,
						previousLattice,automaton);
			}
		}
	}//oneIteration
	private void changeState(int latticeSize,int centerX,int centerY,
						int[][] currentLattice,int[][] previousLattice,
						CA automaton){
		int neighbourhoodSize = (2*automaton.getRadius() + 1);
		int leftMost = ((centerX-automaton.getRadius())
				+ latticeSize)%latticeSize;
		int topMost = ((centerY-automaton.getRadius())
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
		currentLattice[centerY][centerX]=automaton.parseRule(rule);
	}//changeState
	public static void main(String[] args){
		BitModel bitModel = new BitModel(7);
		ICGenerator icg = new ICGenerator(bitModel);
		int[][] ic = icg.getIC2D(5);
		CA automaton = new CA(5,1,2);
		TwoDSimulator newAutomata = new TwoDSimulator();
		int[][][] result = newAutomata.simulate(5, ic, 5,automaton);
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
