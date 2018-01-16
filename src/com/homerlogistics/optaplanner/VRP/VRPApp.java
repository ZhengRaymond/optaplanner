package com.homerlogistics.optaplanner.VRP;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.optaplanner.core.impl.score.director.ScoreDirectorFactory;

import com.homerlogistics.optaplanner.VRP.domain.*;

public class VRPApp {
	public static final String SOLVER_CONFIG_XML = "com/homerlogistics/optaplanner/VRP/solver/VRPConfig.xml";
	public static ScoreDirector scoreDirector;

	public static void main(String[] args) {
		SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG_XML);
		Solver solver = solverFactory.buildSolver();
		ScoreDirectorFactory scoreDirectorFactory = solver.getScoreDirectorFactory();
		scoreDirector = scoreDirectorFactory.buildScoreDirector();

		int size = 20;
		int numberOfVehicles = 3;

		List<Point> pointList = initialize(size);
		Anchor anchor = new Anchor();
		VRPSolution solution = new VRPSolution();
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		for (int i = 0; i < numberOfVehicles; i++) {
			Vehicle vehicle = new Vehicle();
			vehicle.setNextStandstill(null);
			vehicle.setAnchor(anchor);
			vehicleList.add(vehicle);
		}
		solution.setAnchor(anchor);
		solution.setPointList(pointList);
		solution.setVehicleList(vehicleList);

		scoreDirector.setWorkingSolution(solution);
		solver.solve(solution);
		VRPSolution bestSolution = (VRPSolution) solver.getBestSolution();
		System.out.println(bestSolution.toString());
	}

	private static List<Point> initialize(int size) {
		List<Point> pointList = new ArrayList<Point>();
		for (int i = 0; i < size; i++) {
			Point src = new Point(i , Math.random() * 500, Math.random() * 8000);
			Point dest = new Point(i + size, Math.random() * 500, Math.random() * 8000);
			src.setNextStandstill(null);
			dest.setNextStandstill(null);
			src.setDestination(dest);
			dest.setSource(src);
			pointList.add(src);
			pointList.add(dest);
		}
		return pointList;
	}
}
