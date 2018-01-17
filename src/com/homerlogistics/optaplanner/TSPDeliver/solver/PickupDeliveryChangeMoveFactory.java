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

    for (int i = 3; i < customerList.size(); i++) {
      Customer source = customerList.get(i);
      for (int j = 3; j < customerList.size(); j++) {
        Customer target = customerList.get(j);
        if (i != j) {
          moveList.add(new PickupDeliveryChangeMove(source, target));
        }
      }
    }

    return moveList;
  }
}