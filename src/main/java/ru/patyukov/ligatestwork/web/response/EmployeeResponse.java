package ru.patyukov.ligatestwork.web.response;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeResponse {
    private Integer id;
    private String lastname;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<GadgetResponse> gadgets;
}
