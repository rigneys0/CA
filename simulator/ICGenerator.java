package simulator;
import java.util.Random;
public class ICGenerator {
	private Random numberGen;
	public ICGenerator(){
		numberGen = new Random(System.currentTimeMillis());
	}
	public int[][] getIC(int latticeSize){
		int[][] newIC = new int[latticeSize][latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	public void populate(int[][] IC, int latticeSize){
		for(int rows=0;rows<latticeSize;rows++){
			for(int cols=0;cols<latticeSize;cols++){
				IC[rows][cols]=getValue();
			}
		}
	}
	public int getValue(){
		return Math.abs(numberGen.nextInt())%2;
	}
}
