package com.devlach.patters.strategy.tax;

public interface TaxCalculator {
    double calculateTax(double amount);

    TaxType getTaxType();
}
