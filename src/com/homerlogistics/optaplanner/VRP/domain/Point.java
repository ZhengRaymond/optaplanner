package com.homerlogistics.optaplanner.VRP.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;

@PlanningEntity
public class Point extends Location implements Standstill {

  protected Standstill previousStandstill;
  protected Standstill nextStandstill;
  protected Vehicle vehicle;

  protected Point source;
  protected Point destination;

  public Point() {
    super(-1, 0, 0);
  }

  public Point(int id, double x, double y) {
    super(id, x, y);
  }

  @PlanningVariable( valueRangeProviderRefs = {"VehicleRange", "PointRange", "AnchorRange"},
          graphType = PlanningVariableGraphType.CHAINED )
  public Standstill getPreviousStandstill() { return previousStandstill; }
  public void setPreviousStandstill(Standstill previousStandstill) { this.previousStandstill = previousStandstill; }

  @Override
  @AnchorShadowVariable(sourceVariableName = "previousStandstill")
  public Vehicle getVehicle() { return vehicle; }
  public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

  @Override
  public Standstill getNextStandstill() { return nextStandstill; }

  @Override
  public void setNextStandstill(Standstill nextStandstill) { this.nextStandstill = nextStandstill; }

  public Point getSource() {
    return source;
  }

  public void setSource(Point source) {
    this.source = source;
  }

  public Point getDestination() {
    return destination;
  }

  public void setDestination(Point destination) {
    this.destination = destination;
  }


  @Override
  public Location getLocation() {
    return this;
  }

  @Override
  public double getDistanceTo(Standstill standstill) {
    return this.getDistanceTo(standstill.getLocation());
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }
}
