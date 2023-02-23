package ru.patyukov.ligatestwork.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.patyukov.ligatestwork.dto.EmployeeDto;
import ru.patyukov.ligatestwork.mapper.EmployeeMapper;
import ru.patyukov.ligatestwork.service.EmployeeService;
import ru.patyukov.ligatestwork.service.GadgetService;
import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;

@Slf4j
@Component
@AllArgsConstructor
public class FacadeImpl implements Facade {
    private final EmployeeService employeeService;
    private final GadgetService gadgetService;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        EmployeeDto employeeDto = employeeMapper.employeeRequestToEmployeeDto(employeeRequest);
        log.info("Фасад получил запрос на создание работника: {}", employeeDto);
        EmployeeDto responseEmployeeDto = employeeService.createEmployee(employeeDto);
        EmployeeResponse employeeResponse = employeeMapper.employeeDtoToEmployeeResponse(responseEmployeeDto);

        //todo тут нужно добавить список гаджетов

        return employeeResponse;
    }
}
