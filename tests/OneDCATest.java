package tests;

import static org.junit.Assert.*;
import org.junit.*;

import simulator.CA;
import simulator.OneDSimulator;


public class OneDCATest {
	@Test
	public void testSimulate2_110() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDSimulator someCA = new OneDSimulator(new CA(110,r));
		int[][] afterTwoTurns = someCA.simulate(N, IC, 2);
		int[] predictedRow2 = {0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = afterTwoTurns[2][cell]==predictedRow2[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate1_110() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDSimulator someCA = new OneDSimulator(new CA(110,r));
		int[][] after1Turns = someCA.simulate(N, IC, 1);
		int[] predictedRow1 = {1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after1Turns[1][cell]==predictedRow1[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate5_110() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDSimulator someCA = new OneDSimulator(new CA(110,r));
		int[][] after5Turns = someCA.simulate(N, IC, 5);
		int[] predictedRow5 = {1,1,1,1,1,0,0,0,1,0,0,0,1,0,0,0,1,1,1,1,1};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after5Turns[5][cell]==predictedRow5[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate11_110() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDSimulator someCA = new OneDSimulator(new CA(110,r));
		int[][] after11Turns = someCA.simulate(N, IC, 11);
		int[] predictedRow11 = {1,1,0,1,0,0,1,0,0,1,0,1,0,0,1,0,0,1,0,1,1};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after11Turns[11][cell]==predictedRow11[cell])){
				break;
			}
		}
		assertTrue(match);
	}@Test
	public void testSimulate14_110() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDSimulator someCA = new OneDSimulator(new CA(110,r));
		int[][] after14Turns = someCA.simulate(N, IC, 14);
		int[] predictedRow14 = {0,1,1,1,1,0,0,0,1,0,1,0,1,0,0,0,1,1,1,1,0};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after14Turns[14][cell]==predictedRow14[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate1_85() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDSimulator someCA = new OneDSimulator(new CA(85,r));
		int[][] after1Turn = someCA.simulate(N, IC, 1);
		int[] predictedRow1 = {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after1Turn[1][cell]==predictedRow1[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate5_85() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDSimulator someCA = new OneDSimulator(new CA(85,r));
		int[][] after5Turns = someCA.simulate(N, IC, 5);
		int[] predictedRow5 = {0,0,0,0,0,1,1,1,0,1,1,1,0,1,1,1,0,0,0,0,0};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after5Turns[5][cell]==predictedRow5[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate11_85() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDSimulator someCA = new OneDSimulator(new CA(85,r));
		int[][] after11Turns = someCA.simulate(N, IC, 11);
		int[] predictedRow11 = {1,1,0,1,0,0,1,0,0,1,0,1,0,0,1,0,0,1,0,1,1};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after11Turns[11][cell]==predictedRow11[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate14_85() {
		fail("Not yet implemented");
	}
}
