package simulator;

import probabilityModel.BitModel;

public class ThreeDSimulator {
	public ThreeDSimulator(){
	}
	public int[][][][] simulate(int latticeSize, int[][][] ic, int turns,
			CA automaton){
		int[][][][] output = 
				new int[turns+1][latticeSize][latticeSize][latticeSize];
		output[0]=ic;
		for(int turn =1;turn<turns+1;turn++){
			oneIteration(latticeSize,output[turn],output[turn-1],
					automaton);
		}
		return output;
	}
	private void oneIteration(int latticeSize,int[][][] currentLattice, 
			int[][][] previousLattice,CA automaton){
		for(int centerZ=0; centerZ<latticeSize;centerZ++){
			for(int centerY=0;centerY<latticeSize;centerY++){
				for(int centerX=0;centerX<latticeSize;centerX++){
					changeState(latticeSize,currentLattice,previousLattice,
						centerX,centerY,centerZ,automaton);
				}
			}
		}
	}//oneIteration
	private void changeState(int latticeSize, int[][][] currentLattice, 
			int[][][] previousLattice,int centerX,int centerY,int centerZ,
			CA automaton){
		int neighbourhoodSize = (2*automaton.getRadius() + 1);
		int leftMost = ((centerX-automaton.getRadius())
				+ latticeSize)%latticeSize;
		int topMost = ((centerY-automaton.getRadius())
				+ latticeSize)%latticeSize;
		int backMost = ((centerZ-automaton.getRadius())
				+ latticeSize)%latticeSize;
		//System.out.println("QQ"+leftMost+","+topMost);
		long rule=0;
		for(int plane=0;plane<neighbourhoodSize;plane++){
			for(int row=0;row<neighbourhoodSize;row++){
				for(int col=0;col<neighbourhoodSize;col++){
					rule+=(previousLattice[(plane+backMost)%latticeSize][(row+topMost)%latticeSize]
						[(col+leftMost)%latticeSize]<<(((neighbourhoodSize - plane)-1)*neighbourhoodSize*neighbourhoodSize 
								+((neighbourhoodSize-row)-1)*neighbourhoodSize+col));
				}
			}
		}
		currentLattice[centerZ][centerY][centerX]=automaton.parseRule(rule);
	}
	public static void main(String[] args){
		CA someCA = new CA(10241024,3,2);
		int latticeSize=4;
		ThreeDSimulator ThreeDS = new ThreeDSimulator();
		BitModel bitModel = new BitModel(7);
		ICGenerator newIC = new ICGenerator(bitModel);
		int[][][] ic = newIC.getIC3D(4);
		int[][][][] result = ThreeDS.simulate(4, ic, 2, someCA);
		for(int turns=0;turns<3;turns++){
			for(int planes=0;planes<latticeSize;planes++){
				for(int rows=0;rows<latticeSize;rows++){
					for(int cols=0;cols<latticeSize;cols++){
						System.out.print(result[turns][planes][rows][cols]);
					}
					System.out.println();
				}
				System.out.println("\n");
			}
			System.out.println("-----------------");
		}
	}
}
