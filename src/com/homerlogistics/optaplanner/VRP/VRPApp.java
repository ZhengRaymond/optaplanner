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

		List<PickupPoint> pickupList = initializePickups(size);
		List<DropoffPoint> dropoffList = initializeDropoffs(size);
		Anchor anchor = new Anchor();
		VRPSolution solution = new VRPSolution();
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		for (int i = 0; i < numberOfVehicles; i++) {
			Vehicle vehicle = new Vehicle();
			vehicle.setAnchor(anchor);
			vehicleList.add(vehicle);
		}
		solution.setAnchor(anchor);
		solution.setPickupList(pickupList);
		solution.setDropoffList(dropoffList);
		solution.setVehicleList(vehicleList);

		scoreDirector.setWorkingSolution(solution);
		solver.solve(solution);
		VRPSolution bestSolution = (VRPSolution) solver.getBestSolution();
		System.out.println(bestSolution.toString());
	}

	private static List<PickupPoint> initializePickups(int size) {
		List<PickupPoint> pickupList = new ArrayList<PickupPoint>();
		for (int i = 0; i < size; i++) {
			pickupList.add(new PickupPoint(i, Math.random() * 500, Math.random() * 8000));
		}
		return pickupList;
	}

	private static List<DropoffPoint> initializeDropoffs(int size) {
		List<DropoffPoint> dropoffList = new ArrayList<DropoffPoint>();
		for (int i = 0; i < size; i++) {
			dropoffList.add(new DropoffPoint(i + size,Math.random() * 500, Math.random() * 8000));
		}
		return dropoffList;
	}
}
