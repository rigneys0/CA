package simulator;

import java.util.Random;

public class CA {
	private long _caKey;
	private int _radius;
	private static Random _keyGen = new Random(System.currentTimeMillis());
	public CA(long key, int radius){
		_caKey = key;
		_radius = radius;
	}
	public static CA newInstance(int radius){
		long caKey = _keyGen.nextLong();
		return new CA(caKey,radius);
	}
	public long getKey(){
		return _caKey;
	}
	public int getRadius(){
		return _radius;
	}
	public int parseRule(long neighbourhoodValue){
		long xOROfNeighbourValWithKey = getKey() ^ neighbourhoodValue;
		return xORBits(xOROfNeighbourValWithKey);
	}
	private int xORBits(long someValue){
		int currentXOR =0;
		while(someValue>0){
			currentXOR = (int)(currentXOR ^ (someValue % 2));
			someValue = someValue / 2;
		}
		return currentXOR;
	}
}
