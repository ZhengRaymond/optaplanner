package com.homerlogistics.optaplanner.TSPDeliver;

import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.optaplanner.core.impl.score.director.ScoreDirectorFactory;

import javax.swing.*;


import com.homerlogistics.optaplanner.TSPDeliver.domain.*;

public class TSPApp {
  public static final String SOLVER_CONFIG_XML = "com/homerlogistics/optaplanner/TSPDeliver/solver/TSPConfig.xml";
	public static ScoreDirector scoreDirector;

  public static void main(String[] args) {
		SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG_XML);
		Solver solver = solverFactory.buildSolver(); // error
		ScoreDirectorFactory scoreDirectorFactory = solver.getScoreDirectorFactory();
		scoreDirector = scoreDirectorFactory.buildScoreDirector();
		TSPSolution tSPSolution= new TSPSolution();
		System.out.println("A");
		scoreDirector.setWorkingSolution(tSPSolution);
		System.out.println("B");
		solver.solve(tSPSolution);
		System.out.println("C");
		TSPSolution bestSolution = (TSPSolution) solver.getBestSolution();
		System.out.println(bestSolution.getScore().toString());
		System.out.println(bestSolution.toString());

//		JFrame f = new JFrame();
//		f.setSize(400,400);
//		f.setLocation(200,200);
//		f.setVisible(true);
	}
}
