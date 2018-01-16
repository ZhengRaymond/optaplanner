package com.homerlogistics.optaplanner.TSPDeliver.domain;

import java.lang.Math;

public class Location {
  protected double x;
  protected double y;

  public Location() {
    System.out.println("Error: default Location constructor.");
  }

  public Location(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getDistance(Location other) {
    double dx = x - other.x;
    double dy = y - other.y;
    return Math.sqrt(dx*dx + dy*dy);
  }

  public String toString() {
    return x + ", " + y;
  }
}
