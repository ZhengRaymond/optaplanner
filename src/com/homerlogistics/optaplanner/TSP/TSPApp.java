package com.homerlogistics.optaplanner.TSP;

import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.optaplanner.core.impl.score.director.ScoreDirectorFactory;

import com.homerlogistics.optaplanner.TSP.domain.*;

public class TSPApp {
  public static final String SOLVER_CONFIG_XML = "com/homerlogistics/optaplanner/TSP/solver/TSPConfig.xml";
	public static ScoreDirector scoreDirector;

  public static void main(String[] args) {
		SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG_XML);
		Solver solver = solverFactory.buildSolver();
		ScoreDirectorFactory scoreDirectorFactory = solver.getScoreDirectorFactory();
		scoreDirector = scoreDirectorFactory.buildScoreDirector();

		TSPSolution tSPSolution= new TSPSolution();
		scoreDirector.setWorkingSolution(tSPSolution);
		solver.solve(tSPSolution);
		TSPSolution bestSolution = (TSPSolution) solver.getBestSolution();
		System.out.println(bestSolution.getScore().toString());
		System.out.println(bestSolution.toString());
	}
}
