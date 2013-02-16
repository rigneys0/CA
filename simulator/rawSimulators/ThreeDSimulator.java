package simulator.rawSimulators;

import probabilityModel.BitModel;
import simulator.CA;
import simulator.ICGenerator;

public class ThreeDSimulator {
	private int _latticeSize;
	private int _turns;
	private byte _radius;
	private byte _states;
	public ThreeDSimulator(){
	}
	public void setParameters(int latticeSize,int turns,byte radius,byte states){
		_latticeSize = latticeSize;
		_turns = turns;
		_radius = radius;
		_states = states;
	}
	public byte[][][][] simulate(byte[][][] ic,CA automaton){
		byte[][][][] output = 
				new byte[_turns+1][_latticeSize][_latticeSize][_latticeSize];
		output[0]=ic;
		for(int turn =1;turn<_turns+1;turn++){
			oneIteration(output[turn],output[turn-1],
					automaton);
		}
		return output;
	}
	private void oneIteration(byte[][][] currentLattice, 
			byte[][][] previousLattice,CA automaton){
		for(int centerZ=0; centerZ<_latticeSize;centerZ++){
			for(int centerY=0;centerY<_latticeSize;centerY++){
				for(int centerX=0;centerX<_latticeSize;centerX++){
					changeState(currentLattice,previousLattice,
						centerX,centerY,centerZ,automaton);
				}
			}
		}
	}//oneIteration
	private void changeState(byte[][][] currentLattice, 
			byte[][][] previousLattice,int centerX,int centerY,int centerZ,
			CA automaton){
		int neighbourhoodSize = (2*_radius + 1);
		int leftMost = ((centerX-_radius)
				+ _latticeSize)%_latticeSize;
		int topMost = ((centerY-_radius)
				+ _latticeSize)%_latticeSize;
		int backMost = ((centerZ-_radius)
				+ _latticeSize)%_latticeSize;
		//System.out.println("QQ"+leftMost+","+topMost);
		long rule=0;
		for(int plane=0;plane<neighbourhoodSize;plane++){
			for(int row=0;row<neighbourhoodSize;row++){
				for(int col=0;col<neighbourhoodSize;col++){
					rule+=(previousLattice[(plane+backMost)%_latticeSize]
							[(row+topMost)%_latticeSize]
							[(col+leftMost)%_latticeSize]
							<<(((neighbourhoodSize - plane)-1)
						*neighbourhoodSize*neighbourhoodSize
						+((neighbourhoodSize-row)-1)*neighbourhoodSize+col));
				}
			}
		}
		currentLattice[centerZ][centerY][centerX]=automaton.parseRule(rule,_states);
	}
	public static void main(String[] args){
		CA someCA = new CA(0);
		int latticeSize=4;
		ThreeDSimulator ThreeDS = new ThreeDSimulator();
		BitModel bitModel = new BitModel(7);
		ICGenerator newIC = new ICGenerator(bitModel);
		byte[][][] ic = newIC.getIC3D(4);
		byte[][][][] result = ThreeDS.simulate(ic,someCA);
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
