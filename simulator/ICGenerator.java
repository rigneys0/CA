package simulator;
import probabilityModel.Model;

public class ICGenerator{
	private Model _model;
	public ICGenerator(Model probModel){
		_model=probModel;
	}
	public byte[] getIC1D(int latticeSize){
		byte[] newIC = new byte[latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	public byte[][] getIC2D(int latticeSize){
		byte[][] newIC = new byte[latticeSize][latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	public byte[][][] getIC3D(int latticeSize){
		byte[][][] newIC = new byte[latticeSize][latticeSize][latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	public byte[][][][] getIC4D(int latticeSize){
		byte[][][][] newIC = new byte[latticeSize][latticeSize][latticeSize][latticeSize];
		populate(newIC,latticeSize);
		return newIC;
	}
	private void populate(byte[] IC, int latticeSize){
		for(int cols=0;cols<latticeSize;cols++){
			IC[cols]=getValue();
		}
	}
	private void populate(byte[][] IC, int latticeSize){
		for(int rows=0;rows<latticeSize;rows++){
			for(int cols=0;cols<latticeSize;cols++){
				IC[rows][cols]=getValue();
			}
		}
	}
	private void populate(byte[][][] IC, int latticeSize){
		for(int planes=0; planes<latticeSize;planes++){
			for(int rows=0;rows<latticeSize;rows++){
				for(int cols=0;cols<latticeSize;cols++){
					IC[planes][rows][cols]=getValue();
				}
			}
		}
	}
	private void populate(byte[][][][] IC, int latticeSize){
		for(int cubes=0;cubes<latticeSize;cubes++){
			for(int planes=0; planes<latticeSize;planes++){
				for(int rows=0;rows<latticeSize;rows++){
					for(int cols=0;cols<latticeSize;cols++){
						IC[cubes][planes][rows][cols]=getValue();
					}
				}
			}
		}
	}
	public byte getValue(){
		return _model.nextDigit();
	}
}
