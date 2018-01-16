package com.homerlogistics.optaplanner.TSPDeliver.score;

import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;

public class TSPScore implements EasyScoreCalculator<TSPSolution> {

  public HardSoftScore calculateScore(TSPSolution solution) {
    int hardScore = 0;
    int softScore = 0;

    List<Customer> customerList = solution.getCustomerList();
    Standstill current = solution.getVehicle();
    Customer next = (Customer) current.getNext();
    while (next != null) {
      if (next.getSource() != null) {
        // next customer is a delivery exchange
        if (next.getVehicle() != next.getSource().getVehicle()) {
          hardScore -= 1;
        }
      }
      softScore -= current.getDistance(next);
      current = next;
      next = (Customer) next.getNext();
    }

    HardSoftScore score = HardSoftScore.valueOf(hardScore, softScore);
    System.out.println("Intermediate step: ");
    System.out.println("Score: hard/soft = " + score.toString());
    System.out.println("Solution: " + solution.toString());
    System.out.println("");

    return score;
  }
}
