package com.homerlogistics.optaplanner.TSPDeliver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public class Customer extends Standstill {
  private Customer source;
  private Customer destination;
  private Vehicle vehicle;

  public Customer() {
//    System.out.println("Error: Default Customer constructor.");
  }

  public Customer(Location location) {
    this.location = location;
    this.source = null;
    this.destination = null;
  }

  @AnchorShadowVariable(sourceVariableName = "prev")
  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  @ProblemFactProperty
  public Customer getSource() {
    return source;
  }

  public void setSource(Customer source) {
    this.source = source;
  }

  @ProblemFactProperty
  public Customer getDestination() {
    return destination;
  }

  public void setDestination(Customer destination) {
    this.destination = destination;
  }

  @Override
  public Standstill getNext() {
    return next;
  }

  @Override
  public void setNext(Standstill next) {
    this.next = next;
  }

  @Override
  @PlanningVariable(valueRangeProviderRefs = {"customerRange", "vehicleRange"},
          graphType = PlanningVariableGraphType.CHAINED)
  public Standstill getPrev() {
    return prev;
  }

  @Override
  public void setPrev(Standstill prev) {
    this.prev = prev;
  }

  @Override
  public Location getLocation() {
    return location;
  }
}
