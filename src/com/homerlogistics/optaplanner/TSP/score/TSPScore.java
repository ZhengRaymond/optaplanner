package com.homerlogistics.optaplanner.TSP.score;

import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import com.homerlogistics.optaplanner.TSP.domain.*;

public class TSPScore implements EasyScoreCalculator<TSPSolution> {

  public HardSoftScore calculateScore(TSPSolution tSPSolution) {
    List<Trail> trailList = tSPSolution.getTrailList();
    int hardScore = 0;
    int softScore = 0;

    for (int i = 0; i < trailList.size(); i++) {
      Trail trail = trailList.get(i);
      softScore -= trail.getDistance();
    }

    return HardSoftScore.valueOf(hardScore, softScore);
  }
}
