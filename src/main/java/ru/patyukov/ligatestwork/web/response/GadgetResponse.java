package ru.patyukov.ligatestwork.web.response;

import lombok.Data;
import ru.patyukov.ligatestwork.entity.TypeGadget;

@Data
public class GadgetResponse {
    private Integer id;
    private TypeGadget type;
    private String cpu;
    private Double diagonal;
    private String ram;
    private String mk;
    private String model;
    private Integer employeeId;
}
