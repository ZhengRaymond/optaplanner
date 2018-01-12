package com.homerlogistics.optaplanner.VRP.domain;

import java.util.Collections;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;


@PlanningSolution
public class VRPSolution {

  /**************** Fields ****************/
  private Anchor anchor;
  private List<PickupPoint> pickupList;
  private List<DropoffPoint> dropoffList;
  private List<Vehicle> vehicleList; // the solution
//  private List<Standstill> trailList;
  private HardSoftScore score;

  /**************** Constructor ****************/
  public VRPSolution() { }

  /**************** Getters and Setters ****************/
  @ProblemFactProperty
  public Anchor getAnchor() { return anchor; }
  public void setAnchor(Anchor anchor) { this.anchor = anchor; }

  @ProblemFactCollectionProperty
  @ValueRangeProvider(id = "PickupRange")
  public List<PickupPoint> getPickupList() { return pickupList; }
  public void setPickupList(List<PickupPoint> pickupList) { this.pickupList = pickupList; }

  @ProblemFactCollectionProperty
  @ValueRangeProvider(id = "DropoffRange")
  public List<DropoffPoint> getDropoffList() { return dropoffList; }
  public void setDropoffList(List<DropoffPoint> dropoffList) { this.dropoffList = dropoffList; }

  @PlanningEntityCollectionProperty
  @ValueRangeProvider(id = "VehicleRange")
  public List<Vehicle> getVehicleList() { return vehicleList; }
  public void setVehicleList(List<Vehicle> vehicleList) { this.vehicleList = vehicleList; }

//  @PlanningEntityCollectionProperty
//  @ValueRangeProvider(id = "TrailRange")
//  public List<Standstill> getTrailList() { return trailList; }
//  public void setTrailList(List<Standstill> trailList) { this.trailList = trailList; }

  @PlanningScore
  public HardSoftScore getScore() { return score; }
  public void setScore(HardSoftScore score) { this.score = score; }

  /**************** ValueRangeProviders ****************/
  @ValueRangeProvider(id = "AnchorRange")
  public List<Anchor> getAnchorRange() { return Collections.singletonList(anchor); }

  public String toString() {
    String x = "x = [";
    String y = "y = [";
    // ...
    // ...
    // ...
    x += "]; ";
    y += "]; ";
    String s = x + y + "plot(x, y);";
    return s;
  }

  private double distance(Location a, Location b) {
    double deltaX = a.x - b.x;
    double deltaY = a.y - b.y;
    return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
  }

}
