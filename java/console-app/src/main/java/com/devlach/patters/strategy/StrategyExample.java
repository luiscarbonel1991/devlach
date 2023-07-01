package com.devlach.patters.strategy;

import com.devlach.patters.strategy.tax.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Function;

public class StrategyExample {

    // Strategy Design Pattern with lambdas functions
    private static class AppContext{
        private static final EnumMap<TaxType, Function<Double, Double>> TAX_CALCULATOR_STRATEGIES = new EnumMap<>(TaxType.class);

        private static final double FIX_RATE_PERCENTAGE_INCOME = 0.2;
        private static final double FIX_RATE_PERCENTAGE_VAT = 0.15;
        private static final double FIX_RATE_PERCENTAGE_SALES = 0.1;

        public AppContext() {
            initTaxCalculatorStrategies();
        }

        public Function<Double, Double> getCalculatorTax(TaxType taxType) {
            return TAX_CALCULATOR_STRATEGIES.get(taxType);
        }

        private static void initTaxCalculatorStrategies() {
            TAX_CALCULATOR_STRATEGIES.put(TaxType.INCOME_TAX, amount -> FIX_RATE_PERCENTAGE_INCOME * amount);
            TAX_CALCULATOR_STRATEGIES.put(TaxType.VAT_TAX,  amount -> FIX_RATE_PERCENTAGE_VAT * amount);
            TAX_CALCULATOR_STRATEGIES.put(TaxType.SALES_TAX, amount -> FIX_RATE_PERCENTAGE_SALES * amount);
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
            return  Arrays.stream(amounts)
                    .map(amount -> appContext.getCalculatorTax( taxType).apply(amount))
                    .sum();
        }

    }


    public static void main(String[] args) {
        double[] amounts = {100, 200, 300}; // Amounts to calculate tax for

        // Tax Calculation Logic.
        // For each amount, calculate tax and add to total tax based on taxType
        AppContext appContext = new AppContext();
        AccountingApplication accountingApplication = new AccountingApplication(appContext);
        System.out.println("Total Income Tax: " + accountingApplication.calculateTax(amounts, TaxType.INCOME_TAX));
        System.out.println("Total VAT Tax: " + accountingApplication.calculateTax(amounts, TaxType.VAT_TAX));
        System.out.println("Total Sales Tax: " + accountingApplication.calculateTax(amounts, TaxType.SALES_TAX));
    }


  /*

    // Improving flexibility with a context and a Map in the Strategy design pattern
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

   */
/*
    // Setter method to change the tax calculation strategy at runtime
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
