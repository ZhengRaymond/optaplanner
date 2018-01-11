package com.homerlogistics.optaplanner.TSP.tweaking;

import com.homerlogistics.optaplanner.TSP.domain.*;
import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;

public class TrailNearbyDistanceMeter implements NearbyDistanceMeter<Trail, City> {

  @Override
  public double getNearbyDistance(Trail trail, City city) {
    double d1 = trail.getX() - city.getX();
    double d2 = trail.getY() - city.getY();
    return Math.sqrt(d1 * d1 + d2 * d2);
  }
}
