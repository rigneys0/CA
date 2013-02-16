package geneticAlgorithm;

import java.util.Set;
import java.util.TreeSet;

import simulator.CA;

public class Hybridizer {
	public Hybridizer(){
		
	}
	public void rank(TreeSet<CA> CAs, byte keepZone){
		int size = CAs.size();
		int topThird = size/3;
	}
	private Set<CA> getTopThird(TreeSet<CA> CAs){
		CA template = new CA(0);
		template.solvesProblem();
		Set<CA> tailSet = CAs.tailSet(template);
		template.resetProblemsSolved();
		tailSet.remove(template);
		return tailSet;
	}
	public static void main(String[] args){
		Hybridizer h = new Hybridizer();
		TreeSet<CA> someCAs = new TreeSet<CA>();
		CA ca1 = new CA(0);
		CA ca2 = new CA(0);
		CA ca3 = new CA(0);
		CA ca4 = new CA(0);
		ca4.solvesProblem();
		ca4.solvesProblem();
		ca4.solvesProblem();
		CA ca5 = new CA(0);
		ca5.solvesProblem();
		ca5.solvesProblem();
		ca5.solvesProblem();
		ca5.solvesProblem();
		CA ca6 = new CA(0);
		ca6.solvesProblem();
		ca6.solvesProblem();
		CA ca7 = new CA(0);
		ca7.solvesProblem();
		ca7.solvesProblem();
		ca7.solvesProblem();
		ca7.solvesProblem();
		ca7.solvesProblem();
		CA ca8 = new CA(0);
		ca8.solvesProblem();
		someCAs.add(ca1);
		someCAs.add(ca2);
		someCAs.add(ca3);
		someCAs.add(ca4);
		someCAs.add(ca5);
		someCAs.add(ca6);
		someCAs.add(ca7);
		someCAs.add(ca8);
		Set<CA> = 
	}
	
}
