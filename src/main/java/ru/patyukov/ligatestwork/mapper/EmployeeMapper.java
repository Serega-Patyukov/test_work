package ru.patyukov.ligatestwork.mapper;

import org.mapstruct.Mapper;
import ru.patyukov.ligatestwork.dto.EmployeeDto;
import ru.patyukov.ligatestwork.entity.Employee;
import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto employeeRequestToEmployeeDto(EmployeeRequest employeeRequest);
    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
    EmployeeDto employeeToEmployeeDto(Employee employee);
    EmployeeResponse employeeDtoToEmployeeResponse(EmployeeDto employeeDto);
}
