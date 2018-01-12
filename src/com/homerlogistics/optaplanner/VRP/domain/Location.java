package com.homerlogistics.optaplanner.VRP.domain;

public abstract class Location {

  protected int id;
  protected double x;
  protected double y;

  public Location() { }

  public Location(int id, double x, double y) {
    this.id = id;
    this.x = x;
    this.y = y;
  }

  public double getDistanceTo(Location location) {
    double dx = this.x - location.x;
    double dy = this.y - location.y;
    return Math.sqrt(dx*dx + dy*dy);
  }
}
