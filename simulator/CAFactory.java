package simulator;
import java.util.Random;
public class CAFactory {
	Random keyGen;
	private CAFactory(){
		keyGen = new Random(System.currentTimeMillis());
	}
	public CA createCA(){
		long caKey = keyGen.nextLong();
		return new CA(caKey);
	}
}
