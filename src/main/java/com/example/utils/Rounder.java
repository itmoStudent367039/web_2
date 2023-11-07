package com.example.utils;

public class Rounder {
  public static String round(double var) {
    String formatVar = String.format("%.2f", var);

    if (formatVar.endsWith(",00")) {
      return formatVar.substring(0, formatVar.indexOf(',')).replace(',', '.');
    }
    return formatVar.replace(',', '.');
  }
}
