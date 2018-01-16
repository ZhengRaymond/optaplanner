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
  private List<Point> pointList;
  private List<Vehicle> vehicleList; // the solution
//  private List<Standstill> trailList;
  private HardSoftScore score;

  /**************** Constructor ****************/
  public VRPSolution() { }

  /**************** Getters and Setters ****************/
  @ProblemFactProperty
  public Anchor getAnchor() { return anchor; }
  public void setAnchor(Anchor anchor) { this.anchor = anchor; }

  @PlanningEntityCollectionProperty
  @ValueRangeProvider(id = "PointRange")
  public List<Point> getPointList() { return pointList; }
  public void setPointList(List<Point> pointList) { this.pointList= pointList; }

//  @PlanningEntityCollectionProperty
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
  @ProblemFactCollectionProperty
  @ValueRangeProvider(id = "AnchorRange")
  public List<Anchor> getAnchorRange() { return Collections.singletonList(anchor); }

  public String toString() {
//    String x = "x = [";
//    String y = "y = [";
//    x += "]; ";
//    y += "]; ";
//    String s = x + y + "plot(x, y);";
    String s = "";
    int count = 1;
    for (Vehicle vehicle : vehicleList) {
      s += "Vehicle"  + count + ": ";
      count++;
      Standstill ss = vehicle.getAnchor();
      while (ss != null) {
        boolean found = false;
        for (Point point : pointList) {
          if (ss == point.getPreviousStandstill()) {
            s += point.getLocation().toString() + ",";
            ss = point;
            found = true;
          }
        }
        if (!found) break;
      }
      s += "\n";
    }
    System.out.println("Score: " + score.toString());
    return s;
  }

  private double distance(Location a, Location b) {
    double deltaX = a.x - b.x;
    double deltaY = a.y - b.y;
    return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
  }

}
