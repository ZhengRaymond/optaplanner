package com.homerlogistics.optaplanner.TSP.domain;

public class Anchor extends City {

  private City previousLocation;

  public Anchor(int id, double x, double y) {
    super(id, x, y);
    this.previousLocation = null;
  }
}
