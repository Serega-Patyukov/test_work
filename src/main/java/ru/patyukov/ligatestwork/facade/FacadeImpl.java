package ru.patyukov.ligatestwork.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.patyukov.ligatestwork.service.EmployeeService;
import ru.patyukov.ligatestwork.service.GadgetService;

@Slf4j
@Component
@AllArgsConstructor
public class FacadeImpl implements Facade {
    private final EmployeeService employeeService;
    private final GadgetService gadgetService;
}
