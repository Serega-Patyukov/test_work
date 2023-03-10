package ru.patyukov.ligatestwork.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")
    })
    @PostMapping("/create/employee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        log.info("=================================================================================================");
        log.info("Controller. Got employee create request. {}", employeeRequest);
        EmployeeResponse employeeResponse = facade.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Create gadget")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201")
    })
    @PostMapping("/create/gadget/employeeid/{employeeId}")
    public ResponseEntity<GadgetResponse> createGadget(
            @PathVariable Integer employeeId,
            @RequestBody GadgetRequest gadgetRequest) {
        log.info("=================================================================================================");
        log.info("Controller. Got gadget create request. employeeId = " + employeeId + ". {}", gadgetRequest);
        GadgetResponse gadgetResponse = facade.createGadget(employeeId, gadgetRequest);
        return new ResponseEntity<>(gadgetResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete gadget")
    @DeleteMapping("/delete/gadget/gadgetid/{gadgetId}")
    public void deleteGadget(@PathVariable Integer gadgetId) {
        log.info("=================================================================================================");
        log.info("Controller. Got gadget delete request. gadgetId = " + gadgetId);
        facade.deleteGadget(gadgetId);
    }

    @Operation(summary = "Delete employee")
    @DeleteMapping("/delete/employee/employeeid/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        log.info("=================================================================================================");
        log.info("Controller. Got employee delete request. employeeId = " + employeeId);
        facade.deleteEmployee(employeeId);
    }

    @Operation(summary = "Update employee")
    @PutMapping("/update/employee/employeeid/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Integer employeeId,
            @RequestBody EmployeeRequest employeeRequest) {
        log.info("=================================================================================================");
        log.info("Controller. Got employee update request. employeeId = " + employeeId + ". {}", employeeRequest);
        EmployeeResponse employeeResponse = facade.updateEmployee(employeeId, employeeRequest);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update gadget")
    @PutMapping("/update/gadget/employeeid/{employeeId}/gadgetid/{gadgetId}")
    public ResponseEntity<GadgetResponse> updateGadget(
            @PathVariable Integer employeeId,
            @PathVariable Integer gadgetId,
            @RequestBody GadgetRequest gadgetRequest) {
        log.info("=================================================================================================");
        log.info("Controller. Got gadget update request. employeeId = " + employeeId + ". gadgetId = " + gadgetId + ". {}", gadgetRequest);
        GadgetResponse gadgetResponse = facade.updateGadget(employeeId, gadgetId, gadgetRequest);
        return new ResponseEntity<>(gadgetResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get employee")
    @GetMapping("/get/employee/employeeid/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Integer employeeId) {
        log.info("=================================================================================================");
        log.info("Controller. Got employee get request. employeeId = " + employeeId);
        EmployeeResponse employeeResponse = facade.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get employee all")
    @GetMapping("/get/employee/all")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeAll() {
        log.info("=================================================================================================");
        log.info("Controller. Got all employees get request");
        List<EmployeeResponse> employeeResponses = facade.getEmployeeAll();
        return new ResponseEntity<>(employeeResponses, HttpStatus.OK);
    }

    @Operation(summary = "Get gadget")
    @GetMapping("/get/gadget/gadgetid/{gadgetId}")
    public ResponseEntity<GadgetResponse> getGadgetById(@PathVariable Integer gadgetId) {
        log.info("=================================================================================================");
        log.info("Controller. Got gadget get request. gadgetId = " + gadgetId);
        GadgetResponse gadgetResponse = facade.getGadgetById(gadgetId);
        return new ResponseEntity<>(gadgetResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get gadget all")
    @GetMapping("/get/gadget/all")
    public ResponseEntity<List<GadgetResponse>> getGadgetAll() {
        log.info("=================================================================================================");
        log.info("Controller. Got all gadgets get request");
        List<GadgetResponse> gadgetResponses = facade.getGadgetAll();
        return new ResponseEntity<>(gadgetResponses, HttpStatus.OK);
    }
}
