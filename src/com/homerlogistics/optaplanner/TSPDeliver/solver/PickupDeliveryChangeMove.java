package com.homerlogistics.optaplanner.TSPDeliver.solver;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class PickupDeliveryChangeMove extends AbstractMove<TSPSolution> {

  private Customer customer;
  private Standstill target;
  private Standstill destination;
  private Standstill best;
  private List<Standstill> changedEntities;
  private List<Standstill> changedValues;

  public PickupDeliveryChangeMove(Customer customer, Customer target) {
    this.customer = customer;
    this.target = target;
    this.destination = customer.getDestination();
    this.best = null;
    changedEntities = new ArrayList<Standstill>();
    changedValues = new ArrayList<Standstill>();
  }

  public PickupDeliveryChangeMove(Customer customer, Standstill target, Standstill best) {
    this.customer = customer;
    this.target = target;
    this.destination = customer.getDestination();
    this.best = best;
    changedEntities = new ArrayList<Standstill>();
    changedValues = new ArrayList<Standstill>();
  }

  @Override
  protected AbstractMove<TSPSolution> createUndoMove(ScoreDirector<TSPSolution> scoreDirector) {
    return new PickupDeliveryChangeMove(customer, customer.getPreviousStandstill(), customer.getDestination().getPreviousStandstill());
  }

  @Override
  protected void doMoveOnGenuineVariables(ScoreDirector<TSPSolution> scoreDirector) {
    System.out.println("doMoveOnGenuineVariables");
    // remove customer
    delete(customer, scoreDirector);

    // add customer
    insert(customer, target, scoreDirector);

    // remove customer correspondent
    delete(destination, scoreDirector);

    // add customer correspondent
    Customer curr = customer;
    if (best == null) {
      double bestDistance = 999999999;
      Customer newBest = null;
      while (curr != null) {
        double distance = curr.getLocation().getDistance(destination.getLocation());
        if (curr.getNextCustomer() != null) {
          distance += curr.getNextCustomer().getLocation().getDistance(destination.getLocation());
          distance -= curr.getLocation().getDistance(curr.getNextCustomer().getLocation());
        }
        if (distance < bestDistance) {
          bestDistance = distance;
          newBest = curr;
        }
        curr = curr.getNextCustomer();
      }
      this.best = newBest;
    }
    insert(destination, this.best, scoreDirector);
  }

  @Override
  public boolean isMoveDoable(ScoreDirector<TSPSolution> scoreDirector) {
    return (customer.getPreviousStandstill() != target &&
            destination != null);
  }

  @Override
  public Collection<?> getPlanningEntities() {
    return changedEntities;
  }

  @Override
  public Collection<?> getPlanningValues() {
    return changedValues;
  }


  /* Helpers */

  private void insert(Standstill source, Standstill target, ScoreDirector scoreDirector) {
    Customer next = target.getNextCustomer();
    if (next != null) {
      scoreDirector.beforeVariableChanged(source, "nextCustomer");
      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      next.setPreviousStandstill(source);
      source.setNextCustomer(next);
      changedEntities.add(next);
      changedEntities.add(source);
      changedValues.add(source);
      changedValues.add(next);
      scoreDirector.afterVariableChanged(next, "previousStandstill");
      scoreDirector.afterVariableChanged(source, "nextCustomer");
    }

    scoreDirector.beforeVariableChanged(target, "nextCustomer");
    scoreDirector.beforeVariableChanged(source, "previousStandstill");
    source.setPreviousStandstill(target);
    target.setNextCustomer((Customer) source);
    changedEntities.add(source);
    changedEntities.add(target);
    changedValues.add(target);
    changedValues.add(source);
    scoreDirector.afterVariableChanged(source, "previousStandstill");
    scoreDirector.afterVariableChanged(target, "nextCustomer");
  }

  private void delete(Standstill source, ScoreDirector scoreDirector) {
    Customer next = source.getNextCustomer();
    Standstill previous = source.getPreviousStandstill();
    if (previous != null && next != null) {
      scoreDirector.beforeVariableChanged(source, "nextCustomer");
      scoreDirector.beforeVariableChanged(source, "previousStandstill");
      scoreDirector.beforeVariableChanged(previous, "nextCustomer");
      scoreDirector.beforeVariableChanged(next, "previousStandstill");

      next.setPreviousStandstill(previous);
      previous.setNextCustomer(next);
      source.setPreviousStandstill(null);
      source.setNextCustomer(null);
      changedEntities.add(next);
      changedEntities.add(previous);
      changedEntities.add(source);
      changedEntities.add(source);
      changedValues.add(previous);
      changedValues.add(next);
      changedValues.add(null);
      changedValues.add(null);

      scoreDirector.afterVariableChanged(next, "previousStandstill");
      scoreDirector.afterVariableChanged(previous, "nextCustomer");
      scoreDirector.afterVariableChanged(source, "previousStandstill");
      scoreDirector.afterVariableChanged(source, "nextCustomer");
    }
    else if (next != null) {
      scoreDirector.beforeVariableChanged(source, "nextCustomer");
      scoreDirector.beforeVariableChanged(next, "previousStandstill");

      source.setNextCustomer(null);
      next.setPreviousStandstill(null);
      changedEntities.add(source);
      changedEntities.add(next);
      changedValues.add(null);
      changedValues.add(null);

      scoreDirector.afterVariableChanged(next, "previousStandstill");
      scoreDirector.afterVariableChanged(source, "nextCustomer");
    }
    else if (previous != null) {
      scoreDirector.beforeVariableChanged(source, "previousStandstill");
      scoreDirector.beforeVariableChanged(previous, "nextCustomer");

      previous.setNextCustomer(null);
      source.setPreviousStandstill(null);
      changedEntities.add(previous);
      changedEntities.add(source);
      changedValues.add(null);
      changedValues.add(null);

      scoreDirector.afterVariableChanged(previous, "nextCustomer");
      scoreDirector.afterVariableChanged(source, "previousStandstill");
    }
  }
}
