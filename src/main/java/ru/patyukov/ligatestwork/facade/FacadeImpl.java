package ru.patyukov.ligatestwork.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.patyukov.ligatestwork.dto.EmployeeDto;
import ru.patyukov.ligatestwork.dto.GadgetDto;
import ru.patyukov.ligatestwork.exception.NotFoundException;
import ru.patyukov.ligatestwork.mapper.EmployeeMapper;
import ru.patyukov.ligatestwork.mapper.GadgetMapper;
import ru.patyukov.ligatestwork.service.EmployeeService;
import ru.patyukov.ligatestwork.service.GadgetService;
import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.request.GadgetRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;
import ru.patyukov.ligatestwork.web.response.GadgetResponse;

@Slf4j
@Component
@AllArgsConstructor
public class FacadeImpl implements Facade {
    private final EmployeeService employeeService;
    private final GadgetService gadgetService;
    private final EmployeeMapper employeeMapper;
    private final GadgetMapper gadgetMapper;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        EmployeeDto employeeDto = employeeMapper.employeeRequestToEmployeeDto(employeeRequest);
        log.info("Фасад получил запрос на создание работника: {}", employeeDto);
        EmployeeDto responseEmployeeDto = employeeService.createEmployee(employeeDto);
        EmployeeResponse employeeResponse = employeeMapper.employeeDtoToEmployeeResponse(responseEmployeeDto);

        //todo тут нужно добавить список гаджетов

        return employeeResponse;
    }

    @Override
    public GadgetResponse createGadget(Integer employeeId, GadgetRequest gadgetRequest) {
        GadgetDto gadgetDto = gadgetMapper.gadgetRequestToGadgetDto(gadgetRequest);
        log.info("Фасад получил запрос на создание гаджета для работника с id = " + employeeId + " : {}", gadgetDto);

        if (employeeService.existsById(employeeId)) {
            gadgetDto.setEmployeeId(employeeId);
            GadgetDto responseGadgetDto = gadgetService.createGadget(gadgetDto);
            GadgetResponse gadgetResponse = gadgetMapper.gadgetDtoToGadgetResponse(responseGadgetDto);
            return gadgetResponse;
        }

        throw new NotFoundException("Работник с id = " + employeeId + " не найден");
    }

    @Override
    public void deleteGadget(Integer gadgetId) {
        log.info("Фасад получил запрос на удаление гаджета с id = " + gadgetId);
        gadgetService.deleteGadget(gadgetId);
    }
}
