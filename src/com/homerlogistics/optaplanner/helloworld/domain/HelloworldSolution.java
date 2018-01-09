package com.homerlogistics.optaplanner.helloworld.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import com.homerlogistics.optaplanner.helloworld.domain.HelloEntity;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import com.homerlogistics.optaplanner.helloworld.domain.HelloEntity;

@PlanningSolution
public class HelloworldSolution {
  List<HelloEntity> helloList;
  List<Integer> helloCountRange;
  private HardSoftScore score;

	public HelloworldSolution() {
		initialize();
	}

	@ValueRangeProvider(id = "helloCountRange")
  public List<Integer> getHelloCountList() {
		return helloCountRange;
	}

  @PlanningEntityCollectionProperty
  public List<HelloEntity> getHelloList() {
    return helloList;
  }

  @PlanningScore
  public HardSoftScore getScore() {
    return score;
  }

  public void setScore(HardSoftScore score) {
    this.score = score;
  }


  public String toString() {
    String helloListStringified = "[";
    for (int i = 0; i < helloList.size(); i++) {
      HelloEntity helloEntity = helloList.get(i);
      helloListStringified += " " + helloEntity.getCount();
    }
    helloListStringified += " ]";
    return "Score: " + score + ". Solution: " + helloListStringified + ".";
  }


	private void initialize() {
		if (null == helloList) {
			helloList = new ArrayList<HelloEntity>();
			helloList.add(new HelloEntity(17));
		}

    if (null == helloCountRange) {
      helloCountRange = new ArrayList<Integer>();
      for (int i = 0; i < 20000; i++) {
        helloCountRange.add(new Integer(i));
      }
    }
	}

}
