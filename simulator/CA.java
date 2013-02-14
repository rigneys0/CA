package simulator;

import java.util.Random;

public class CA {
	private long _key;
	private static Random _keyGen = new Random(System.currentTimeMillis());
	private int _numberSolved;
	private byte[] _table;
	public CA(long key){
		//_key = key;
		_numberSolved = 0;
		_table = new byte[64];
		for(int index=0; index<64;index++){
			
			_table[index] = (byte) Math.abs(generateKey() % 4);
			//System.out.println(_table[index]);
		}
	}
	public long getKey(){
		return _key;
	}
	public void changeKey(){
		_key=0;
	}
	public byte parseRule(long neighbourhoodValue,byte numberOfStates){
		//long xOROfNeighbourValWithKey = (getKey() ^ neighbourhoodValue);
		return (byte) (_table[(int) (neighbourhoodValue % 64)]%numberOfStates);
	}
	public void solvesProblem(){
		_numberSolved++;
	}
	public void resetProblemsSolved(){
		_numberSolved=0;
	}
	public int problemsSolved(){
		return _numberSolved;
	}
	private long generateKey(){
		return _keyGen.nextLong();
	}
}
