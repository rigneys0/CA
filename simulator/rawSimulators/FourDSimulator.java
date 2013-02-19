package simulator.rawSimulators;

import simulator.CA;

public class FourDSimulator {
	private int _latticeSize;
	private int _turns;
	private byte _radius;
	public FourDSimulator(){
	}
	public void setParameters(int latticeSize,int turns,byte radius){
		_latticeSize = latticeSize;
		_turns = turns;
		_radius = radius;
	}
	public byte[][][][][] simulate(byte[][][][] ic,CA automaton){
		byte[][][][][] output = 
				new byte[_turns][_latticeSize][_latticeSize][_latticeSize][_latticeSize];
		output[0]=ic;
		for(int turn =1;turn<_turns+1;turn++){
			oneIteration(output[turn],output[turn-1],automaton);
		}
		return output;
	}
	private void oneIteration(byte[][][][] currentLattice,byte[][][][] previousLattice,CA automaton){
		for(int centerT=0; centerT<_latticeSize;centerT++){
			for(int centerZ=0; centerZ<_latticeSize;centerZ++){
				for(int centerY=0;centerY<_latticeSize;centerY++){
					for(int centerX=0;centerX<_latticeSize;centerX++){	
						changeState(currentLattice,previousLattice,centerX,centerY,
								centerZ,centerT,automaton);
					}
				}
			}
		}
	}//oneIteration
	private void changeState(byte[][][][] currentLattice,
			byte[][][][] previousLattice,int centerX,int centerY,
			int centerZ, int centerT, CA automaton){
		int neighbourhoodSize = (2*_radius + 1);
		int leftMost = ((centerX-_radius)
				+ _latticeSize)%_latticeSize;
		int topMost = ((centerY-_radius)
				+ _latticeSize)%_latticeSize;
		int backMost = ((centerZ-_radius)
				+ _latticeSize)%_latticeSize;
		int earliest = ((centerT-_radius)
				+ _latticeSize)%_latticeSize;
		System.out.println("QQ"+leftMost+","+topMost);
		long rule=0;
		for(int cubePlane=0;cubePlane<neighbourhoodSize;cubePlane++){
			for(int plane=0;plane<neighbourhoodSize;plane++){
				for(int row=0;row<neighbourhoodSize;row++){
					for(int col=0;col<neighbourhoodSize;col++){
						rule+=(previousLattice[(cubePlane + earliest)
						      %_latticeSize][(plane+backMost)
						      %_latticeSize][(row+topMost)%_latticeSize]
						      [(col+leftMost)%_latticeSize]
						      <<(((neighbourhoodSize - plane)-1)
						      *neighbourhoodSize*
						      neighbourhoodSize +((
						      neighbourhoodSize-row)-1)*
						      neighbourhoodSize+col));
					}
				}
			}
		}
		currentLattice[centerT][centerZ][centerY][centerX]=
				automaton.parseRule(rule);
	}
}
