package com.homerlogistics.optaplanner.nqueens.domain;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;


@PlanningSolution
public class NQueensSolution {
  private int n;

  List<Queen> queenList;
  List<Row> rowList;
  private HardSoftScore score;

  public NQueensSolution() {
    initialize(8);
  }

  public NQueensSolution(int n) {
    initialize(n);
  }

  public int getN() {
    return n;
  }

  private void initialize(int n) {
    this.n = n;
    rowList = new ArrayList<Row>();
    queenList = new ArrayList<Queen>();

    for (int i = 0; i < n; i++) {
      rowList.add(new Row(i));
      queenList.add(new Queen(i));
    }
  }

  @ValueRangeProvider(id = "rowRange")
  public List<Row> getRows() {
    return rowList;
	}

  @PlanningEntityCollectionProperty
  public List<Queen> getQueenList() {
    return queenList;
  }

  public void setQueenList(List<Queen> queenList) {
    this.queenList = queenList;
  }

  @PlanningScore
  public HardSoftScore getScore() {
    return score;
  }

  public void setScore(HardSoftScore score) {
    this.score = score;
  }


  public List<int[]> toArray() {
    List<int[]> solution = new ArrayList<int[]>();
    for (int i = 0; i < n; i++) {
      Queen queen = queenList.get(i);
      Row row = queen.getRow();
      int[] coordinates = { queen.getColumn(), row != null ? row.getIndex() : -1 };
      solution.add(coordinates);
    }
    return solution;
  }

  public String toString() {
    List<int[]> arr = toArray();
    String sol = "Solution: [ ";
    for (int i = 0; i < arr.size(); i++) {
      int[] coord = arr.get(i);
      sol += "(" + coord[0] + "," + coord[1] + ") ";
    }
    sol += "].";
    return sol;
  }

}
