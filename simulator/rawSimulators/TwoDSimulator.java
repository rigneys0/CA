package simulator.rawSimulators;

import simulator.CA;

public class TwoDSimulator {
	private int _latticeSize;
	private int _turns;
	private byte _radius;
	private byte _states;
	public TwoDSimulator(){
	}
	public void setParameters(int latticeSize,int turns,byte radius,byte states){
		_latticeSize = latticeSize;
		_turns = turns;
		_radius = radius;
		_states = states;
	}
	public byte[][][] simulate(byte[][] IC,CA automaton){
		byte[][][] output = new byte[_turns+1][_latticeSize][_latticeSize];
		output[0]=IC;
		for(int turn =1;turn<_turns+1;turn++){
			oneIteration(output[turn],output[turn-1],automaton);
		}
		return output;
	}//Simulate
	private void oneIteration(byte[][] currentLattice, 
								byte[][] previousLattice,CA automaton){
		for(int centerY=0;centerY<_latticeSize;centerY++){
			for(int centerX=0;centerX<_latticeSize;centerX++){
				changeState(centerX,centerY,currentLattice,
						previousLattice,automaton);
			}
		}
	}//oneIteration
	private void changeState(int centerX,int centerY,
						byte[][] currentLattice,byte[][] previousLattice,
						CA automaton){
		int neighbourhoodSize = (2*_radius) + 1;
		int leftMost = ((centerX-_radius)
				+ _latticeSize)%_latticeSize;
		int topMost = ((centerY-_radius)
				+ _latticeSize)%_latticeSize;
		long rule=0;
		for(int row=0;row<neighbourhoodSize;row++){
			for(int col=0;col<neighbourhoodSize;col++){
				rule+=(previousLattice[(row+topMost)%_latticeSize]
						[(col+leftMost)%_latticeSize]
						<<((neighbourhoodSize-row)-1)*neighbourhoodSize +col);
			}
		}
		currentLattice[centerY][centerX]=automaton.parseRule(rule,_states);
	}//changeState
	/*public static void main(String[] args){
		SimulatorFactory simFac = SimulatorFactory.getInstance();
		TwoDSimulator twoDS = (TwoDSimulator)(simFac.getSimulator(2));
		BitModel bitModel = new BitModel(7);
		ICGenerator icg = new ICGenerator(bitModel);
		byte[][] ic = icg.getIC2D(5);
		CA automaton = new CA(5,1,2);
		/*byte[][][] result =twoDS.simulate(5, ic, 5,automaton);
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
	}*/
}
