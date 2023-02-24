package ru.patyukov.ligatestwork.service;

import ru.patyukov.ligatestwork.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    boolean existsById(Integer employeeId);

    void deleteEmployee(Integer employeeId);

    EmployeeDto updateEmployee(Integer employeeId, EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Integer employeeId);
}
