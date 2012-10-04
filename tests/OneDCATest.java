package tests;

import static org.junit.Assert.*;
import org.junit.*;
import simulator.OneDCA;


public class OneDCATest {
	@Test
	public void testSimulate1() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDCA someCA = new OneDCA(new int[]{0,1,1,0,1,1,1,0},r);
		int[][] afterOneTurn = someCA.simulate(N, IC, 1);
		int[] predictedNextRow = {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = afterOneTurn[1][cell]==predictedNextRow[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate2() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDCA someCA = new OneDCA(new int[]{0,1,1,0,1,1,1,0},r);
		int[][] after2Turns = someCA.simulate(N, IC, 2);
		int[] predictedRow2 = {0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after2Turns[2][cell]==predictedRow2[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	@Test
	public void testSimulate11() {
		int r = 1;
		int N = 21;
		int[] IC = new int[]{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		OneDCA someCA = new OneDCA(new int[]{0,1,1,0,1,1,1,0},r);
		int[][] after11Turns = someCA.simulate(N, IC, 11);
		int[] predictedRow11 = {1,1,1,0,0,0,1,1,0,1,0,1,1,0,1,1,0,1,1,0,0};
		boolean match = true;
		for(int cell=0;cell<N;cell++){
			if(!(match = after11Turns[11][cell]==predictedRow11[cell])){
				break;
			}
		}
		assertTrue(match);
	}
	public void testSimulate4() {
		fail("Not yet implemeted");
	}
	public void testSimulate5() {
		fail("Not yet implemented");
	}
	public void testSimulate6() {
		fail("Not yet implemented");
	}
	public void testSimulate7() {
		fail("Not yet implemented");
	}
	public void testSimulate8() {
		fail("Not yet implemented");
	}
	public void testSimulate9() {
		fail("Not yet implemented");
	}
	public void testSimulate10() {
		fail("Not yet implemented");
	}
}
