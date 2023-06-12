package com.devlach.patters.strategy;

import com.devlach.patters.strategy.tax.*;

import java.util.Arrays;
import java.util.EnumMap;

public class StrategyExample {


    private static class AppContext {
        private final EnumMap<TaxType, TaxCalculator> taxCalculatorStrategies = new EnumMap<>(TaxType.class);

        public AppContext() {
            initTaxCalculatorStrategies();
        }

        private void initTaxCalculatorStrategies() {
            TaxCalculator incomeTaxCalculator = new IncomeTaxCalculator();
            taxCalculatorStrategies.put(incomeTaxCalculator.getTaxType(), incomeTaxCalculator);

            TaxCalculator vatCalculator = new VATCalculator();
            taxCalculatorStrategies.put(vatCalculator.getTaxType(), vatCalculator);

            TaxCalculator salesTaxCalculator = new SalesTaxCalculator();
            taxCalculatorStrategies.put(salesTaxCalculator.getTaxType(), salesTaxCalculator);
        }

        public TaxCalculator getTaxCalculator(TaxType taxType) {
            return taxCalculatorStrategies.get(taxType);
        }
    }


    private static class AccountingApplication {
        private final AppContext appContext;

        public AccountingApplication(AppContext appContext) {
            this.appContext = appContext;
        }

        public double calculateTax(double[] amounts, TaxType taxType) {
            // Tax Calculation Logic.
            // For each amount, calculate tax and add to total tax based on taxType
            return Arrays.stream(amounts)
                    .map(amount -> appContext.getTaxCalculator(taxType).calculateTax(amount))
                    .sum();
        }
    }

    public static void main(String[] args) {
        double[] amounts = {100, 200, 300}; // Amounts to calculate tax for
        AppContext appContext = new AppContext();
        AccountingApplication accountingApplication = new AccountingApplication(appContext);
        System.out.println("Total Income Tax: " + accountingApplication.calculateTax(amounts, TaxType.INCOME_TAX));
        System.out.println("Total VAT Tax: " + accountingApplication.calculateTax(amounts, TaxType.VAT_TAX));
        System.out.println("Total Sales Tax: " + accountingApplication.calculateTax(amounts, TaxType.SALES_TAX));
    }

/*

    private static class AccountingApplication {

        private TaxCalculator taxCalculator;

        public AccountingApplication(TaxCalculator taxCalculator) {
            this.taxCalculator = taxCalculator;
        }

        public double calculateTax(double[] amounts) {
            // Tax Calculation Logic.
            // For each amount, calculate tax and add to total tax
            return Arrays.stream(amounts)
                    .map(taxCalculator::calculateTax)
                    .sum();
        }

        public void setTaxCalculator(TaxCalculator taxCalculator) {
            this.taxCalculator = taxCalculator;
        }
    }

    public static void main(String[] args) {
        double[] amounts = {100, 200, 300}; // Amounts to calculate tax for
        AccountingApplication accountingApplication = new AccountingApplication(new IncomeTaxCalculator());
        System.out.println("Total Income Tax: " + accountingApplication.calculateTax(amounts));

        accountingApplication.setTaxCalculator(new VATCalculator());
        System.out.println("Total VAT Tax: " + accountingApplication.calculateTax(amounts));

        accountingApplication.setTaxCalculator(new SalesTaxCalculator());
        System.out.println("Total Sales Tax: " + accountingApplication.calculateTax(amounts));
    }

*/
}
