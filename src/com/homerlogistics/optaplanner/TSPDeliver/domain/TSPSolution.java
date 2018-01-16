package com.homerlogistics.optaplanner.TSPDeliver.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;


@PlanningSolution
public class TSPSolution {

  private List<Customer> customerList; // the solution

  private Vehicle vehicle;
  private HardSoftScore score;

  public TSPSolution() {
    this.vehicle = new Vehicle();
    this.customerList = initialize(5);
  }

  @PlanningEntityCollectionProperty
  @ValueRangeProvider(id = "customerRange")
  public List<Customer> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Customer> customerList) {
    this.customerList = customerList;
  }

  @ValueRangeProvider(id = "vehicleRange")
  public List<Vehicle> getVehicleRange() {
    List<Vehicle> vehicleRange = new ArrayList<Vehicle>();
    vehicleRange.add(this.vehicle);
    return vehicleRange;
  }

  @ProblemFactProperty
  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  @PlanningScore
  public HardSoftScore getScore() {
    return score;
  }

  public void setScore(HardSoftScore score) {
    this.score = score;
  }

  public String toString() {
    String s = "plt.plot(";
    String x = "[0";
    String y = "[0";
    String sx = "[0";
    String sy = "[0";
    for (Customer customer : customerList) {
      Location loc = customer.getLocation();
      x += "," + Math.round(loc.getX());
      y += "," + Math.round(loc.getY());
      if (customer.getDestination() != null) {
        sx += "," + Math.round(loc.getX());
        sy += "," + Math.round(loc.getY());
      }
    }
    x += "]";
    y += "]";
    sx += "]";
    sy += "]";
    s += x + "," + y + "," + "'-ro'" + "," + sx + "," + sy + "," + "'bo'" + "," +  "markersize=13" + ")";
    s += "\nplt.gca().set_aspect('equal', adjustable='box')\nplt.show()";

    return s;
  }

  private List<Customer> initialize(int size) {
    List<Customer> customers = new ArrayList<Customer>();
    Customer lastDest = null;
    for (int i = 0; i < size; i++) {
      Location location = new Location(Math.random() * 8000, Math.random() * 8000);
      Location location2 = new Location(Math.random() * 8000, Math.random() * 8000);
      Customer source = new Customer(location);
      Customer destination = new Customer(location2);
      source.setDestination(destination);
      destination.setSource(source);

      if (lastDest != null) {
        lastDest.setNext(source);
        source.setPrev(lastDest);
      }
      else {
        source.setPrev(null);
      }
      source.setNext(destination);
      destination.setPrev(source);
      lastDest = destination;

      customers.add(source);
      customers.add(destination);
    }
    lastDest.setNext(null);
    return customers;
  }

}

