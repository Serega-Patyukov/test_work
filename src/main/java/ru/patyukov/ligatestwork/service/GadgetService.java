package ru.patyukov.ligatestwork.service;

import ru.patyukov.ligatestwork.dto.GadgetDto;

public interface GadgetService {
    GadgetDto createGadget(GadgetDto gadgetDto);

    void deleteGadget(Integer gadgetId);

    GadgetDto updateGadget(Integer gadgetId, GadgetDto gadgetDto);
}
