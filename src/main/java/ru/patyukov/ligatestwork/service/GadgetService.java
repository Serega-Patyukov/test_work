package ru.patyukov.ligatestwork.service;

import ru.patyukov.ligatestwork.dto.GadgetDto;
import ru.patyukov.ligatestwork.entity.Gadget;

import java.util.List;

public interface GadgetService {
    GadgetDto createGadget(GadgetDto gadgetDto);

    void deleteGadget(Integer gadgetId);

    GadgetDto updateGadget(Integer gadgetId, GadgetDto gadgetDto);

    List<GadgetDto> findAllByEmployeeId(Integer employeeId);
}
