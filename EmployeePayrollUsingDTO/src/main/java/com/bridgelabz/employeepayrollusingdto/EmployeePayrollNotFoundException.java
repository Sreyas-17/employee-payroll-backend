package com.bridgelabz.employeepayrollusingdto;

public class EmployeePayrollNotFoundException extends RuntimeException {
    public EmployeePayrollNotFoundException(String message) {
        super(message);
    }
}