package com.bridgelabz.employeepayrollusingdto;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "employees")
@Data
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double salary;
    private String gender;
    private String department;
    private Date startDate;
    private String notes;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String profileImage;
}