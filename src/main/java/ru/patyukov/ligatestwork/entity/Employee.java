package ru.patyukov.ligatestwork.entity;

import jakarta.persistence.*;
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
    @Size(min = 3, message = "Минимум 3 символа.")
    private String lastname;

    @NotNull
    @Size(min = 3, message = "Минимум 3 символа.")
    private String name;

    @NotNull
    @Size(min = 3, message = "Минимум 3 символа.")
    private String surname;

    @NotNull
    @Pattern(regexp = "^((([+][7])|[8])[0-9]{10})$",
            message = "Должно быть 11 цифр. Первая цифра +7 или 8.")
    private String phone;

    @NotNull
    private String email;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "employee",
            cascade = CascadeType.ALL)
    private List<Gadget> gadgets = new ArrayList<>();
}
