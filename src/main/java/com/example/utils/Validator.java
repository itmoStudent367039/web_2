package com.example.utils;

public class Validator {
    private static final String BETWEEN_MINUS_THREE_AND_THREE = "^-?(?:3(?:\\.0+)?|[0-2](?:\\.[0-9]+)?|\\.[0-9]+)$";

    public static boolean isInside(double x, double y, double r) {
        return
                Double.compare(x, 0.0) <= 0 && Double.compare(y, 0.0) >= 0 && Double.compare(y, 0.5 * (x + r)) <= 0 ||
                        Double.compare(y, 0.0) >= 0 && Double.compare(x, 0.0) >= 0 && Double.compare(x*x + y*y, r*r / 4) <= 0 ||
                        Double.compare(x, 0.0) <= 0 && Double.compare(y, 0.0) <= 0 && Double.compare(x, - r / 2) >= 0 &&
                                Double.compare(y, -r) >= 0;
    }

    public static void validateX(double x) throws InvalidArgumentException {
        if (!String.valueOf(x).matches(BETWEEN_MINUS_THREE_AND_THREE)) {
            throw new InvalidArgumentException(String.format("X isn't valid: %f", x));
        }
    }

    public static void validateY(double y) throws InvalidArgumentException {
        if (!String.valueOf(y).matches(BETWEEN_MINUS_THREE_AND_THREE)) {
            throw new InvalidArgumentException(String.format("Y isn't valid: %f", y));
        }
    }

    public static void validateRadius(double radius) throws InvalidArgumentException {

        if (Double.compare(radius, 1.0) != 0 &&
                Double.compare(radius, 1.5) != 0 &&
                Double.compare(radius, 2.0) != 0 &&
                Double.compare(radius, 2.5) != 0 &&
                Double.compare(radius, 3.0) != 0) {
            throw new InvalidArgumentException(String.format("Radius isn't valid: %f", radius));
        }
    }

    public static class InvalidArgumentException extends IllegalArgumentException {
        public InvalidArgumentException(String s) {
            super(s);
        }
    }
}
