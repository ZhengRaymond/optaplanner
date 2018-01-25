package com.homerlogistics.optaplanner.TSPDeliver.solver;

import com.homerlogistics.optaplanner.TSPDeliver.domain.Customer;
import com.homerlogistics.optaplanner.TSPDeliver.domain.Standstill;
import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;

public class nearbyDistanceMeterChange implements NearbyDistanceMeter<Customer, Standstill> {
  @Override
  public double getNearbyDistance(Customer customer, Standstill standstill) {
    return customer.getLocation().getDistance(standstill.getLocation()) + 10000 * (customer.getSource() != null ? 1 : 0);
  }
}
