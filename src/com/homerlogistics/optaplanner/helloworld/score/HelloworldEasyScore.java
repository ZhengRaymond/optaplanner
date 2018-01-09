package com.homerlogistics.optaplanner.helloworld.score;

import java.util.List;

import com.homerlogistics.optaplanner.helloworld.domain.HelloEntity;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import com.homerlogistics.optaplanner.helloworld.domain.HelloworldSolution;

public class HelloworldEasyScore implements EasyScoreCalculator<HelloworldSolution> {

  public HardSoftScore calculateScore(HelloworldSolution helloworldSolution) {
    int optimalCount = 16792;

    List<HelloEntity> helloList = helloworldSolution.getHelloList();
    int length = helloList.size();
    int hardScore = 0;
    int softScore = 0;
    for (int i = 0; i < length; i++) {
      HelloEntity helloEntity = helloList.get(i);
      int count = helloEntity.getCount();
      if (count % 5 != 0) {
        hardScore -= 1;
      }
      int difference = count - optimalCount;
      if (difference < 0) {
        difference *= -1;
      }
      softScore -= difference;
    }

    System.out.println(hardScore + "hard/" + softScore + "soft, solution: " + helloworldSolution.toString());

    return HardSoftScore.valueOf(hardScore, softScore);
  }
}
