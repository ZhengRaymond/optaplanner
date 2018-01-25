package com.homerlogistics.optaplanner.TSPDeliver;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.optaplanner.benchmark.api.*;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//ch.qos.logback:logback-classic:1.2.3

public class TSPAppBenchmark {
  public static final String SOLVER_CONFIG_XML = "com/homerlogistics/optaplanner/TSPDeliver/solver/persistence/TSPBenchmarker.xml";
  public static ScoreDirector scoreDirector;

  public static void main(String[] args) {
    PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromXmlResource(SOLVER_CONFIG_XML);
    PlannerBenchmark plannerBenchmark = benchmarkFactory.buildPlannerBenchmark();
    plannerBenchmark.benchmark();

  }

  public static void writeFile(String filename, String solutions) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      writer.write(solutions);
      writer.close();
    } catch (IOException ie) {
      ie.printStackTrace();
    }
  }

}
