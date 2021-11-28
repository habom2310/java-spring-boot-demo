package com.example.demo;


public class TaxCalculator {

    public TaxCalculator(){

    }

    static int getMonthlyGrossIncome(int annualSalary) {
        return Math.round(annualSalary / 12);
    }

    static int getMonthlySuperAmount(float monthlyIncome, float superRate){
        return Math.round(monthlyIncome * superRate);
    }

    static int getMonthlyIncomeTax(float monthlyIncome, float taxRate) {
        return Math.round(monthlyIncome * taxRate);
    }

    static int getMonthlyNetIncome(int grossIncome, int incomeTax) {
        return grossIncome - incomeTax;
    }
    


}
