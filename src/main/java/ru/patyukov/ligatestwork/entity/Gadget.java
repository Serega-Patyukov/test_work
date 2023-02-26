package ru.patyukov.ligatestwork.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Size(min = 3, max = 255)
    private String cpu;

    @NotNull
    @Min(5)
    @Max(50)
    private Double diagonal;

    @NotNull
    @Size(min = 3, max = 255)
    private String ram;

    @NotNull
    @Size(min = 2, max = 255)
    private String mk;

    @NotNull
    @Size(min = 3, max = 255)
    private String model;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
