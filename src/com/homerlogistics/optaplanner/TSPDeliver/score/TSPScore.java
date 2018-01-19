package com.homerlogistics.optaplanner.TSPDeliver.score;

import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;

public class TSPScore implements EasyScoreCalculator<TSPSolution> {

  public HardSoftScore calculateScore(TSPSolution solution) {
    int hardScore = 0;
    int softScore = 0;
    int capacity = 16;

    List<Vehicle> vehicleList = solution.getVehicleList();
    int maxDistance = 0;
    for (Vehicle vehicle : vehicleList) {
      int delivCount = 0;
      int distance = 0;
      Customer cust = vehicle.getNextCustomer();
      Location lastLoc = vehicle.getLocation();
//      Location lastLoc = null;
      int carrying = 0;
      while (cust != null) {
        delivCount+= 1;
        if (lastLoc != null) {
          distance += cust.getLocation().getDistance(lastLoc);
        }
        Customer src = cust.getSource();
        if (src != null) {
          Customer current = src;
          boolean found = false;
          while (current != null) {
            if (current.getLocation() == cust.getLocation()) {
              found = true;
              break;
            }
            current = current.getNextCustomer();
          }
          if (!found) hardScore -= 100;
//           if (src.getVehicle() != src.getDestination().getVehicle()) {
//            hardScore -= 1;
          // }
          carrying -= cust.getWeight();
        }
        else {
          carrying += cust.getWeight();
        }
        if (carrying > capacity) {
          hardScore -= (carrying - capacity);
        }
        lastLoc = cust.getLocation();
        cust = cust.getNextCustomer();
      }
      if (delivCount < 1) {
        hardScore -= 10000;
      }
      if (distance > maxDistance) {
        maxDistance = distance;
      }
      softScore -= distance;
    }
//    softScore -= maxDistance;

    HardSoftScore score = HardSoftScore.valueOf(hardScore, softScore);
    return score;
  }
}
