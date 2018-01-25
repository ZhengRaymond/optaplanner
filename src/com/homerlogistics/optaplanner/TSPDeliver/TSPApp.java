package com.homerlogistics.optaplanner.TSPDeliver;

import java.util.ArrayList;
import java.util.List;
//import java.util.Collection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.optaplanner.core.impl.score.director.ScoreDirectorFactory;
//import org.optaplanner.core.api.score.constraint.*;
//import org.optaplanner.core.api.score.Score;

import javax.swing.*;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;

public class TSPApp {
  public static final String SOLVER_CONFIG_XML = "com/homerlogistics/optaplanner/TSPDeliver/solver/TSPConfig.xml";
	public static ScoreDirector scoreDirector;

  public static void main(String[] args) {
//		String solutions = "";
//		int[] sizes = {25, 75, 150, 500};
//		int[] numVeh = {2, 4, 5, 6, 7, 8, 10};
//		int[] sizes = {25, 75};
//		int[] numVeh = {2, 6};
//		int[] sizes = {25, 100};
//		int[] numVeh = {1};
//		int[] sizes = {200};
//		int[] numVeh = {8};
//		LocalDateTime currentTime = LocalDateTime.now();
//		System.out.println("Current DateTime: " + currentTime);
//		System.out.println("ETA: " + sizes.length * numVeh.length + " minutes.");
//		for (int size : sizes) {
//			for (int n : numVeh) {
//				solutions += "Size="+size + "NumVehicles="+n + "\n";
//				solutions += solve(size, n);
//				solutions += "\n\n";
//			}
//		}
//		writeFile("output.txt", solutions);
//		System.out.println(solutions);
//		System.out.println("Done.");

		String[] filenames = {
						"./data/TSPDeliver/input_small_2018-01-22T14:01:11.216226.txt",
						"./data/TSPDeliver/input_medium_2018-01-22T14:01:11.216226.txt",
						"./data/TSPDeliver/input_large_2018-01-22T14:01:11.216226.txt"
		};

		String solutions = "";
		for (String filename : filenames) {
			solutions += solve(filename) + "\n";
		}
		System.out.println(solutions);

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

	private static String solve(String filename) {
		SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG_XML);
		Solver solver = solverFactory.buildSolver(); // error
		ScoreDirectorFactory scoreDirectorFactory = solver.getScoreDirectorFactory();
		scoreDirector = scoreDirectorFactory.buildScoreDirector();
		TSPSolution tSPSolution= new TSPSolution();

		List<Customer> customerList = new ArrayList<Customer>();
		int numPackages = 0;
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String srcLine, destLine;
			while((srcLine= bufferedReader.readLine()) != null && (destLine= bufferedReader.readLine()) != null) {
				String [] srcCoord = srcLine.split(" ", 2);
				String [] destCoord = destLine.split(" ", 2);
				Customer source = new Customer(new Location(Integer.parseInt(srcCoord[0]) + Math.random(), Integer.parseInt(srcCoord[1]) + Math.random()), numPackages);
				Customer destination = new Customer(new Location(Integer.parseInt(destCoord[0]) + Math.random(), Integer.parseInt(destCoord[1]) + Math.random()), numPackages);
				source.setDestination(destination);
				destination.setSource(source);
				source.setWeight(5);
				destination.setWeight(5);
				numPackages += 1;
				customerList.add(source);
				customerList.add(destination);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file, " + filename + ".");
		}
		catch(IOException ex) {
			System.out.println("Error reading file, " + filename + ".");
		}

		List<Location> locationList = new ArrayList<Location>();
		for (Customer customer : customerList) {
			locationList.add(customer.getLocation());
		}

		int numVehicles = Math.max(numPackages / 48, 1);
		System.out.println(numVehicles);
		List<Vehicle> vehicleList = new ArrayList<Vehicle>();
		for (int i = 0; i < numVehicles; i++) {
			Vehicle v = new Vehicle();
			v.setLocation(new Location(300, 4000));
			Vehicle v2 = new Vehicle();
			v2.setLocation(new Location(100, 1000));
			vehicleList.add(v);
			vehicleList.add(v2);
		}

		tSPSolution.setLocationList(locationList);
		tSPSolution.setCustomerList(customerList);
		tSPSolution.setVehicleList(vehicleList);
		scoreDirector.setWorkingSolution(tSPSolution);
		solver.solve(tSPSolution);
		TSPSolution bestSolution = (TSPSolution) solver.getBestSolution();
		return bestSolution.toString();
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
//		Collection<ConstraintMatchTotal> constraints = scoreDirector.getConstraintMatchTotals();
//		for (ConstraintMatchTotal constraintMatchTotal : constraints) {
//			String constraintName = constraintMatchTotal.getConstraintName();
//			Score scoreTotal = constraintMatchTotal.getScoreTotal();
//
//			for (ConstraintMatch constraintMatch : constraintMatchTotal.getConstraintMatchSet()) {
//				List<Object> justificationList = constraintMatch.getJustificationList();
//				Score score = constraintMatch.getScore();
//        System.out.println(score.toString());
//			}
//		}
		return bestSolution.toString();
	}

	private static List<Vehicle> initializeVehicles(int size) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for (int i = 0; i < size; i++) {
			Vehicle vehicle = new Vehicle();
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
			Customer source = new Customer(new Location(Math.round(Math.random()*Math.random()*5)*400 + 400 + Math.random()*300, Math.round(Math.random()*5)*800 + 1500 + Math.random()*300), i);
//			Customer source = new Customer(new Location(Math.random() * 3000, Math.random() * 11000), i);
			Customer destination = new Customer(new Location(Math.random() * 3000, Math.random() * 11000), i); //12874
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
