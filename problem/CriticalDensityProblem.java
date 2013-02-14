package problem;

public class CriticalDensityProblem{

	private static CriticalDensityProblem instance=null; 
	private int[] _states;
	public CriticalDensityProblem(byte numberOfStates){
		_states = new int[numberOfStates];
	}
	public synchronized boolean solves1DProblem(byte[] iC,
			byte[] finalRow){
		reset();
		for(byte b : iC){
			_states[b]++;
		}
		int mostCommonState = getLargest();
		for(byte b : finalRow){
			if(b!=mostCommonState){
				return false;
			}
		}
		return true;
	}
	private void reset(){
		for(int index=0; index<_states.length;index++){
			_states[index]=0;
		}
	}
	private byte getLargest(){
		byte largest = 0;
		for(byte index=1;index<_states.length;index++){
			if(_states[index]>_states[largest]){
				largest = index;
			}
		}
		return largest;
	}
	public synchronized boolean solves2DProblem(byte[][] iC,
			byte[][] finalSquare){
		reset();
		for(byte[] row : iC){
			for(byte b : row){
				_states[b]++;
			}
		}
		byte mostCommonState = getLargest();
		for(byte[] row : finalSquare){
			for(byte b : row){
				if(b!=mostCommonState){
					return false;
				}
			}
		}
		return true;
	}
	public synchronized boolean solves3DProblem(byte[][][] iC,
			byte[][][] finalCube){
		reset();
		for(byte[][] square : iC){
			for(byte[] row : square){
				for(byte b : row){
					_states[b]++;
				}
			}
		}
		byte mostCommonState = getLargest();
		for(byte[][] square :finalCube){
			for(byte[] row : square){
				for(byte b : row){
					if(b!=mostCommonState){
						return false;
					}
				}
			}
		}
		return true;
	}
	public synchronized boolean solves4DProblem(byte[][][][] iC,
			byte[][][][] finalHyperCube){
		reset();
		for(byte[][][] cube : iC){
			for(byte[][] square : cube){
				for(byte[] row : square){
					for(byte b : row){
						_states[b]++;
					}
				}
			}
		}
		byte mostCommonState = getLargest();
		for(byte[][][] cube : finalHyperCube){
			for(byte[][] square :cube){
				for(byte[] row : square){
					for(byte b : row){
						if(b!=mostCommonState){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	public static void main(String[] args){
		
	}
}
