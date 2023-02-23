package ru.patyukov.ligatestwork.mapper;

import org.mapstruct.Mapper;
import ru.patyukov.ligatestwork.dto.GadgetDto;
import ru.patyukov.ligatestwork.entity.Gadget;
import ru.patyukov.ligatestwork.web.request.GadgetRequest;
import ru.patyukov.ligatestwork.web.response.GadgetResponse;

@Mapper(componentModel = "spring")
public interface GadgetMapper {
    GadgetDto gadgetRequestToGadgetDto(GadgetRequest gadgetRequest);
    Gadget gadgetDtoToGadget(GadgetDto gadgetDto);
    GadgetDto gadgetToGadgetDto(Gadget gadget);
    GadgetResponse gadgetDtoToGadgetResponse(GadgetDto gadgetDto);
}
