package org.example.lab02;

import java.util.Scanner;

public class Lab02 {
  public static String getValueFromUser(java.util.Scanner scanner, String message) {
    System.out.print(message);

    String value;
    value = scanner.next();

    return value;
  }

  public static void main(String[] args) {
    System.out.println("Program służy do obliczania pól i objętości walca");
    Scanner scanner = new Scanner(System.in);

    int height = 0;
    int radius = 0;
    try {
      height = Integer.parseInt(getValueFromUser(scanner, "Podaj wysokość walca: "));
      radius = Integer.parseInt(getValueFromUser(scanner, "Podaj promień walca: "));
    } catch (NumberFormatException error) {
      System.out.println(error.getMessage());
      System.exit(1);
    }

    Roller roller = new Roller(height, radius);

    while (true) {
      System.out.println();
      System.out.println("""
        Wybierz jedną z opcji:
        a) Wyświetl wartości opisujących walec
        b) Zmień wysokość
        c) Zmień promień
        d) Wyświetl pole powierzchni walca i jego objętość
        e) Wyjdź
        """);

      char option = scanner.next().toCharArray()[0];
      switch (option) {
        case 'a' -> System.out.println("Promień: " + roller.getRadius() + " Wysokość: " + roller.getHeight());

        case 'b' -> roller.setHeight(Integer.parseInt(getValueFromUser(scanner, "Podaj nową wartość: ")));

        case 'c' -> roller.setRadius(Integer.parseInt(getValueFromUser(scanner, "Podaj nową wartość: ")));

        case 'd' ->
          System.out.println("Objętość: " + roller.calculateVolume() + " Pole całkowite: " + roller.calculateField());

        case 'e' -> System.exit(0);

        default -> System.out.println("Wybrałeś złą opcję!");
      }
    }
  }
}
