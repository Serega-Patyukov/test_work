package ru.patyukov.ligatestwork.web.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeResponse {
    private Integer id;
    private String lastname;
    private String name;
    private String secondname;
    private String phone;
    private String email;
    private List<GadgetResponse> gadgets = new ArrayList<>();
}
