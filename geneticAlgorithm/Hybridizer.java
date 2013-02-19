package geneticAlgorithm;

import java.util.Random;
import java.util.TreeSet;

import simulator.CA;

public class Hybridizer {
	private int _keepZone;
	private Random _randGen;
	public Hybridizer(byte keepZone){
		_keepZone = keepZone;
		_randGen = new Random(System.currentTimeMillis());
	}
	public TreeSet<CA> rank(TreeSet<CA> CAs){
		TreeSet<CA> nonZeroList = getNonZero(CAs);
		return getTopValues(nonZeroList);
	}
	private TreeSet<CA> getTopValues(TreeSet<CA> CAs){
		if(CAs.size()<= _keepZone){
			return CAs;
		}
		System.out.println("QQ"+(CAs.size() - _keepZone));
		int numberToRemove = CAs.size()-_keepZone;
		for(int index=0; index<numberToRemove;index++){
			System.out.println(CAs.remove(CAs.first()));
			//someCA = CAs.iterator().next();
		}
		return CAs;
	}
	private TreeSet<CA> getNonZero(TreeSet<CA> CAs){
		CA template = new CA((byte)2);
		template.solvesProblem();
		CAs.remove(template);
		return CAs;
	}
	public CA[] CrossBreed(CA mother, CA father, int offSpring){
		CA[] children = new CA[offSpring];
		byte[] motherKey = mother.getKey();
		byte[] fatherKey = father.getKey();
		for(int index=0; index<offSpring; index++){
			children[index]= new CA((byte)2);
			cross(children[index].getKey(), motherKey, fatherKey);
			mutate(children[index].getKey());
		}
		return children;
	}
	private void cross(byte[] childKey, byte[] motherKey, 
			byte[] fatherKey){
		for(int index=0; index<childKey.length; index++){
			childKey[index] = (byte) (motherKey[index]^fatherKey[index]);
		}
	}
	private void mutate(byte[] childKey){
		byte position = (byte)(_randGen.nextInt() % 8);
		byte base = 0b1;
		byte mask = (byte)(base << position);
		for(byte index=0; index<position; index++){
			childKey[index] = (byte)(childKey[index] ^ mask);
		}
	}
	public static void main(String[] args){
		Hybridizer h = new Hybridizer((byte)2);
		TreeSet<CA> someCAs = new TreeSet<CA>();
		CA ca1 = new CA((byte)2);
		CA ca2 = new CA((byte)2);
		CA ca3 = new CA((byte)2);
		CA ca4 = new CA((byte)2);
		ca4.solvesProblem();
		ca4.solvesProblem();
		ca4.solvesProblem();
		CA ca5 = new CA((byte)2);
		ca5.solvesProblem();
		ca5.solvesProblem();
		ca5.solvesProblem();
		ca5.solvesProblem();
		CA ca6 = new CA((byte)2);
		ca6.solvesProblem();
		ca6.solvesProblem();
		CA ca7 = new CA((byte)2);
		ca7.solvesProblem();
		ca7.solvesProblem();
		ca7.solvesProblem();
		ca7.solvesProblem();
		ca7.solvesProblem();
		CA ca8 = new CA((byte)2);
		ca8.solvesProblem();
		someCAs.add(ca1);
		someCAs.add(ca2);
		someCAs.add(ca3);
		someCAs.add(ca4);
		someCAs.add(ca5);
		someCAs.add(ca6);
		someCAs.add(ca7);
		someCAs.add(ca8);
		TreeSet<CA> top= h.rank(someCAs);
		for(CA c : top){
			System.out.println(c.problemsSolved());
		}
	}
	
}
