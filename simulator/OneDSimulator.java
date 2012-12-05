package simulator;
import java.io.IOException;
import java.util.Arrays;

import probabilityModel.BitModel;
import imageManipulation.AutomataDisplayer;
import imageManipulation.States;

public class OneDSimulator {
	public OneDSimulator(){
	}
	public int[][] simulate(int latticeSize,int[] IC,int turns,
			CA automaton){
		int[][] output = new int[turns+1][latticeSize];
		output[0]=Arrays.copyOf(IC, latticeSize);
		for(int turn =1;turn<turns+1;turn++){
			oneIteration(latticeSize,output[turn],output[turn-1],automaton);
		}
		return output;
	}//Simulate
	private void oneIteration(int latticeSize,int[] currentLattice, 
								int[] previousLattice,CA automaton){
		for(int centerIndex=0;centerIndex<latticeSize;centerIndex++){
			changeState(latticeSize,centerIndex,currentLattice,
					previousLattice,automaton);
		}
	}//oneIteration
	private void changeState(int latticeSize,int centerIndex,
					int[] currentLattice,int[] previousLattice,
					CA automaton){
		int leftMost = ((centerIndex-automaton.getRadius()) 
				+ latticeSize)%latticeSize;
		int rule=0;
		for(int i=1;i<(2*automaton.getRadius() + 2);i= i + 1){
			rule+=previousLattice[(leftMost+(i-1))%latticeSize]<<
					((2*automaton.getRadius()+1)-i);
		}
		currentLattice[centerIndex]=automaton.parseRule(rule);
	}//changeState
	public static void main(String[] args) throws IOException{
		CA automaton = new CA(110,9,7);
		OneDSimulator newAutomata = new OneDSimulator();
		BitModel bitModel = new BitModel(7);
		ICGenerator icg = new ICGenerator(bitModel);
		int[] ic = icg.getIC1D(149);
		int[][] result = newAutomata.simulate(149, ic, 300,automaton);
		AutomataDisplayer.toImage(result, "png", "testImage", States.SEVEN);
		for(int time=0;time<300;time++){
			for(int i=0;i<149;i++){
				System.out.print(result[time][i]);
			}
			System.out.println();
		}
	}
}
