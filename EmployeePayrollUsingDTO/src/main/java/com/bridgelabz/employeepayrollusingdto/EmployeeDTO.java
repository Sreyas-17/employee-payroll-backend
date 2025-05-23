package com.bridgelabz.employeepayrollusingdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
@Data
public class EmployeeDTO {
    private Long id;

    @NotNull(message = "Name is required")
    @Pattern(regexp = "[A-Za-z ]{2,50}", message = "Name must be 2-50 characters and contain only letters and spaces")
    private String name;

    @Min(value = 10000, message = "Salary should be at least 10000")
    private double salary;

    private String gender;
    private String department;
    private Date startDate;
    private String notes;
    private String profileImage;
}