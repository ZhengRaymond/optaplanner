package com.homerlogistics.optaplanner.TSPDeliver;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
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
		String solutions = "";
//		int[] sizes = {25, 75, 150, 500};
//		int[] numVeh = {2, 4, 5, 6, 7, 8, 10};

//		int[] sizes = {25, 75};
//		int[] numVeh = {2, 6};

//		int[] sizes = {25, 100};
//		int[] numVeh = {1};

		int[] sizes = {30};
		int[] numVeh = {4};



//		Location l1 = new Location(17, 22);
//		Location l2 = new Location(3, 4);
//		System.out.println(l1.getDistance(l2));
//		try {
//			TimeUnit.SECONDS.sleep(15);
//		}
//		catch(Exception e) {
//
//		}


		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("Current DateTime: " + currentTime);
		System.out.println("ETA: " + sizes.length * numVeh.length + " minutes.");
		for (int size : sizes) {
			for (int n : numVeh) {
				solutions += "Size="+size + "NumVehicles="+n + "\n";
				solutions += solve(size, n);
				solutions += "\n\n";
			}
		}
//		writeFile("output.txt", solutions);
		System.out.println(solutions);
		System.out.println("Done.");
	}

	public static void writeFile(String filename, String solutions) {
  	try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(solutions);
			writer.close();
		}
		catch (IOException ie) {
  		ie.printStackTrace();
		}
	}

	private static String solve(int size, int numVeh) {
		SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG_XML);
		Solver solver = solverFactory.buildSolver(); // error
		ScoreDirectorFactory scoreDirectorFactory = solver.getScoreDirectorFactory();
		scoreDirector = scoreDirectorFactory.buildScoreDirector();
		TSPSolution tSPSolution= new TSPSolution();

		List<Customer> customerList = initializeCustomers(size);
		List<Location> locationList = initializeLocations(customerList);
		List<Vehicle> vehicleList = initializeVehicles(numVeh);

		tSPSolution.setLocationList(locationList);
		tSPSolution.setCustomerList(customerList);
		tSPSolution.setVehicleList(vehicleList);
		scoreDirector.setWorkingSolution(tSPSolution);
		solver.solve(tSPSolution);
		TSPSolution bestSolution = (TSPSolution) solver.getBestSolution();
		return bestSolution.toString();
	}

	private static List<Vehicle> initializeVehicles(int size) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for (int i = 0; i < size; i++) {
			Vehicle vehicle = new Vehicle();
//			vehicle.setLocation(new Location(Math.random() * 50, Math.random() * 50));
			vehicle.setLocation(new Location(1500, 5500));
			vehicles.add(vehicle);
		}
		return vehicles;
	}

	private static List<Customer> initializeCustomers(int size) {
  	List<Customer> customerList = new ArrayList<Customer>();
  	for (int i = 0; i < size; i++) {
//			double x = ((int) (Math.random() * 30)) * 100 + Math.random();
//			double y = ((int) (Math.random() * 110)) * 100 + Math.random();
//			Customer source = new Customer(new Location(x, y), i);
//			Customer source = new Customer(new Location(Math.floor(Math.random() * 50), Math.floor(Math.random() * 50)), i);
			Customer source = new Customer(new Location(Math.round(Math.random()) * 2000 + 2000 + Math.random() * 300, Math.round(Math.random()) * 2000 + 2000 + Math.random() * 300), i);
//			Customer source = new Customer(new Location(Math.random() * 3000, Math.random() * 11000), i);
			Customer destination = new Customer(new Location(Math.random() * 6000, Math.random() * 11000), i); //12874
//			Customer destination = new Customer(new Location(Math.floor(Math.random() * 50), Math.floor(Math.random() * 50)), i);//12874
			source.setDestination(destination);
//			source.setWeight(Math.random() * 5 + 5);
			source.setWeight(5);
			destination.setSource(source);
//			destination.setWeight(Math.random() * 5 + 5);
			destination.setWeight(5);
			customerList.add(source);
			customerList.add(destination);
		}
		return customerList;
	}

	private static List<Location> initializeLocations(List<Customer> customerList) {
		List<Location> locations = new ArrayList<Location>();
		for (Customer customer : customerList) {
			locations.add(customer.getLocation());
		}
		return locations;
	}
}
