package com.homerlogistics.optaplanner.TSPDeliver.solver.persistence;

import com.homerlogistics.optaplanner.TSPDeliver.domain.*;
import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;

import java.io.File;

public class TSPSolutionIO implements SolutionFileIO<TSPSolution> {

  private TspImporter importer = new TspImporter();
  private TspExporter exporter = new TspExporter();

  @Override
  public String getInputFileExtension() {
    return "txt";
  }

  @Override
  public String getOutputFileExtension() {
    return "txt";
  }

  @Override
  public TSPSolution read(File file) {
    return importer.readSolution(file);
  }

  @Override
  public void write(TSPSolution solution, File file) {
    exporter.writeSolution(solution, file);
  }
}
