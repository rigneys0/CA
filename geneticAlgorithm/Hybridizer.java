package geneticAlgorithm;

import simulator.CA;

public class Hybridizer {
	public Hybridizer(){
		
	}
	public void rank(CA[] CAs, byte iterations){
		
	}
	private void halfAssedBubbleSort(CA[] automatons){
		for(int index=0; index<automatons.length -2; index++){
			if(automatons[index].problemsSolved() > automatons[index+1].problemsSolved()){
				CA temp = automatons[index];
				automatons[index] = automatons[index+1];
				automatons[index+1] = temp;
			}
		}
	}
}
