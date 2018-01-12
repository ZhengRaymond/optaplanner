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
    List<PickupPoint> pickupList = solution.getPickupList();
    List<DropoffPoint> dropoffList = solution.getDropoffList();

    for (Vehicle vehicle : vehicleList) {
       Standstill ss = vehicle.getAnchor();
       while (ss != null) {
         boolean found = false;
         for (PickupPoint pickup : pickupList) {
           if (pickup.getPreviousStandstill() == ss) {
             softScore -= ss.getDistanceTo(pickup);
             ss = pickup;
             found = true;
             break;
           }
         }
         if (found) continue;
         for (DropoffPoint dropoff : dropoffList) {
           if (dropoff.getPreviousStandstill() == ss) {
             if (dropoff.getSrc().getVehicle() != vehicle) {
               hardScore -= 1;
             }
             softScore -= ss.getDistanceTo(dropoff);
             ss = dropoff;
             found = true;
             break;
           }
         }
         if (found) continue;

         break;
       }
     }
     return HardSoftScore.valueOf(hardScore, softScore);
  }
}
