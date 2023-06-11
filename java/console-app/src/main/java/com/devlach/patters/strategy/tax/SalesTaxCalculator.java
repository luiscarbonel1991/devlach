package com.devlach.patters.strategy.tax;

public class SalesTaxCalculator implements TaxCalculator {

        public static final double FIX_RATE_PERCENTAGE = 0.1;

        @Override
        public double calculateTax(double amount) {
            // Sales Tax Calculation Logic.
            // Fixed rate of 10% for sales tax
            return FIX_RATE_PERCENTAGE * amount;
        }

    @Override
    public TaxType getTaxType() {
        return TaxType.SALES_TAX;
    }
}
