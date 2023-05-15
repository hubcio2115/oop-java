package org.example.lab01;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Scanner;

public class Lab01 {
  public static BigInteger factorial(int n) {
    BigInteger res = BigInteger.ONE;

    for (int i = 2; i <= n; i++) {
      res = res.multiply(BigInteger.valueOf(i));
    }

    return res;
  }

  public static BigInteger pow(int x) {
    BigInteger res = BigInteger.TWO;

    for (int i = 0; i < x; i++) {
      res = res.multiply(res);
    }

    return res;
  }

  public static void program(Scanner in) {
    System.out.println("Podaj liczbę całkowitą (lub 'exit' żeby wyjść): ");
    String input = in.next();

    if (Objects.equals(input, "exit")) {
      System.exit(0);
      return;
    }

    try {
      int number = Integer.parseInt(input);

      if (number <= 0) {
        System.out.println("Number musi być większy od 0!");
        program(in);
      }

      System.out.println(factorial(number));
    } catch(NumberFormatException err) {
      System.out.println(err.getMessage());
      System.exit(1);
    }

    program(in);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    program(in);
  }
}