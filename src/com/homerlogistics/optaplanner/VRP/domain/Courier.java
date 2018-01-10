package com.homerlogistics.optaplanner.VRP.domain;

import com.homerlogistics.optaplanner.nqueens.domain.Row;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Courier {

  // project facts:
  private int column;

  // planning variables:
  private com.homerlogistics.optaplanner.nqueens.domain.Row row;

  public Courier() {
    /* do nothing */
  }

  public Courier(int column) {
    this.column = column;
  }

  public int getColumn() {
    return column;
  }

  @PlanningVariable(valueRangeProviderRefs = { "rowRange" })
  public com.homerlogistics.optaplanner.nqueens.domain.Row getRow() {
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
