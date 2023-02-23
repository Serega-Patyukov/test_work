package ru.patyukov.ligatestwork.facade;

import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;

public interface Facade {
    EmployeeResponse createEmployee(EmployeeRequest employeeRequest);
}
