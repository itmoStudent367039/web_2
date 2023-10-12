package com.example.models;

import com.example.utils.Validator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Setter
public class Point {
    private double radius;
    private double x;
    private double y;
    private boolean inRange;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime currentTime;
    private double requestTime;

    public Point(double radius, double x, double y, LocalDateTime currentTime, double requestTime) throws IllegalArgumentException {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(radius, point.radius) == 0 && Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0 && inRange == point.inRange && Double.compare(requestTime, point.requestTime) == 0 && Objects.equals(currentTime, point.currentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, x, y, inRange, currentTime, requestTime);
    }
}
