package ru.patyukov.ligatestwork.web.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String lastname;
    private String name;
    private String secondname;
    private String phone;
    private String email;
}
