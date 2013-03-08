package simulator;

import java.util.Random;

public class CA implements Comparable<CA>{
	private static Random _keyGen = new Random(System.currentTimeMillis());
	private int _numberSolved;
	private final int RULE_TABLE_SIZE = 64;
	private byte _states;
	private byte[] _key;
	public CA(byte states){
		_numberSolved = 0;
		_states = states;
		_key = new byte[RULE_TABLE_SIZE];
		generateKey();
	}
	public byte[] getKey(){
		return _key;
	}
	public void changeKey(){
		generateKey();
	}
	public byte parseRule(long neighbourhoodValue){
		//long xOROfNeighbourValWithKey = (getKey() ^ neighbourhoodValue);
		return _key[(int) (Math.abs(neighbourhoodValue) % RULE_TABLE_SIZE)];
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
	@Override
	public int compareTo(CA other){
		return _numberSolved - other._numberSolved;
	}
	@Override
	public boolean equals(Object other){
		return compareTo((CA)other)==0;
	}
	private void generateKey(){
		for(int index=0; index<RULE_TABLE_SIZE;index++){
			_key[index] = (byte) Math.abs(_keyGen.nextInt() % _states);
		}
	}
	@Override
	public String toString(){
		String returnValue = "";
		for(byte b : _key){
			returnValue+=b;
		}
		return returnValue;
	}
}
