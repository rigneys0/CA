package sim;
public class TwoDCA {
	private int[] ruleTable;
	private int radius;
	public TwoDCA(int[] ruleTable, int radius){
		this.ruleTable = ruleTable;
		this.radius = radius;
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
		int neighbourhoodSize = (2*radius + 1);
		int leftMost = ((centerX-radius) + latticeSize)%latticeSize;
		int topMost = ((centerY-radius) + latticeSize)%latticeSize;
		int rule=0;
		for(int row=0;row<neighbourhoodSize;row++){
			for(int col=0;col<neighbourhoodSize;col++){
				rule+=(previousLattice[(row+topMost)%latticeSize]
						[(col+leftMost)%latticeSize]
						<<((neighbourhoodSize-row)-1)*neighbourhoodSize +col);
			}
		}
		currentLattice[centerY][centerX]=ruleTable[rule];
	}//changeState
	public static void main(String[] args){
		ICGenerator icg = new ICGenerator();
		RuleTableGenerator rtg = new RuleTableGenerator();
		int[][] ic = icg.getIC(21);
		int[] ruleTable = rtg.getRuleTable(2);
		TwoDCA newAutomata = new TwoDCA(ruleTable,2);
		int[][][] result = newAutomata.simulate(21, ic, 1);
		for(int i=0;i<2;i++){
			for(int j=0;j<21;j++){
				for(int k=0;k<21;k++){
					System.out.print(+result[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
	}
}
