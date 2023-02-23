package ru.patyukov.ligatestwork.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patyukov.ligatestwork.facade.Facade;
import ru.patyukov.ligatestwork.web.constant.WebConstant;
import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = WebConstant.VERSION_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {
    private final Facade facade;

    @PostMapping("/createemployee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        log.info("Контроллер получил запрос на создание работника: {}", employeeRequest);
        EmployeeResponse employeeResponse = facade.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }
}
