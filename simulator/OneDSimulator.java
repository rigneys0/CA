package simulator;
import java.util.Arrays;
public class OneDSimulator {
	private CA _automaton;
	public OneDSimulator(CA automaton){
		_automaton = automaton;
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
		int leftMost = ((centerIndex-_automaton.getRadius()) 
				+ latticeSize)%latticeSize;
		int rule=0;
		for(int i=1;i<(2*_automaton.getRadius() + 2);i= i + 1){
			rule+=previousLattice[(leftMost+(i-1))%latticeSize]<<
					((2*_automaton.getRadius()+1)-i);
		}
		currentLattice[centerIndex]=_automaton.parseRule(rule);
	}//changeState
	public static void main(String[] args){
		
	}
}
