package sim;

import java.util.Random;

public class RuleTableGenerator {
	private Random numberGen;
	public RuleTableGenerator(){
		numberGen = new Random(System.currentTimeMillis());
	}
	public int[] getRuleTable(int radius){
		int exponent = (2*radius + 1) * (2*radius + 1);
		int ruleTableSize = (int)Math.pow(2, exponent);
		int[] newRuleTable = new int[ruleTableSize];
		populate(newRuleTable);
		return newRuleTable;
	}
	public void populate(int[] ruleTable){
		for(int index=0;index<ruleTable.length;index++){
			ruleTable[index]= getValue();
		}
	}
	public int getValue(){
		return Math.abs((numberGen.nextInt())%2);
	}
}
