package com.homerlogistics.optaplanner.TSPDeliver.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.InverseRelationShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public interface Standstill {

  /**
   * @return never null
   */
  Location getLocation();

  /**
   * @return sometimes null
   */
  Vehicle getVehicle();

  /**
   * @return sometimes null
   */
  @InverseRelationShadowVariable(sourceVariableName = "previousStandstill")
  Customer getNextCustomer();
  void setNextCustomer(Customer nextCustomer);

  Standstill getPreviousStandstill();
  void setPreviousStandstill(Standstill previousStandstill);

}
