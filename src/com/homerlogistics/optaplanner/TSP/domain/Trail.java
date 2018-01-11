package com.homerlogistics.optaplanner.TSP.domain;

import java.lang.Math;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public class Trail extends City {

  private City previousCity;

  public Trail() { }

  public Trail(City previousCity, int id, double x, double y) {
    super(id, x, y);
    this.previousCity = previousCity;
  }

  @PlanningVariable(
          valueRangeProviderRefs = { "AnchorRange", "TrailRange" },
          graphType = PlanningVariableGraphType.CHAINED)
  public City getPreviousCity() {
    return previousCity;
  }

  public void setPreviousCity(City previousCity) {
    this.previousCity = previousCity;
  }

  public double getDistance() {
    if (previousCity == null) {
      return 0L;
    }
    return euclidean(previousCity.getX(), previousCity.getY(), this.getX(), this.getY());
  }

  private double euclidean(double x1, double y1, double x2, double y2) {
    double d1 = x1 - x2;
    double d2 = y1 - y2;
    return Math.sqrt(d1 * d1 + d2 * d2);
  }
}
