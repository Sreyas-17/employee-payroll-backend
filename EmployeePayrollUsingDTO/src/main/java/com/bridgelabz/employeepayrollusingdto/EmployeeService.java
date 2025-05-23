package com.bridgelabz.employeepayrollusingdto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("Creating employee: {}", employeeDTO);
        EmployeeDetails employee = convertToEntity(employeeDTO);
        EmployeeDetails saved = employeeRepository.save(employee);
        log.debug("Saved employee: {}", saved);
        return convertToDTO(saved);
    }

    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        log.info("Fetching employee with id: {}", id);
        return employeeRepository.findById(id)
                .map(employee -> {
                    log.debug("Found employee: {}", employee);
                    return convertToDTO(employee);
                })
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return new EmployeePayrollNotFoundException("Employee not found with id: " + id);
                });
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with id: {}", id);
        if (!employeeRepository.existsById(id)) {
            log.error("Employee not found with id: {}", id);
            throw new EmployeePayrollNotFoundException("Employee not found with id: " + id);
        }
        EmployeeDetails employee = convertToEntity(employeeDTO);
        employee.setId(id);
        EmployeeDetails updated = employeeRepository.save(employee);
        log.debug("Updated employee: {}", updated);
        return convertToDTO(updated);
    }

    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        if (!employeeRepository.existsById(id)) {
            log.error("Employee not found with id: {}", id);
            throw new EmployeePayrollNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
        log.debug("Deleted employee with id: {}", id);
    }

    private EmployeeDTO convertToDTO(EmployeeDetails employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());
        dto.setGender(employee.getGender());
        dto.setDepartment(employee.getDepartment());
        dto.setStartDate(employee.getStartDate());
        dto.setNotes(employee.getNotes());
        dto.setProfileImage(employee.getProfileImage()); // Add this line
        return dto;
    }

    private EmployeeDetails convertToEntity(EmployeeDTO dto) {
        EmployeeDetails employee = new EmployeeDetails();
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        employee.setGender(dto.getGender());
        employee.setDepartment(dto.getDepartment());
        if (dto.getStartDate() != null) {
            employee.setStartDate(new java.sql.Date(dto.getStartDate().getTime()));
        }
        employee.setNotes(dto.getNotes());
        employee.setProfileImage(dto.getProfileImage());
        return employee;
    }


    public List<EmployeeDetails> findUsersByUsername(String name) {
        return employeeRepository.findByUsername(name);
    }
}
