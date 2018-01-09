package com.homerlogistics.optaplanner.helloworld.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class HelloEntity {

    private int count;

    public HelloEntity() {
      /* do nothing */
    }

    public HelloEntity(int count) {
      this.count = count;
    }

    @PlanningVariable(valueRangeProviderRefs = { "helloCountRange" })
    public Integer getCount() {
      return count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }
}
