package com.example.models;

import com.example.utils.Validator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Point {
  private double radius;
  private double x;
  private double y;
  private boolean inRange;
  private LocalDateTime currentTime;
  private double requestTime;

  public Point(double radius, double x, double y, LocalDateTime currentTime, double requestTime)
      throws IllegalArgumentException {
    this.checkArguments(radius, x, y);
    this.radius = radius;
    this.x = x;
    this.y = y;
    this.inRange = Validator.isInside(x, y, radius);
    this.currentTime = currentTime;
    this.requestTime = requestTime;
  }

  private void checkArguments(double radius, double x, double y) throws IllegalArgumentException {
    Validator.validateX(x);
    Validator.validateY(y);
    Validator.validateRadius(radius);
  }
}
