package com.homerlogistics.optaplanner.nqueens.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Queen {

  // project facts:
  private int column;

  // planning variables:
  private Row row;

  public Queen() {
    /* do nothing */
  }

  public Queen(int column) {
    this.column = column;
  }

  public int getColumn() {
    return column;
  }

  @PlanningVariable(valueRangeProviderRefs = { "rowRange" })
  public Row getRow() {
    return row;
  }

  public void setRow(Row row) {
    this.row = row;
  }

  public int getLeftDiagnolIndex(){
    return column + row.getIndex();
  }

  public int getRightDiagnolIndex(){
    return column - row.getIndex();
  }
}
