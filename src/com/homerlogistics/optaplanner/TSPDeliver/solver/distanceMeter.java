package com.homerlogistics.optaplanner.TSPDeliver.solver;

import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;
import com.homerlogistics.optaplanner.TSPDeliver.domain.*;

public class distanceMeter implements NearbyDistanceMeter<Customer, Standstill> {
  @Override
  public double getNearbyDistance(Customer customer, Standstill standstill) {
    return customer.getLocation().getDistance(standstill.getLocation());
  }
}
