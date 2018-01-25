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
  private Customer destination;
  private Standstill best;
  private List<Standstill> changedEntities;
  private List<Standstill> changedValues;

  public PickupDeliveryChangeMove(Customer customer, Standstill target) {
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

  public void validate(Customer customer, String id) {
//    System.out.println("Validating system, Point " + id + ":");
    Standstill ss = customer.getVehicle();
    while (ss != null) {
      Standstill next = ss.getNextCustomer();
      if (next == null) break;
      Standstill nextPrevious = next.getPreviousStandstill();
      if (ss != nextPrevious) {
        System.out.println("Error, current != current.next.previous.");
      }
      ss = next;
    }
//    System.out.println("Done Validating system, Point " + id + ".");
  }

  @Override
  public boolean isMoveDoable(ScoreDirector<TSPSolution> scoreDirector) {
    return (customer != null && target != null &&
            customer.getPreviousStandstill() != target &&
            destination != null &&  customer.getPreviousStandstill() != null && target.getPreviousStandstill() != null);
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

  private void insert(Customer source, Standstill target, ScoreDirector scoreDirector) {
    Customer next = target.getNextCustomer();
    if (next != null) {
      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      scoreDirector.beforeVariableChanged(target, "nextCustomer");
      next.setPreviousStandstill(null);
      target.setNextCustomer(null);
      scoreDirector.afterVariableChanged(next, "previousStandstill");
      scoreDirector.afterVariableChanged(target, "nextCustomer");

      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      scoreDirector.beforeVariableChanged(source, "nextCustomer");
      next.setPreviousStandstill(source);
      source.setNextCustomer(next);
      scoreDirector.afterVariableChanged(next, "previousStandstill");
      scoreDirector.afterVariableChanged(source, "nextCustomer");

      scoreDirector.beforeVariableChanged(source, "previousStandstill");
      scoreDirector.beforeVariableChanged(target, "nextCustomer");
      source.setPreviousStandstill(target);
      target.setNextCustomer(source);
      scoreDirector.afterVariableChanged(source, "previousStandstill");
      scoreDirector.afterVariableChanged(target, "nextCustomer");

//      changedEntities.add(next);
//      changedEntities.add(source);
//      changedValues.add(source);
//      changedValues.add(next);
    }
    else {
      scoreDirector.beforeVariableChanged(source, "previousStandstill");
      scoreDirector.beforeVariableChanged(target, "nextCustomer");
      source.setPreviousStandstill(target);
      target.setNextCustomer(source);
      scoreDirector.afterVariableChanged(source, "previousStandstill");
      scoreDirector.afterVariableChanged(target, "nextCustomer");
    }

//    changedEntities.add(source);
//    changedEntities.add(target);
//    changedValues.add(target);
//    changedValues.add(source);
  }

  private void delete(Standstill source, ScoreDirector scoreDirector) {
    Customer next = source.getNextCustomer();
    Standstill previous = source.getPreviousStandstill();
    if (previous != null && next != null) {
      scoreDirector.beforeVariableChanged(source, "previousStandstill");
      scoreDirector.beforeVariableChanged(previous, "nextCustomer");
      source.setPreviousStandstill(null);
      previous.setNextCustomer(null);
      scoreDirector.afterVariableChanged(source, "previousStandstill");
      scoreDirector.afterVariableChanged(previous, "nextCustomer");

      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      scoreDirector.beforeVariableChanged(previous, "nextCustomer");
      next.setPreviousStandstill(previous);
      previous.setNextCustomer(next);
      scoreDirector.afterVariableChanged(next, "previousStandstill");
      scoreDirector.afterVariableChanged(previous, "nextCustomer");

//      changedEntities.add(next);
//      changedEntities.add(previous);
//      changedEntities.add(source);
//      changedEntities.add(source);
//      changedValues.add(previous);
//      changedValues.add(next);
//      changedValues.add(null);
//      changedValues.add(null);
    }
    else if (next != null) {
      scoreDirector.beforeVariableChanged(next, "previousStandstill");
      scoreDirector.beforeVariableChanged(source, "nextCustomer");
      source.setNextCustomer(null);
      next.setPreviousStandstill(null);
      scoreDirector.afterVariableChanged(source, "nextCustomer");
      scoreDirector.afterVariableChanged(next, "previousStandstill");

//      changedEntities.add(source);
//      changedEntities.add(next);
//      changedValues.add(null);
//      changedValues.add(null);

    }
    else if (previous != null) {
      scoreDirector.beforeVariableChanged(source, "previousStandstill");
      scoreDirector.beforeVariableChanged(previous, "nextCustomer");

      previous.setNextCustomer(null);
      source.setPreviousStandstill(null);
//      changedEntities.add(previous);
//      changedEntities.add(source);
//      changedValues.add(null);
//      changedValues.add(null);

      scoreDirector.afterVariableChanged(previous, "nextCustomer");
      scoreDirector.afterVariableChanged(source, "previousStandstill");
    }
  }
}
