package simulator;

import problem.Problem;

public interface SimulatorAdapter {
	public int[][] getNextImageOutput();
	public void run(int turns, int latticeSize, CA automata);
	public boolean solvesProblem(Problem<String> p); 
}
