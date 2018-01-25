package com.homerlogistics.optaplanner.TSPDeliver.solver.persistence;

import com.homerlogistics.optaplanner.TSPDeliver.domain.Customer;
import com.homerlogistics.optaplanner.TSPDeliver.domain.Location;
import com.homerlogistics.optaplanner.TSPDeliver.domain.TSPSolution;
import com.homerlogistics.optaplanner.TSPDeliver.domain.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TspImporter {

  public TspImporter() {

  }

  public TSPSolution readSolution(File file) {
    TSPSolution solution = new TSPSolution();

    List<Customer> customerList = new ArrayList<Customer>();
    int numPackages = 0;
    try {
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String srcLine, destLine;
      while ((srcLine = bufferedReader.readLine()) != null && (destLine = bufferedReader.readLine()) != null) {
        String[] srcCoord = srcLine.split(" ", 2);
        String[] destCoord = destLine.split(" ", 2);
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
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file.");
    } catch (IOException ex) {
      System.out.println("Error reading file.");
    }

    List<Location> locationList = new ArrayList<Location>();
    for (Customer customer : customerList) {
      locationList.add(customer.getLocation());
    }

    int numVehicles = Math.max((int) numPackages / 32 , 1);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    for (int i = 0; i < 2; i++) {
      Vehicle v = new Vehicle();
      v.setLocation(new Location(300, 4000));
      Vehicle v2 = new Vehicle();
      v2.setLocation(new Location(100, 1000));
      vehicleList.add(v);
      vehicleList.add(v2);
    }
//    Vehicle v = new Vehicle();
//    v.setLocation(new Location(300, 4000));
//    vehicleList.add(v);


    solution.setLocationList(locationList);
    solution.setCustomerList(customerList);
    solution.setVehicleList(vehicleList);
    return solution;
  }

}



