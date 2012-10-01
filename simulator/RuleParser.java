package simulator;

public class RuleParser {
	public static int parseRule(CA automaton, long neighbourhoodValue){
		int xorOfCAKey = xORBits(automaton.getKey());
		int xorOfNeighbourhoodValue = xORBits(neighbourhoodValue);
		return xorOfCAKey ^ xorOfNeighbourhoodValue;
	}
	public static int xORBits(long someValue){
		int currentXOR =0;
		while(someValue>0){
			currentXOR = (int)(currentXOR ^ (someValue % 2));
			someValue = someValue / 2;
		}
		return currentXOR;
	}
}
