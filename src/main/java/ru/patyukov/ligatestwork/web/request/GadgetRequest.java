package ru.patyukov.ligatestwork.web.request;

import lombok.Data;
import ru.patyukov.ligatestwork.entity.TypeGadget;

@Data
public class GadgetRequest {
    private TypeGadget type;
    private String cpu;
    private Double diagonal;
    private String ram;
    private String mk;
    private String model;
}
