package com.homerlogistics.optaplanner.TSPDeliver.domain;

import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

public class Vehicle implements Standstill {
  protected Location location;
  protected Customer nextCustomer;

  public Location getLocation() { return location; }
  public void setLocation(Location location) { this.location = location; }

  @Override
  public Customer getNextCustomer() { return nextCustomer; }

  @Override
  public void setNextCustomer(Customer nextCustomer) { this.nextCustomer = nextCustomer; }

  @Override
  public Vehicle getVehicle() {
    return this;
  }
}
