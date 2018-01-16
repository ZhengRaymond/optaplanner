package com.homerlogistics.optaplanner.TSP.domain;

import java.lang.Math;

public class Location {
  protected double x;
  protected double y;

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
}
