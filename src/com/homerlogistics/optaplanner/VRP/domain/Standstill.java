package com.homerlogistics.optaplanner.VRP.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

public interface Standstill {

  Location getLocation();

  double getDistanceTo(Standstill standstill);

  Vehicle getVehicle();

  Standstill getNextStandstill();
  void setNextStandstill(Standstill nextStandstill);
}
