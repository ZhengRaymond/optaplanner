package com.homerlogistics.optaplanner.helloworld;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.optaplanner.core.impl.score.director.ScoreDirectorFactory;

import com.homerlogistics.optaplanner.helloworld.domain.HelloworldSolution;

public class HelloworldApp {
  public static final String SOLVER_CONFIG_XML = "com/homerlogistics/optaplanner/helloworld/solver/helloworldConfig.xml";
	public static ScoreDirector scoreDirector;

  public static void main(String[] args) {
		SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG_XML);
		Solver solver = solverFactory.buildSolver();
		ScoreDirectorFactory scoreDirectorFactory = solver.getScoreDirectorFactory();
		scoreDirector = scoreDirectorFactory.buildScoreDirector();

		HelloworldSolution helloSolution = new HelloworldSolution();
		scoreDirector.setWorkingSolution(helloSolution);

		solver.solve(helloSolution);
		HelloworldSolution bestSolution = (HelloworldSolution) solver.getBestSolution();
    System.out.println(bestSolution.toString());
	}
}
