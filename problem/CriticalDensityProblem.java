package problem;

public class CriticalDensityProblem implements Problem<String>{

	private String _solutionPattern;
	public CriticalDensityProblem(){
		_solutionPattern = "1";
	}
	@Override
	public String getSolutionToAchieve() {
		return _solutionPattern;
	}
}
