package simulator;

import java.util.Random;

public class CA {
	private long _key;
	private static Random _keyGen = new Random(System.currentTimeMillis());
	private static long ctr=0;
	private int _numberSolved;
	public CA(long key){
		_key = key;
		_numberSolved = 0;
	}
	public static CA newInstance(){
		return new CA(generateKey());
	}
	public long getKey(){
		return _key;
	}
	public void changeKey(){
		_key=generateKey();
	}
	public byte parseRule(long neighbourhoodValue,byte numberOfStates){
		long xOROfNeighbourValWithKey = (getKey() ^ neighbourhoodValue);
		return (byte)(xOROfNeighbourValWithKey % numberOfStates);
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
	private static long generateKey(){
		return Math.abs(_keyGen.nextLong()+ctr++);
	}
}
