package ru.patyukov.ligatestwork.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Gadget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeGadget type;

    @NotNull
    @Size(min = 3, message = "Минимум 3 символа.")
    private String cpu;

    @NotNull
    private Double diagonal;

    @NotNull
    @Size(min = 3, message = "Минимум 3 символа.")
    private String ram;

    @NotNull
    @Size(min = 3, message = "Минимум 3 символа.")
    private String mk;

    @NotNull
    @Size(min = 3, message = "Минимум 3 символа.")
    private String model;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
