package simulator;

import java.util.Random;

public class CA {
	private long _key;
	private static Random _keyGen = new Random(System.currentTimeMillis());
	private static long ctr=0;
	public CA(long key){
		_key = key;
	}
	public static synchronized CA newInstance(){
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
		return (byte)(neighbourhoodValue % numberOfStates);
	}
	private static long generateKey(){
		return Math.abs(_keyGen.nextLong()+ctr++);
	}
}
