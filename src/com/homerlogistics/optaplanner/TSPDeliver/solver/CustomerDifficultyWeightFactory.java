package com.homerlogistics.optaplanner.TSPDeliver.solver;

import org.apache.commons.lang3.builder.CompareToBuilder;
import com.homerlogistics.optaplanner.TSPDeliver.domain.*;
import org.optaplanner.core.impl.heuristic.selector.common.decorator.SelectionSorterWeightFactory;

public class CustomerDifficultyWeightFactory implements SelectionSorterWeightFactory<TSPSolution, Customer> {

  @Override
  public CustomerDifficultyWeight createSorterWeight(TSPSolution tspSolution, Customer customer) {
    return new CustomerDifficultyWeight(customer);
  }

  public static class CustomerDifficultyWeight implements Comparable<CustomerDifficultyWeight> {

    private final Customer customer;
    private final Location depot;
    private final double depotAngle;
    private final double depotRoundTripDistance;

    public CustomerDifficultyWeight(Customer customer) {
      this.customer = customer;
      this.depotAngle = Math.atan2(customer.getLocation().getX(), customer.getLocation().getY());
      this.depot = new Location(0, 0);
      Customer dest = customer.getDestination();
      if (dest != null) {
        Location c = customer.getLocation();
        Location d = dest.getLocation();
        this.depotRoundTripDistance = this.depot.getDistance(c) + c.getDistance(d) + d.getDistance(this.depot);
      }
      else {
        Location s = customer.getSource().getLocation();
        Location c = customer.getLocation();
        this.depotRoundTripDistance = this.depot.getDistance(s) + s.getDistance(c) + c.getDistance(this.depot);
      }
    }

    @Override
    public int compareTo(CustomerDifficultyWeight other) {
      return new CompareToBuilder()
              .append(depotAngle, other.depotAngle)
              .append(depotRoundTripDistance, other.depotRoundTripDistance) // Ascending (further from the depot are more difficult)
              .toComparison();
    }

  }
}
