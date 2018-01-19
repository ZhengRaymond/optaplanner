package com.homerlogistics.optaplanner.TSPDeliver.domain;

import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

import java.lang.Math;

public class Location {
  protected double x;
  protected double y;
//  protected Location source;
//  protected Location destination;
//  protected Vehicle vehicle;

  public Location() {
    System.out.println("Error: default Location constructor.");
  }

  public Location(double x, double y) {
//    this.source = null;
//    this.destination = null;
    this.x = x;
    this.y = y;
  }

//  @CustomShadowVariable(variableListenerClass = VehicleListener.class,
//          sources = {@PlanningVariableReference(variableName = "vehicle")})
//  @InverseRelationShadowVariable(sourceVariableName = "vehicle")
//  public Vehicle getVehicle() {
//    return vehicle;
//  }
//
//  public void setVehicle(Vehicle vehicle) {
//    this.vehicle = vehicle;
//  }2200+5100

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getDistance(Location other) {
    double dx = x - other.x;
    double dy = y - other.y;
    return Math.sqrt(dx*dx + dy*dy);
  }


//  public Location getSource() {
//    return source;
//  }
//
//  public void setSource(Location source) {
//    this.source = source;
//  }
//
//  public Location getDestination() {
//    return destination;
//  }
//
//  public void setDestination(Location destination) {
//    this.destination = destination;
//  }

  public String toString() {
    return x + ", " + y;
  }
}
