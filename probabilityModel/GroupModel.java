package probabilityModel;

import java.util.Random;
/* This class models a probability distribution that is more likely to create
 * "groups" of the same digit of a certain size, than digits singularly, this size
 *  is specified in the constructor.
 */
public class GroupModel implements Model{
	private Random _stateGenerator;
	private byte _numberOfStates;
	private byte _groupSize;
	private long[] _factorialTable;
	private byte _lastGenerated;
	private int _numberGenerated;
	private byte[] _probabilities;
	public GroupModel(byte numberOfStates, byte groupSize,
			byte[] probabilities){
		_numberOfStates=numberOfStates;		
		_probabilities = probabilities;
		_stateGenerator = new Random(System.currentTimeMillis());
		_groupSize = groupSize;
		_lastGenerated = getRandomState();
		_numberGenerated=1;
		_factorialTable=buildTable(groupSize);

	}//constructor
	private long[] buildTable(int size){
		long[] factorialTable = new long[size];
		factorialTable[0]=1;
		for(int index=1;index<size;index++){
			factorialTable[index]=index*factorialTable[index-1];
		}//for
		return factorialTable;
	}//buildTable
	
	private byte getRandomState(){
		return _probabilities[
		            Math.abs(_stateGenerator.nextInt())%_probabilities.length];
	}//getRandomState
	
	private long nChooseR(int r){
		long coefficient = _factorialTable[_groupSize-1] / 
				(_factorialTable[r] * _factorialTable[(_groupSize-r)-1]);
		return coefficient;
	}//nChooseR
	
	@Override
	public byte nextDigit(){
		  //probability of another number
		double probabilityOfOtherValue = 1/((double)getRandomState());
		 //probability of repeating previous value
		double ncR = ((double)nChooseR((_numberGenerated+1)%_groupSize));
		double probabilityOfSameValue = ncR/((_numberOfStates));
		if(probabilityOfOtherValue >= probabilityOfSameValue){
			byte randomState = getRandomState();
			_numberGenerated=1;
			_lastGenerated=randomState;
		}//if
		else{
			_numberGenerated=(_numberGenerated+1)%_groupSize;
		}//else
		return _lastGenerated;
	}//nextDigit
	
	public void reset(){
		_numberGenerated=1;
		_lastGenerated=getRandomState();
	}//reset
	public static void main(String[] args){
		GroupModel g = new GroupModel((byte)2,(byte)1, new byte[]{1,0,0});
		for(int j=0;j<60;j++){
			System.out.print(g.nextDigit());
		}
	}
}
