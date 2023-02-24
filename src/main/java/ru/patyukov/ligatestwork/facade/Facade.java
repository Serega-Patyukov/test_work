package ru.patyukov.ligatestwork.facade;

import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.request.GadgetRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;
import ru.patyukov.ligatestwork.web.response.GadgetResponse;

import java.util.List;

public interface Facade {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    GadgetResponse createGadget(Integer employeeId, GadgetRequest gadgetRequest);

    void deleteGadget(Integer gadgetId);

    void deleteEmployee(Integer employeeId);

    EmployeeResponse updateEmployee(Integer employeeId, EmployeeRequest employeeRequest);

    GadgetResponse updateGadget(Integer employeeId, Integer gadgetId, GadgetRequest gadgetRequest);

    EmployeeResponse getEmployeeById(Integer employeeId);

    List<EmployeeResponse> getEmployeeAll();

    GadgetResponse getGadgetById(Integer gadgetId);

    List<GadgetResponse> getGadgetAll();
}
