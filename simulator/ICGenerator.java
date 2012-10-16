package simulator;
import java.util.Random;
public class ICGenerator {
	private Random _numberGen;
	private int _numberOfStates;
	public ICGenerator(int numberOfStates){
		_numberGen = new Random(System.currentTimeMillis());
		_numberOfStates = numberOfStates;
	}
	public int[] getIC1D(int latticeSize){
		int[] newIC = new int[latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	public int[][] getIC2D(int latticeSize){
		int[][] newIC = new int[latticeSize][latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	public int[][][] getIC3D(int latticeSize){
		int[][][] newIC = new int[latticeSize][latticeSize][latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	public int[][][][] getIC4D(int latticeSize){
		int[][][][] newIC = new int[latticeSize][latticeSize][latticeSize][latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	private void populate(int[] IC, int latticeSize){
		for(int cols=0;cols<latticeSize;cols++){
			IC[cols]=getValue();
		}
	}
	private void populate(int[][] IC, int latticeSize){
		for(int rows=0;rows<latticeSize;rows++){
			for(int cols=0;cols<latticeSize;cols++){
				IC[rows][cols]=getValue();
			}
		}
	}
	private void populate(int[][][] IC, int latticeSize){
		for(int planes=0; planes<latticeSize;planes++){
			for(int rows=0;rows<latticeSize;rows++){
				for(int cols=0;cols<latticeSize;cols++){
					IC[planes][rows][cols]=getValue();
			
				}
			}
		}
	}
	private void populate(int[][][][] IC, int latticeSize){
		for(int cubes=0;cubes<latticeSize;cubes++){
			for(int planes=0; planes<latticeSize;planes++){
				for(int rows=0;rows<latticeSize;rows++){
					for(int cols=0;cols<latticeSize;cols++){
						IC[cubes][planes][rows][cols]=getValue();
					}
				}
			}
		}
	}
	public int getValue(){
		return Math.abs(_numberGen.nextInt())%_numberOfStates;
	}
}
