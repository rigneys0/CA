package probabilityModel;

import java.util.Random;

public class BitModel implements Model{
	private Random _numberGen;
	private int _numberOfStates;
	public BitModel(int numberOfStates){
		_numberOfStates=numberOfStates;
		_numberGen = new Random(System.currentTimeMillis());
	}
	@Override
	public int nextDigit() {
		return (int)(Math.abs(_numberGen.nextLong())%_numberOfStates);
	}
}
