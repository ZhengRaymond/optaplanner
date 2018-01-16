package com.homerlogistics.optaplanner.TSPDeliver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public abstract class Standstill {
  protected Location location;
  protected Standstill next;
  protected Standstill prev;

  @InverseRelationShadowVariable(sourceVariableName = "prev")
  public abstract Standstill getNext();
  public abstract void setNext(Standstill next);

  @PlanningVariable(valueRangeProviderRefs = {"customerRange", "vehicleRange"},
          graphType = PlanningVariableGraphType.CHAINED)
  public abstract Standstill getPrev();
  public abstract void setPrev(Standstill prev);

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public double getDistance(Standstill other) {
    return location.getDistance(other.location);
  }
}
