package com.homerlogistics.optaplanner.TSPDeliver.domain;
import org.optaplanner.core.impl.domain.variable.listener.VariableListener;
import org.optaplanner.core.impl.score.director.ScoreDirector;


public class VehicleListener implements VariableListener<Customer> {
  @Override
  public void beforeEntityAdded(ScoreDirector scoreDirector, Customer customer) {

  }

  @Override
  public void afterEntityAdded(ScoreDirector scoreDirector, Customer customer) {

  }

  @Override
  public void beforeVariableChanged(ScoreDirector scoreDirector, Customer customer) {

  }

  @Override
  public void afterVariableChanged(ScoreDirector scoreDirector, Customer customer) {

  }

  @Override
  public void beforeEntityRemoved(ScoreDirector scoreDirector, Customer customer) {

  }

  @Override
  public void afterEntityRemoved(ScoreDirector scoreDirector, Customer customer) {

  }
}
