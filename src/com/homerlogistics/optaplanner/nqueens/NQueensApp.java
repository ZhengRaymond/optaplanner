package com.homerlogistics.optaplanner.nqueens;

import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.optaplanner.core.impl.score.director.ScoreDirectorFactory;

import com.homerlogistics.optaplanner.nqueens.domain.NQueensSolution;

public class NQueensApp {
  public static final String SOLVER_CONFIG_XML = "com/homerlogistics/optaplanner/nqueens/solver/nQueensConfig.xml";
	public static ScoreDirector scoreDirector;

	private NQueensSolution createNQueens(int n) {
		NQueensSolution nQueensSolution = new NQueensSolution(n);
		return nQueensSolution;
	}

  public static void main(String[] args) {
		SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG_XML);
		Solver solver = solverFactory.buildSolver();
		ScoreDirectorFactory scoreDirectorFactory = solver.getScoreDirectorFactory();
		scoreDirector = scoreDirectorFactory.buildScoreDirector();

		NQueensSolution nQueensSolution = new NQueensSolution();
		scoreDirector.setWorkingSolution(nQueensSolution);
		solver.solve(nQueensSolution);
		NQueensSolution bestSolution = (NQueensSolution) solver.getBestSolution();
    System.out.println(bestSolution.toString());
	}
}
