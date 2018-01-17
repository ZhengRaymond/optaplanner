package com.homerlogistics.optaplanner.TSPDeliver.solver;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class PickupDeliveryChangeMove extends AbstractMove<TSPSolution> {

  private Customer customer;
  private Customer target;
  private Customer destination;
  private Customer best;

  public PickupDeliveryChangeMove(Customer customer, Customer target) {
    this.customer = customer;
    this.target = target;
    this.destination = customer.getDestination();
    this.best = null;
  }

  public PickupDeliveryChangeMove(Customer customer, Customer target, Customer best) {
    this.customer = customer;
    this.target = target;
    this.destination = customer.getDestination();
    this.best = best;
  }

  @Override
  protected AbstractMove<TSPSolution> createUndoMove(ScoreDirector<TSPSolution> scoreDirector) {
    return new PickupDeliveryChangeMove(customer, (Customer) customer.getPreviousStandstill(), (Customer) customer.getDestination().getPreviousStandstill());
  }

  @Override
  protected void doMoveOnGenuineVariables(ScoreDirector<TSPSolution> scoreDirector) {
    Customer next;

    // remove customer
    next = customer.getNextCustomer();
    if (next != null) {
      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      next.setPreviousStandstill(customer.getPreviousStandstill());
      scoreDirector.afterVariableChanged(next, "previousStandstill");
    }

    // add customer
    next = target.getNextCustomer();
    if (next != null) {
      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      next.setPreviousStandstill(customer);
      scoreDirector.afterVariableChanged(next, "previousStandstill");
    }
    scoreDirector.beforeVariableChanged(customer, "previousStandstill");
    customer.setPreviousStandstill(target);
    scoreDirector.afterVariableChanged(customer, "previousStandstill");

    // remove customer correspondent
    next = destination.getNextCustomer();
    if (next != null) {
      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      next.setPreviousStandstill(destination.getPreviousStandstill());
      scoreDirector.afterVariableChanged(next, "previousStandstill");
    }

    // add customer correspondent
    Customer curr = customer;
    if (best == null) {
      double bestDistance = 999999999;
      while (curr != null) {
        double distance = curr.getLocation().getDistance(destination.getLocation());
        if (curr.getNextCustomer() != null) {
          distance += curr.getNextCustomer().getLocation().getDistance(destination.getLocation());
        }
        if (distance < bestDistance) {
          bestDistance = distance;
          best = curr;
        }
        curr = curr.getNextCustomer();
      }
    }
    this.best = best;
    next = best.getNextCustomer();
    if (next != null) {
      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      next.setPreviousStandstill(destination);
      scoreDirector.afterVariableChanged(next, "previousStandstill");
    }
    scoreDirector.beforeVariableChanged(destination, "previousStandstill");
    destination.setPreviousStandstill(best);
    scoreDirector.beforeVariableChanged(destination, "previousStandstill");
  }

  @Override
  public boolean isMoveDoable(ScoreDirector<TSPSolution> scoreDirector) {
    return (customer.getPreviousStandstill() != target &&
            destination != null);
  }

  @Override
  public Collection<?> getPlanningEntities() {
    List<Customer> changedEntities = new ArrayList<Customer>();
    changedEntities.add(customer);
    changedEntities.add(destination);
    changedEntities.add(target.getNextCustomer());
    changedEntities.add(best.getNextCustomer());
    return changedEntities;
  }

  @Override
  public Collection<?> getPlanningValues() {
    List<Standstill> changedValues = new ArrayList<Standstill>();
    changedValues.add(customer.getPreviousStandstill());
    changedValues.add(destination.getPreviousStandstill());
    changedValues.add(target);
    changedValues.add(best);
    return changedValues;
  }
}
