package org.example.lab03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class GradeList {
  ArrayList<Double> grades;

  public GradeList() {
    grades = new ArrayList<>();
  }

  public void addGrade(Double grade) {
    grades.add(grade);
  }

  public Double calculateMean() {
    var sum = grades.stream().reduce(0.0, Double::sum);

    return sum / grades.size();
  }

  public Double getMaxGrade() {
    try {
      return Collections.max(grades);
    } catch (NoSuchElementException e) {
      return Double.MAX_VALUE;
    }
  }

  public Double getMinGrade() {
    try {
      return Collections.min(grades);
    } catch (NoSuchElementException e) {
      return Double.MIN_VALUE;
    }
  }
}
