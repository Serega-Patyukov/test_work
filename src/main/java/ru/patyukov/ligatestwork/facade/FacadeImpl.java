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

import java.util.List;

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

    @Override
    public void deleteEmployee(Integer employeeId) {
        log.info("Фасад получил запрос на удаление работника с id = " + employeeId);
        employeeService.deleteEmployee(employeeId);
    }

    @Override
    public EmployeeResponse updateEmployee(Integer employeeId, EmployeeRequest employeeRequest) {
        EmployeeDto employeeDto = employeeMapper.employeeRequestToEmployeeDto(employeeRequest);
        log.info("Фасад получил запрос на обновление работника с id = " + employeeId + " : {}", employeeDto);
        EmployeeDto responseEmployeeDto = employeeService.updateEmployee(employeeId, employeeDto);
        EmployeeResponse employeeResponse = employeeMapper.employeeDtoToEmployeeResponse(responseEmployeeDto);
        return employeeResponse;
    }

    @Override
    public GadgetResponse updateGadget(Integer employeeId, Integer gadgetId, GadgetRequest gadgetRequest) {
        GadgetDto gadgetDto = gadgetMapper.gadgetRequestToGadgetDto(gadgetRequest);
        log.info("Фасад получил запрос на обновление гаджета с id = " + gadgetId + " У работника с id = " + employeeId + " : {}", gadgetDto);

        if (employeeService.existsById(employeeId)) {
            gadgetDto.setEmployeeId(employeeId);
            GadgetDto responseGadgetDto = gadgetService.updateGadget(gadgetId, gadgetDto);
            GadgetResponse gadgetResponse = gadgetMapper.gadgetDtoToGadgetResponse(responseGadgetDto);
            return gadgetResponse;
        }

        throw new NotFoundException("Работник с id = " + employeeId + " не найден");
    }

    @Override
    public EmployeeResponse getEmployeeById(Integer employeeId) {
        log.info("Фасад получил запрос на получение работника с id = " + employeeId);
        EmployeeDto responseEmployeeDto = employeeService.getEmployeeById(employeeId);
        EmployeeResponse employeeResponse = employeeMapper.employeeDtoToEmployeeResponse(responseEmployeeDto);

        List<GadgetDto> gadgetDtos = gadgetService.findAllByEmployeeId(employeeId);

        List<GadgetResponse> gadgetResponses = gadgetDtos.stream()
                .map(gadgetDto -> gadgetMapper.gadgetDtoToGadgetResponse(gadgetDto))
                .toList();

        employeeResponse.setGadgets(gadgetResponses);

        return employeeResponse;
    }

    @Override
    public List<EmployeeResponse> getEmployeeAll() {
        log.info("Фасад получил запрос на получение всех работников");
        List<EmployeeDto> employeeDtos = employeeService.getEmployeeAll();

        List<EmployeeResponse> employeeResponses = employeeDtos.stream()
                .map(employeeDto -> employeeMapper.employeeDtoToEmployeeResponse(employeeDto))
                .map(employeeResponse -> {
                    List<GadgetDto> gadgetDtos = gadgetService.findAllByEmployeeId(employeeResponse.getId());
                    List<GadgetResponse> gadgetResponses = gadgetDtos.stream()
                            .map(gadgetDto -> gadgetMapper.gadgetDtoToGadgetResponse(gadgetDto))
                            .toList();
                    employeeResponse.setGadgets(gadgetResponses);
                    return employeeResponse;
                })
                .toList();

        return employeeResponses;
    }
}
