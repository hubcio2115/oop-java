package org.example.lab02;

public class Roller {
  private int height;
  private int radius;

  public Roller(int height, int radius) {
    this.height = height;
    this.radius = radius;
  }

  public Roller() {
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getHeight() {
    return this.height;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }

  public int getRadius() {
    return this.radius;
  }

  public double calculateFieldOfBase() {
    return Math.PI * Math.pow(radius, 2);
  }

  public double calculateFieldOfSideArea() {
    return 2 * Math.PI * radius * height;
  }

  public double calculateField() {
    return 2 * calculateFieldOfSideArea() + calculateFieldOfBase();
  }

  public double calculateVolume() {
    return calculateFieldOfBase() * height;
  }
}
