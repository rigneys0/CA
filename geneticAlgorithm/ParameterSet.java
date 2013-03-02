package geneticAlgorithm;

public class ParameterSet {
	private byte _dimensions;
	private int _latticeSize;
	private byte _turns;
	private byte _states;
	private int _populationSize;
	private int _parameterSpaceSize;
	private byte _radius;
	private byte _groupSize;
	private byte[] _probabilities;
	public ParameterSet(){
		
	}
	public void setDimensions(byte dimensions){
		_dimensions = dimensions;
	}
	public void setGrouping(byte groupSize){
		_groupSize = groupSize;
	}
	public void setLatticeSize(int latticeSize){
		_latticeSize = latticeSize;
	}
	public void setTurns(byte turns){
		_turns = turns;
	}
	public void setStates(byte states){
		_states = states;
	}
	public void setProbabilities(byte[] probabilities){
		_probabilities = probabilities;
	}
	public void setPopulationSize(int populationSize){
		_populationSize = populationSize;
	}
	public void setParameterSpaceSize(int parameterSpaceSize){
		_parameterSpaceSize = parameterSpaceSize;
	}
	public void setRadius(byte radius){
		_radius = radius;
	}
	public byte[] getProbabilities(){
		return _probabilities;
	}
	public byte getRadius(){
		return _radius;
	}
	public byte getStates(){
		return _states;
	}
	public int getLatticeSize(){
		return _latticeSize;
	}
	public byte getTurns(){
		return _turns;
	}
	public int getPopulationSize(){
		return _populationSize;
	}
	public int getParameterSpaceSize(){
		return _parameterSpaceSize;
	}
	public byte getDimensions(){
		return _dimensions;
	}
	public byte getGrouping(){
		return _groupSize;
	}
	
}
