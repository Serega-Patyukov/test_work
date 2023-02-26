package ru.patyukov.ligatestwork.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.patyukov.ligatestwork.dto.EmployeeDto;
import ru.patyukov.ligatestwork.entity.Employee;
import ru.patyukov.ligatestwork.exception.NotFoundException;
import ru.patyukov.ligatestwork.mapper.EmployeeMapper;
import ru.patyukov.ligatestwork.repository.EmployeeRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        log.info("Service. Got employee create request. {}", employee);
        Employee responseEmployee = employeeRepository.save(employee);
        log.info("Service. Repository save employee. {}", responseEmployee);
        EmployeeDto responseEmployeeDto = employeeMapper.employeeToEmployeeDto(responseEmployee);
        return responseEmployeeDto;
    }

    @Override
    public boolean existsById(Integer employeeId) {
        boolean isEmployee = employeeRepository.existsById(employeeId);
        return isEmployee;
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        log.info("Service. Got employee delete request. employeeId = " + employeeId);
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new NotFoundException("Service. Employee not found. employeeId = " + employeeId);
        }
    }

    @Override
    public EmployeeDto updateEmployee(Integer employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
        log.info("Service. Got employee update request. employeeId = " + employeeId + ". {}", employee);
        if (employeeRepository.existsById(employeeId)) {
            employee.setId(employeeId);
            Employee responseEmployee = employeeRepository.save(employee);
            log.info("Service. Repository update employee: {}", responseEmployee);
            EmployeeDto responseEmployeeDto = employeeMapper.employeeToEmployeeDto(responseEmployee);
            return responseEmployeeDto;
        } else {
            throw new NotFoundException("Service. Employee not found. employeeId = " + employeeId);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Integer employeeId) {
        log.info("Service. Got employee get request. employeeId = " + employeeId);
        Employee responseEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Service. Employee not found. employeeId = " + employeeId));
        EmployeeDto responseEmployeeDto = employeeMapper.employeeToEmployeeDto(responseEmployee);
        return responseEmployeeDto;
    }

    @Override
    public List<EmployeeDto> getEmployeeAll() {
        log.info("Service. Got all employees get request");
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDto> employeeDtos = employees.stream()
                .map(employee -> employeeMapper.employeeToEmployeeDto(employee))
                .toList();

        return employeeDtos;
    }
}
