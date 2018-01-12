package com.homerlogistics.optaplanner.VRP.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;

@PlanningEntity
public interface Standstill {

  Location getLocation();

  double getDistanceTo(Standstill standstill);

  @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
  Standstill getNextStandstill();
  void setNextStandstill(Standstill nextStandstill);
}
