package ru.patyukov.ligatestwork.service;

import ru.patyukov.ligatestwork.dto.GadgetDto;

import java.util.List;

public interface GadgetService {
    GadgetDto createGadget(GadgetDto gadgetDto);

    void deleteGadget(Integer gadgetId);

    GadgetDto updateGadget(Integer gadgetId, GadgetDto gadgetDto);

    List<GadgetDto> findAllByEmployeeId(Integer employeeId);

    GadgetDto getGadgetById(Integer gadgetId);
}
