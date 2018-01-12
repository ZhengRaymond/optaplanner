package com.homerlogistics.optaplanner.VRP.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;

@PlanningEntity
public class PickupPoint extends Location implements Standstill {

  private Standstill previousStandstill;
  private DropoffPoint dest;

  private Standstill nextStandstill;
  private Vehicle vehicle;

  public PickupPoint(int id, double x, double y) {
    super(id, x, y);
  }

  @PlanningVariable( valueRangeProviderRefs = {"VehicleRange", "PickupRange", "DropoffRange"},
          graphType = PlanningVariableGraphType.CHAINED )
  public Standstill getPreviousStandstill() { return previousStandstill; }
  public void setPreviousStandstill(Standstill previousStandstill) { this.previousStandstill = previousStandstill; }

  @AnchorShadowVariable(sourceVariableName = "previousStandstill")
  public Vehicle getVehicle() { return vehicle; }
  public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

  @Override
  public Standstill getNextStandstill() { return nextStandstill; }

  @Override
  public void setNextStandstill(Standstill nextStandstill) { this.nextStandstill = nextStandstill; }

  public DropoffPoint getDest() { return dest; }
  public void setDest(DropoffPoint dest) { this.dest = dest; }

  @Override
  public Location getLocation() {
    return this;
  }

  @Override
  public double getDistanceTo(Standstill standstill) {
    return this.getDistanceTo(standstill.getLocation());
  }
}
