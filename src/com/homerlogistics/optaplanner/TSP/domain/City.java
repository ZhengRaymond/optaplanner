package com.homerlogistics.optaplanner.TSP.domain;

import java.lang.Math.*;

public class City {

  private int id;
  private double x;
  private double y;

  public City() {
    this.id = -1;
    this.x = 0;
    this.y = 0;
  }

  public City(int id, double x, double y) {
    this.id = id;
    this.x = x;
    this.y = y;
  }

  public int getId() { return id; }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}
