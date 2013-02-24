package simulator.rawSimulators;
import java.io.IOException;
import simulator.CA;

public class OneDSimulator {
	private int _latticeSize;
	private int _turns;
	private byte _radius;
	public OneDSimulator(){
	}
	public void setParameters(int latticeSize,int turns,byte radius){
		_latticeSize = latticeSize;
		_turns = turns;
		_radius = radius;
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
		currentLattice[centerIndex]=automaton.parseRule(rule);
	}//changeState
	public static void main(String[] args) throws IOException{
	/*	CA automaton = new CA((byte)2);
		OneDSimulator newAutomata = new OneDSimulator();
		GroupModel gModel = new GroupModel(4,6);
		ICGenerator icg = new ICGenerator(gModel);
		byte[][] storage = new byte[101][149];
		storage[0]= icg.getIC1D(149);
		
		newAutomata.setParameters(149,100,(byte)6);
		byte[][] result = newAutomata.simulate(storage,automaton);
		AutomataDisplayer.toImage(result, "png", "testImage", States.FOUR);
		for(int time=0;time<101;time++){
			for(int i=0;i<storage[0].length;i++){
				System.out.print(result[time][i]);
			}
			System.out.println();
		}*/
	}
}
