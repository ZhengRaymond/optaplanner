package com.homerlogistics.optaplanner.TSPDeliver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

import com.homerlogistics.optaplanner.TSPDeliver.solver.CustomerDifficultyWeightFactory;

@PlanningEntity(difficultyWeightFactoryClass = CustomerDifficultyWeightFactory.class)
public class Customer implements Standstill {
  protected Location location;
  protected Standstill previousStandstill;
  protected Customer nextCustomer;
  protected Vehicle vehicle;
  protected double weight;
  protected int id;

  private Customer source;
  private Customer destination;

  public Customer() {
    this.source = null;
    this.destination = null;
  }

  public Customer(Location location, int id) {
    this.source = null;
    this.destination = null;
    this.location = location;
    this.id = id;
  }

  @Override
  @ProblemFactProperty
  public Location getLocation() { return location; }

  public void setLocation(Location location) { this.location = location; }

  @PlanningVariable(valueRangeProviderRefs = {"vehicleRange", "customerRange"},
          graphType = PlanningVariableGraphType.CHAINED)
  public Standstill getPreviousStandstill() { return previousStandstill; }

  public void setPreviousStandstill(Standstill previousStandstill) { this.previousStandstill = previousStandstill; }

  @Override
  public Customer getNextCustomer() {
    return nextCustomer;
  }

  @Override
  public void setNextCustomer(Customer nextCustomer) {
    this.nextCustomer = nextCustomer;
  }

  @Override
  @AnchorShadowVariable(sourceVariableName = "previousStandstill")
  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }


  /* My getters and setters: */
  @ProblemFactProperty
  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  @ProblemFactProperty
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Customer getSource() {
    return source;
  }

  public void setSource(Customer source) {
    this.source = source;
  }

  public Customer getDestination() {
    return destination;
  }

  public void setDestination(Customer destination) {
    this.destination = destination;
  }

}
