package com.example.demo;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/employee")

public class MainController {
    @Autowired // This means to get the bean called employeeRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String healthCheck() {
            return "HEALTH CHECK OK!";
    }

    @GetMapping("/version")
    public String version() {
            return "The actual version is 1.0.0";
    }
    
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody HashMap<String, Object> addNewEmployee (@RequestParam String fname, @RequestParam String lname, @RequestParam int annualSalary, @RequestParam Date hireDate, @RequestParam float superRate, @RequestParam float taxRate, @RequestParam String payPeriod) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Employee em = new Employee();
    em.setFname(fname);
    em.setLname(lname);
    em.setAnnualSalary(annualSalary);
    em.setHireDate(hireDate);
    em.setSuperRate(superRate);
    em.setTaxRate(taxRate);
    employeeRepository.save(em);

    int monthlyGrossIncome = TaxCalculator.getMonthlyGrossIncome(annualSalary);
    int monthlySuperAmount = TaxCalculator.getMonthlySuperAmount(monthlyGrossIncome, superRate);
    int monthlyIncomeTax = TaxCalculator.getMonthlyIncomeTax(monthlyGrossIncome, taxRate);
    int monthlyNetIncome = TaxCalculator.getMonthlyNetIncome(monthlyGrossIncome, monthlyIncomeTax);

    HashMap<String, Object> ret = new HashMap<String, Object>();
    ret.put("name", fname + " " + lname);
    ret.put("pay period", payPeriod);
    ret.put("gross income", monthlyGrossIncome);
    ret.put("income tax", monthlyIncomeTax);
    ret.put("net income", monthlyNetIncome);
    ret.put("super", monthlySuperAmount);

    return ret;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Employee> getAllUsers() {
    // This returns a JSON or XML with the users
    return employeeRepository.findAll();
    }

    @PostMapping(path="/tax")
    public @ResponseBody HashMap<Integer, Object> findTaxEmployeeByName (@RequestParam String fname, @RequestParam String lname, @RequestParam String payPeriod) {
        List<Employee> lstEmployee = employeeRepository.findByFnameAndLname(fname, lname);

        HashMap<Integer, Object> ret = new HashMap<Integer, Object>();

        int c = 0;
        for (Employee em : lstEmployee) {
            int monthlyGrossIncome = TaxCalculator.getMonthlyGrossIncome(em.getAnnualSalary());
            int monthlySuperAmount = TaxCalculator.getMonthlySuperAmount(monthlyGrossIncome, em.getSuperRate());
            int monthlyIncomeTax = TaxCalculator.getMonthlyIncomeTax(monthlyGrossIncome, em.getTaxRate());
            int monthlyNetIncome = TaxCalculator.getMonthlyNetIncome(monthlyGrossIncome, monthlyIncomeTax);

            HashMap<String, Object> r = new HashMap<String, Object>();
            r.put("name", em.getFname() + " " + em.getLname());
            r.put("pay period", payPeriod);
            r.put("gross income", monthlyGrossIncome);
            r.put("income tax", monthlyIncomeTax);
            r.put("net income", monthlyNetIncome);
            r.put("super", monthlySuperAmount);

            ret.put(c, r);
            c++;
        }
        return ret;
    }

    @PostMapping(path="/tax/{id}")
    public @ResponseBody HashMap<String, Object> findTaxEmployeeByID (@PathVariable Long id, @RequestParam String payPeriod) {
        Employee em = employeeRepository.findById(id);

        int monthlyGrossIncome = TaxCalculator.getMonthlyGrossIncome(em.getAnnualSalary());
        int monthlySuperAmount = TaxCalculator.getMonthlySuperAmount(monthlyGrossIncome, em.getSuperRate());
        int monthlyIncomeTax = TaxCalculator.getMonthlyIncomeTax(monthlyGrossIncome, em.getTaxRate());
        int monthlyNetIncome = TaxCalculator.getMonthlyNetIncome(monthlyGrossIncome, monthlyIncomeTax);

        HashMap<String, Object> ret = new HashMap<String, Object>();
        ret.put("name", em.getFname() + " " + em.getLname());
        ret.put("pay period", payPeriod);
        ret.put("gross income", monthlyGrossIncome);
        ret.put("income tax", monthlyIncomeTax);
        ret.put("net income", monthlyNetIncome);
        ret.put("super", monthlySuperAmount);

        return ret;
    }

}
