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
import ru.patyukov.ligatestwork.web.request.GadgetRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;
import ru.patyukov.ligatestwork.web.response.GadgetResponse;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = WebConstant.VERSION_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {
    private final Facade facade;

    @PostMapping("/create/employee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        log.info("Контроллер получил запрос на создание работника: {}", employeeRequest);
        EmployeeResponse employeeResponse = facade.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @PostMapping("/create/gadget/employeeid/{employeeId}")
    public ResponseEntity<GadgetResponse> createGadget(
            @PathVariable Integer employeeId,
            @RequestBody GadgetRequest gadgetRequest) {
        log.info("Контроллер получил запрос на создание гаджета для работника с id = " + employeeId + " : {}", gadgetRequest);
        GadgetResponse gadgetResponse = facade.createGadget(employeeId, gadgetRequest);
        return new ResponseEntity<>(gadgetResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/gadget/gadgetid/{gadgetId}")
    public ResponseEntity deleteGadget(@PathVariable Integer gadgetId) {
        log.info("Контроллер получил запрос на удаление гаджета с id = " + gadgetId);
        facade.deleteGadget(gadgetId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/employee/employeeid/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable Integer employeeId) {
        log.info("Контроллер получил запрос на удаление работника с id = " + employeeId);
        facade.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/employee/employeeid/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Integer employeeId,
            @RequestBody EmployeeRequest employeeRequest) {
        log.info("Контроллер получил запрос на обновление работника с id = " + employeeId + " : {}", employeeRequest);
        EmployeeResponse employeeResponse = facade.updateEmployee(employeeId, employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @PutMapping("/update/gadget/employeeid/{employeeId}/gadgetid/{gadgetId}")
    public ResponseEntity<GadgetResponse> updateGadget(
            @PathVariable Integer employeeId,
            @PathVariable Integer gadgetId,
            @RequestBody GadgetRequest gadgetRequest) {
        log.info("Контроллер получил запрос на обновление гаджета с id = " + gadgetId + " У работника с id = " + employeeId + " : {}", gadgetRequest);
        GadgetResponse gadgetResponse = facade.updateGadget(employeeId, gadgetId, gadgetRequest);
        return new ResponseEntity<>(gadgetResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/employee/employeeid/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Integer employeeId) {
        log.info("Контроллер получил запрос на получение работника с id = " + employeeId);
        EmployeeResponse employeeResponse = facade.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/get/employee/all")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeAll() {
        log.info("Контроллер получил запрос на получение всех работников");
        List<EmployeeResponse> employeeResponses = facade.getEmployeeAll();
        return new ResponseEntity<>(employeeResponses, HttpStatus.OK);
    }
}
