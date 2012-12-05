package simulator;

import java.util.Random;

public class CA {
	private long _caKey;
	private int _radius;
	private static Random _keyGen = new Random(System.currentTimeMillis());
	private int _numberOfStates;
	public CA(long key, int radius, int numberOfStates){
		_caKey = key;
		_radius = radius;
		_numberOfStates = numberOfStates;
	}
	public static CA newInstance(int radius, int numberOfStates){
		long caKey = Math.abs(_keyGen.nextLong());
		return new CA(caKey,radius,numberOfStates);
	}
	public long getKey(){
		return _caKey;
	}
	public int getRadius(){
		return _radius;
	}
	public int parseRule(long neighbourhoodValue){
		long xOROfNeighbourValWithKey = getKey() ^ neighbourhoodValue;
		return (int)(xOROfNeighbourValWithKey % _numberOfStates);
	}
}
