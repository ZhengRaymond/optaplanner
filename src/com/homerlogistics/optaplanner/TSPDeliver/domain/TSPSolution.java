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

  protected List<Location> locationList;
  protected List<Vehicle> vehicleList;
  protected List<Customer> customerList;

  private HardSoftScore score;

  @ProblemFactCollectionProperty
  public List<Location> getLocationList() {
    return locationList;
  }

  public void setLocationList(List<Location> locationList) {
    this.locationList = locationList;
  }

  @PlanningEntityCollectionProperty
  @ValueRangeProvider(id = "vehicleRange")
  public List<Vehicle> getVehicleList() {
    return vehicleList;
  }

  public void setVehicleList(List<Vehicle> vehicleList) {
    this.vehicleList = vehicleList;
  }

  @PlanningEntityCollectionProperty
  @ValueRangeProvider(id = "customerRange")
  public List<Customer> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Customer> customerList) {
    this.customerList = customerList;
  }

  @PlanningScore
  public HardSoftScore getScore() {
    return score;
  }

  public void setScore(HardSoftScore score) {
    this.score = score;
  }




  public String toString() {
    String sol = score.toString() + "\n";
    for (Vehicle vehicle : vehicleList) {
      String s = "";
      String x = "" + (int) vehicle.getLocation().getX();
      String y = "" + (int) vehicle.getLocation().getY();
      String id = "-1";
      String isSource = "2";
      Location curr = vehicle.getLocation();
      Customer next = vehicle.getNextCustomer();
      int carrying = 0;
      while (next != null) {
        curr = next.getLocation();
        x += "_" + Math.round(curr.getX());
        y += "_" + Math.round(curr.getY());
        if (next.getDestination() != null) {
          isSource += "_1";
          carrying += next.getWeight();
        }
        else {
          isSource += "_0";
          carrying -= next.getWeight();
        }
        id += "_" + next.getId();
        next = next.getNextCustomer();
      }
      s += x + "_" + y + "_" + id + "_" + isSource;
      sol += s + "   ";
    }
    return sol;
  }

}

