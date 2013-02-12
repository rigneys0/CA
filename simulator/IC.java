package simulator;

public class IC {
	private byte[] _oneDimensionIC;
	private byte[][] _twoDimensionIC;
	private byte[][][] _threeDimensionIC;
	private byte[][][][] _fourDimensionIC;
	public IC(){
		
	}
	public void set1D(byte[] oneDimensionIC){
		_oneDimensionIC = oneDimensionIC;
	}
	public void set2D(byte[][] twoDimensionIC){
		_twoDimensionIC = twoDimensionIC;
	}
	public void set3D(byte[][][] threeDimensionIC){
		_threeDimensionIC = threeDimensionIC;
	}
	public void set4D(byte[][][][] fourDimensionIC){
		_fourDimensionIC = fourDimensionIC;
	}
	public byte[] getOneDimensionIC(){
		return _oneDimensionIC;
	}
	public byte[][] getTwoDimensionIC(){
		return _twoDimensionIC;
	}
	public byte[][][] getThreeDimensionIC(){
		return _threeDimensionIC;
	}
	public byte[][][][] getFourDimensionIC(){
		return _fourDimensionIC;
	}
}
