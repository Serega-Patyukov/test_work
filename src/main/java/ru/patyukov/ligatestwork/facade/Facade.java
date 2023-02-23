package ru.patyukov.ligatestwork.facade;

import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.request.GadgetRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;
import ru.patyukov.ligatestwork.web.response.GadgetResponse;

public interface Facade {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    GadgetResponse createGadget(Integer employeeId, GadgetRequest gadgetRequest);
}
