package com.homerlogistics.optaplanner.TSPDeliver.domain;

import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

public class Vehicle extends Standstill {
  public Vehicle() {
    this.location = new Location(0, 0);
  }

  @Override
  public Standstill getNext() {
    return this.next;
  }

  @Override
  public void setNext(Standstill next) {
    this.next = next;
  }

  @Override
  public Standstill getPrev() {
    return this;
  }

  @Override
  public void setPrev(Standstill prev) {
    System.out.println("Error: Attempting to setPrev of Vehicle");
  }
}
