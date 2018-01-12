package com.homerlogistics.optaplanner.VRP.domain;

import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.entity.PlanningEntity;

public class Vehicle implements Standstill {
  protected int capacity;
  protected Anchor anchor;
  protected Standstill nextStandstill;

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Anchor getAnchor() {
    return anchor;
  }

  public void setAnchor(Anchor anchor) {
    this.anchor = anchor;
  }

  @Override
  public Standstill getNextStandstill() { return nextStandstill; }

  @Override
  public void setNextStandstill(Standstill nextStandstill) { this.nextStandstill = nextStandstill; }

  public Vehicle getVehicle() { return this; }
  public Location getLocation() { return anchor.getLocation(); }

  @Override
  public double getDistanceTo(Standstill standstill) {
    return anchor.getDistanceTo(standstill);
  }
}
