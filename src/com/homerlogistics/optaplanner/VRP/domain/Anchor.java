package com.homerlogistics.optaplanner.VRP.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public class Anchor extends Location implements Standstill {

  protected Location location;
  protected Standstill nextStandstill;
  protected Standstill previousStandstill;

  public Anchor() { super(-100, 0, 0); }

  public Anchor(int id, double x, double y) {
    super(id, x, y);
  }


  public Location getLocation() {
    return location;
  }

  public double getDistanceTo(Standstill standstill) {
    return location.getDistanceTo(standstill.getLocation());
  }

  @Override
  public Vehicle getVehicle() { return null; }

  @Override
  @PlanningVariable( valueRangeProviderRefs = {"VehicleRange", "PointRange"},
          graphType = PlanningVariableGraphType.CHAINED )
  public Standstill getNextStandstill() {
    return nextStandstill;
  }

  @Override
  public void setNextStandstill(Standstill nextStandstill) {
    this.nextStandstill = nextStandstill;
  }
}
