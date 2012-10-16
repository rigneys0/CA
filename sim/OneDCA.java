package sim;
import java.util.Arrays;
public class OneDCA {
	private int[] ruleTable;
	private int radius;
	public OneDCA(int[] ruleTable, int radius){
		this.ruleTable = ruleTable;
		this.radius = radius;
	}
	public int[][] simulate(int latticeSize,int[] IC,int turns){
		int[][] output = new int[turns+1][latticeSize];
		output[0]=Arrays.copyOf(IC, latticeSize);
		for(int turn =1;turn<turns+1;turn++){
			oneIteration(latticeSize,output[turn],output[turn-1]);
		}
		return output;
	}//Simulate
	private void oneIteration(int latticeSize,int[] currentLattice, 
										int[] previousLattice){
		for(int centerIndex=0;centerIndex<latticeSize;centerIndex++){
			changeState(latticeSize,centerIndex,currentLattice,previousLattice);
		}
	}//oneIteration
	private void changeState(int latticeSize,int centerIndex,
						int[] currentLattice,int[] previousLattice){
		int leftMost = ((centerIndex-radius) + latticeSize)%latticeSize;
		int rule=0;
		for(int i=1;i<(2*radius + 2);i= i + 1){
			rule+=previousLattice[(leftMost+(i-1))%latticeSize]<<((2*radius+1)-i);
		}
		currentLattice[centerIndex]=ruleTable[rule];
	}//changeState
	public static void main(String[] args){
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDCA someCA = new OneDCA(new int[]{0,1,1,0,1,1,1,0},1);
		int[][] output=
				someCA.simulate(21,IC,41);
		for(int row=0;row<41;row++){
			for(int col=0;col<21;col++){
				System.out.print(output[row][col]);
			}
			System.out.println();
		}
		System.out.println(Integer[].class.getName());
	}
}
