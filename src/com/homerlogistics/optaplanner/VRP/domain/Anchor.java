package com.homerlogistics.optaplanner.VRP.domain;

public class Anchor implements Standstill {

  protected Location location;
  protected Standstill nextStandstill;

  public Anchor() { }

  public Anchor(Location location) {
    this.location = location;
  }


  public Location getLocation() {
    return location;
  }

  public double getDistanceTo(Standstill standstill) {
    return location.getDistanceTo(standstill.getLocation());
  }

  @Override
  public Standstill getNextStandstill() {
    return nextStandstill;
  }

  @Override
  public void setNextStandstill(Standstill nextStandstill) {
    this.nextStandstill = nextStandstill;
  }
}
