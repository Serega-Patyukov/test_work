package ru.patyukov.ligatestwork.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Integer id;
    private String lastname;
    private String name;
    private String secondname;
    private String phone;
    private String email;
}
