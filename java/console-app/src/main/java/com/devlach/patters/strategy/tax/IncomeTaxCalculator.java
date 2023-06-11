package com.devlach.patters.strategy.tax;

public class IncomeTaxCalculator implements TaxCalculator {

    public static final double FIX_RATE_PERCENTAGE = 0.2;

    @Override
    public double calculateTax(double amount) {
        // Income Tax Calculation Logic.
        // Fixed rate of 20% for income tax
        return FIX_RATE_PERCENTAGE * amount;
    }

    @Override
    public TaxType getTaxType() {
        return TaxType.INCOME_TAX;
    }
}
