package probabilityModel;

import java.util.Random;
/* This class models a probability distribution that is more likely to create
 * "groups" of the same digit of a certain size, than digits singularly, this size
 *  is specified in the constructor.
 */
public class GroupModel implements Model{
	private Random _stateGenerator;
	private int _numberOfStates;
	private int _groupSize;
	private long[] _factorialTable;
	private byte _lastGenerated;
	private int _numberGenerated;
	public GroupModel(int numberOfStates, int groupSize){
		_numberOfStates=numberOfStates;
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
		return (byte)Math.abs(_stateGenerator.nextInt()%_numberOfStates);
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
		GroupModel g = new GroupModel(2,1);
		for(int j=0;j<60;j++){
			System.out.print(g.nextDigit());
		}
	}
}
