package com.homerlogistics.optaplanner.VRP.score;

import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import com.homerlogistics.optaplanner.VRP.domain.*;

public class VRPScore implements EasyScoreCalculator<VRPSolution> {

  public HardSoftScore calculateScore(VRPSolution solution) {
     int hardScore = 0;
     int softScore = 0;
    List<Vehicle> vehicleList = solution.getVehicleList();
    List<Point> pointList = solution.getPointList();

    for (Vehicle vehicle : vehicleList) {
       Standstill ss = vehicle.getAnchor();
       while (ss != null) {
         boolean found = false;
         for (Point point : pointList) {
           if (point.getPreviousStandstill() == ss) {
             softScore -= ss.getDistanceTo(point);
             ss = point;
             found = true;
             Point src = point.getSource();
             if (src != null) {
               if (src.getVehicle() == point.getVehicle()) {
                 // success
               }
               else {
                 hardScore -= 1;
               }
             }
             break;
           }
         }
         if (!found) break;
       }
     }
     return HardSoftScore.valueOf(hardScore, softScore);
  }
}
