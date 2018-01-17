package com.homerlogistics.optaplanner.TSPDeliver.solver;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

import java.util.List;
import java.util.ArrayList;

public class PickupDeliveryChangeMoveFactory implements MoveListFactory<TSPSolution> {

  @Override
  public List<PickupDeliveryChangeMove> createMoveList(TSPSolution solution) {
    List<PickupDeliveryChangeMove> moveList = new ArrayList<>();

    List<Customer> customerList = solution.getCustomerList();
    for (Customer customer : customerList) {
      for (Customer target : customerList) {
        if (customer != target) {
          moveList.add(new PickupDeliveryChangeMove(customer, target));
        }
      }
    }

    return moveList;
  }
}