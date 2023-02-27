package ru.patyukov.ligatestwork.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 50)
    private String lastname;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    private String secondname;

    @NotNull
    @Pattern(regexp = "^((([+][7])|[8])[0-9]{10})$",
            message = "phone error. example 89009553902 or +79009553902")
    private String phone;

    @NotNull
    @Email
    private String email;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "employee",
            cascade = CascadeType.ALL)
    private List<Gadget> gadgets = new ArrayList<>();
}
