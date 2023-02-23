package ru.patyukov.ligatestwork.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.patyukov.ligatestwork.dto.GadgetDto;
import ru.patyukov.ligatestwork.entity.Employee;
import ru.patyukov.ligatestwork.entity.Gadget;
import ru.patyukov.ligatestwork.mapper.GadgetMapper;
import ru.patyukov.ligatestwork.repository.GadgetRepository;

@Slf4j
@Service
@AllArgsConstructor
public class GadgetServiceImpl implements GadgetService {
    private final GadgetRepository gadgetRepository;
    private final GadgetMapper gadgetMapper;

    @Override
    public GadgetDto createGadget(GadgetDto gadgetDto) {
        Gadget gadget = gadgetMapper.gadgetDtoToGadget(gadgetDto);
        log.info("Сервис получил запрос на создание гаджета: {}", gadgetDto);

        Employee employee = new Employee();
        employee.setId(gadgetDto.getEmployeeId());

        gadget.setEmployee(employee);
        Gadget responseGadget = gadgetRepository.save(gadget);
        log.info("Репозиторий сохранил гаджет: {}", responseGadget);
        GadgetDto responseGadgetDto = gadgetMapper.gadgetToGadgetDto(responseGadget);
        responseGadgetDto.setEmployeeId(gadgetDto.getEmployeeId());
        return responseGadgetDto;
    }
}
