package com.homerlogistics.optaplanner.nqueens.score;

import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import com.homerlogistics.optaplanner.nqueens.domain.*;

public class NQueensScore implements EasyScoreCalculator<NQueensSolution> {

  public HardSoftScore calculateScore(NQueensSolution nQueensSolution) {
    List<Queen> queenList = nQueensSolution.getQueenList();
    int n = nQueensSolution.getN();
    int hardScore = 0;
    int softScore = 0;

    for (int i = 0; i < n - 1; i++) {
      Queen queen = queenList.get(i);
      for (int j = i + 1; j < n; j++) {
        Queen other = queenList.get(j);
        Row r1 = queen.getRow();
        Row r2 = other.getRow();
        if (r1 == null || r2 == null) continue;
        if (r1.getIndex() == r2.getIndex()) {
          softScore -= 1;
        }
        if (other.getLeftDiagnolIndex() == queen.getLeftDiagnolIndex()) {
          softScore -= 1;
        }
        if (other.getRightDiagnolIndex() == queen.getRightDiagnolIndex()) {
          softScore -= 1;
        }
      }
    }

//    System.out.println(hardScore + "hard/" + softScore + "soft, solution: " + nQueensSolution.toString());

    return HardSoftScore.valueOf(hardScore, softScore);
  }
}
