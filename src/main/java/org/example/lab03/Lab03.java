package org.example.lab03;

import java.util.Scanner;

public class Lab03 {
  public static String getValueFromUser(java.util.Scanner scanner, String message) {
    System.out.print(message);

    String value;
    value = scanner.next();

    return value;
  }

  public static void main(String[] args) {
    System.out.println("Program służy do dodawania ocen wyświetlania maksymalnej i minimalnej oceny oraz wyliczania średniej.");
    Scanner scanner = new Scanner(System.in);

    var gradesList = new GradeList();
    while (true) {
      System.out.println();
      System.out.println("""
        Wybierz jedną z opcji:
        a) Dodaj ocenę
        b) Wyświetl średnią ocen
        c) Znajdź najwyższą ocenę
        d) Znajdź najniższą ocenę
        e) Wyjdź
        """);

      char option = scanner.next().toCharArray()[0];
      switch (option) {
        case 'a' -> gradesList.addGrade(Double.parseDouble(getValueFromUser(scanner, "Podaj nową ocenę: ")));

        case 'b' -> {
          var mean = gradesList.calculateMean();

          if (mean.isNaN()) System.out.println("Lista ocen jest pusta!");
          else System.out.println("Średnia ocen: " + mean);
        }

        case 'c' -> {
          var max = gradesList.getMaxGrade();

          if (max == Double.MAX_VALUE) System.out.println("Lista ocen jest pusta!");
          else System.out.println("Najwyższa ocena to: " + max);
        }

        case 'd' -> {
          var min = gradesList.getMinGrade();

          if (min == Double.MIN_VALUE) System.out.println("Lista ocen jest pusta!");
          else System.out.println("Najniższa ocena to: " + min);
        }

        case 'e' -> System.exit(0);

        default -> System.out.println("Wybrałeś złą opcję!");
      }
    }
  }
}
