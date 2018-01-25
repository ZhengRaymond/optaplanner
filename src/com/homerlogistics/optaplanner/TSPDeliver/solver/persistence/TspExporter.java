package com.homerlogistics.optaplanner.TSPDeliver.solver.persistence;

import com.homerlogistics.optaplanner.TSPDeliver.domain.TSPSolution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TspExporter {
  public TspExporter() {

  }

  public void writeSolution(TSPSolution solution, File file) {
    System.out.println(solution.toString());
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      writer.write(solution.toString());
      writer.close();
    }
    catch (IOException ie) {
      ie.printStackTrace();
    }
  }
}
