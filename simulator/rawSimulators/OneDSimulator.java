package simulator.rawSimulators;
import java.io.IOException;
import probabilityModel.GroupModel;
import simulator.CA;
import simulator.ICGenerator;
import imageManipulation.AutomataDisplayer;
import imageManipulation.States;

public class OneDSimulator {
	private int _latticeSize;
	private int _turns;
	private byte _radius;
	private byte _states;
	public OneDSimulator(){
	}
	public void setParameters(int latticeSize,int turns,byte radius,byte states){
		_latticeSize = latticeSize;
		_turns = turns;
		_radius = radius;
		_states = states;
	}
	public byte[][] simulate(byte[][] storage,CA automaton){
		for(int turn =1;turn<_turns+1;turn++){
			oneIteration(storage[turn],storage[turn-1],automaton);
		}
		return storage;
	}//Simulate
	private void oneIteration(byte[] currentLattice, 
								byte[] previousLattice,CA automaton){
		for(int centerIndex=0;centerIndex<_latticeSize;centerIndex++){
			changeState(centerIndex,currentLattice,previousLattice,automaton);
		}
	}//oneIteration
	private void changeState(int centerIndex,
					byte[] currentLattice,byte[] previousLattice,
					CA automaton){
		int leftMost = ((centerIndex-_radius) 
				+ _latticeSize)%_latticeSize;
		long rule=0;
		for(int i=1;i<(2*_radius + 2);i= i + 1){
			rule+=previousLattice[(leftMost+(i-1))%_latticeSize]<<
					((2*_radius+1)-i);
		}
		currentLattice[centerIndex]=automaton.parseRule(rule,_states);
	}//changeState
	public static void main(String[] args) throws IOException{
		CA automaton = new CA(9111111111l);
		OneDSimulator newAutomata = new OneDSimulator();
		for(int index=0; index<1000; index++){
			for(int index2=0; index2<1000;index2++){
				
			}
		GroupModel gModel = new GroupModel(2,6);
		ICGenerator icg = new ICGenerator(gModel);
		byte[][] storage = new byte[201][149];
		storage[0]= icg.getIC1D(149);
		
		newAutomata.setParameters(149,200,(byte)13,(byte)2);
		byte[][] result = newAutomata.simulate(storage,automaton);
		AutomataDisplayer.toImage(result, "png", "testImage"+index, States.TWO);
		/*for(int time=0;time<200;time++){
			for(int i=0;i<ic.length;i++){
				System.out.print(result[time][i]);
			}
			System.out.println();
		}*/
		}
	}
}
