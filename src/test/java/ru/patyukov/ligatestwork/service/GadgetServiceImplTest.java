package ru.patyukov.ligatestwork.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.patyukov.ligatestwork.dto.GadgetDto;
import ru.patyukov.ligatestwork.entity.Employee;
import ru.patyukov.ligatestwork.entity.Gadget;
import ru.patyukov.ligatestwork.entity.TypeGadget;
import ru.patyukov.ligatestwork.exception.NotFoundException;
import ru.patyukov.ligatestwork.mapper.GadgetMapper;
import ru.patyukov.ligatestwork.repository.GadgetRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class GadgetServiceImplTest {

    @Mock
    private GadgetRepository gadgetRepository;

    @Mock
    private GadgetMapper gadgetMapper;

    @InjectMocks
    private GadgetServiceImpl gadgetService;

    @Test
    @DisplayName("Создание гаджета. Должно пройти успешно.")
    void createGadget() {
        //given

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setEmployeeId(1);
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        Gadget gadget = new Gadget();
        Employee employee = new Employee();
        employee.setId(gadgetDto.getEmployeeId());
        gadget.setId(1);
        gadget.setDiagonal(27.);
        gadget.setModel("XC-1760");
        gadget.setMk("acer");
        gadget.setEmployee(employee);
        gadget.setRam("32DDR4");
        gadget.setCpu("i512400");
        gadget.setType(TypeGadget.PC);

        GadgetDto responseGadgetDto = new GadgetDto();
        responseGadgetDto.setId(1);
        responseGadgetDto.setDiagonal(27.);
        responseGadgetDto.setModel("XC-1760");
        responseGadgetDto.setMk("acer");
        responseGadgetDto.setEmployeeId(1);
        responseGadgetDto.setRam("32DDR4");
        responseGadgetDto.setCpu("i512400");
        responseGadgetDto.setType(TypeGadget.PC);

        //when

        when(gadgetMapper.gadgetDtoToGadget(gadgetDto)).thenReturn(gadget);
        when(gadgetRepository.save(gadget)).thenReturn(gadget);
        when(gadgetMapper.gadgetToGadgetDto(gadget)).thenReturn(responseGadgetDto);

        //then

        GadgetDto result = gadgetService.createGadget(gadgetDto);
        assertEquals(responseGadgetDto, result);
    }

    @Test
    @DisplayName("Удаление гаджета. Должно пройти успешно.")
    void deleteGadget() {
        //given

        Integer gadgetId = 1;

        //when

        when(gadgetRepository.existsById(gadgetId)).thenReturn(true);

        //then

        gadgetService.deleteGadget(gadgetId);
    }

    @Test
    @DisplayName("Удаление гаджета. Не должно пройти успешно.")
    void deleteGadgetFailed() {
        //given

        Integer gadgetId = 1;

        //when

        when(gadgetRepository.existsById(gadgetId)).thenReturn(false);

        //then

        assertThrows(NotFoundException.class, () -> gadgetService.deleteGadget(gadgetId));
    }

    @Test
    @DisplayName("Обновление гаджета. Должно пройти успешно.")
    void updateGadget() {
        //given

        Integer gadgetId = 1;

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setEmployeeId(1);
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        Gadget gadget = new Gadget();
        Employee employee = new Employee();
        employee.setId(gadgetDto.getEmployeeId());
        gadget.setId(1);
        gadget.setDiagonal(27.);
        gadget.setModel("XC-1760");
        gadget.setMk("acer");
        gadget.setEmployee(employee);
        gadget.setRam("32DDR4");
        gadget.setCpu("i512400");
        gadget.setType(TypeGadget.PC);

        GadgetDto responseGadgetDto = new GadgetDto();
        responseGadgetDto.setId(1);
        responseGadgetDto.setDiagonal(27.);
        responseGadgetDto.setModel("XC-1760");
        responseGadgetDto.setMk("acer");
        responseGadgetDto.setEmployeeId(1);
        responseGadgetDto.setRam("32DDR4");
        responseGadgetDto.setCpu("i512400");
        responseGadgetDto.setType(TypeGadget.PC);

        //when

        when(gadgetMapper.gadgetDtoToGadget(gadgetDto)).thenReturn(gadget);
        when(gadgetRepository.existsById(gadgetId)).thenReturn(true);
        when(gadgetRepository.save(gadget)).thenReturn(gadget);
        when(gadgetMapper.gadgetToGadgetDto(gadget)).thenReturn(responseGadgetDto);

        //then

        GadgetDto result = gadgetService.updateGadget(gadgetId, gadgetDto);
        assertEquals(responseGadgetDto, result);
    }

    @Test
    @DisplayName("Обновление гаджета. Не должно пройти успешно.")
    void updateGadgetFailed() {
        //given

        Integer gadgetId = 1;

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setEmployeeId(1);
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        Gadget gadget = new Gadget();
        gadget.setId(1);
        gadget.setDiagonal(27.);
        gadget.setModel("XC-1760");
        gadget.setMk("acer");
        gadget.setRam("32DDR4");
        gadget.setCpu("i512400");
        gadget.setType(TypeGadget.PC);

        //when

        when(gadgetMapper.gadgetDtoToGadget(gadgetDto)).thenReturn(gadget);
        when(gadgetRepository.existsById(gadgetId)).thenReturn(false);

        //then

        assertThatThrownBy(() -> gadgetService.updateGadget(gadgetId, gadgetDto))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Получение всех гаджетов у определенного работника. Должно пройти успешно.")
    void findAllByEmployeeId() {
        //given

        Integer employeeId = 1;

        Gadget gadget = new Gadget();
        Employee employee = new Employee();
        employee.setId(employeeId);
        gadget.setId(1);
        gadget.setDiagonal(27.);
        gadget.setModel("XC-1760");
        gadget.setMk("acer");
        gadget.setEmployee(employee);
        gadget.setRam("32DDR4");
        gadget.setCpu("i512400");
        gadget.setType(TypeGadget.PC);

        GadgetDto responseGadgetDto = new GadgetDto();
        responseGadgetDto.setId(1);
        responseGadgetDto.setDiagonal(27.);
        responseGadgetDto.setModel("XC-1760");
        responseGadgetDto.setMk("acer");
        responseGadgetDto.setEmployeeId(1);
        responseGadgetDto.setRam("32DDR4");
        responseGadgetDto.setCpu("i512400");
        responseGadgetDto.setType(TypeGadget.PC);

        //when

        when(gadgetRepository.findAllByEmployeeId(employeeId)).thenReturn(List.of(gadget));
        when(gadgetMapper.gadgetToGadgetDto(gadget)).thenReturn(responseGadgetDto);

        //then

        List<GadgetDto> result = gadgetService.findAllByEmployeeId(employeeId);
        assertEquals(List.of(responseGadgetDto), result);
    }

    @Test
    @DisplayName("Получение гаджета. Должно пройти успешно.")
    void getGadgetById() {
        //given

        Integer gadgetId = 1;

        Gadget gadget = new Gadget();
        Employee employee = new Employee();
        employee.setId(1);
        gadget.setId(1);
        gadget.setDiagonal(27.);
        gadget.setModel("XC-1760");
        gadget.setMk("acer");
        gadget.setEmployee(employee);
        gadget.setRam("32DDR4");
        gadget.setCpu("i512400");
        gadget.setType(TypeGadget.PC);

        GadgetDto responseGadgetDto = new GadgetDto();
        responseGadgetDto.setId(1);
        responseGadgetDto.setDiagonal(27.);
        responseGadgetDto.setModel("XC-1760");
        responseGadgetDto.setMk("acer");
        responseGadgetDto.setEmployeeId(1);
        responseGadgetDto.setRam("32DDR4");
        responseGadgetDto.setCpu("i512400");
        responseGadgetDto.setType(TypeGadget.PC);

        //when

        when(gadgetRepository.existsById(gadgetId)).thenReturn(true);
        when(gadgetRepository.findById(gadgetId)).thenReturn(Optional.of(gadget));
        when(gadgetMapper.gadgetToGadgetDto(gadget)).thenReturn(responseGadgetDto);

        //then

        GadgetDto result = gadgetService.getGadgetById(gadgetId);
        assertEquals(responseGadgetDto, result);
    }

    @Test
    @DisplayName("Получение гаджета. Не должно пройти успешно.")
    void getGadgetByIdFailed() {
        //given

        Integer gadgetId = 1;

        //when

        when(gadgetRepository.existsById(gadgetId)).thenReturn(false);

        //then

        assertThrows(NotFoundException.class, () -> gadgetService.getGadgetById(gadgetId));
    }

    @Test
    @DisplayName("Получение всех гаджетов. Должно пройти успешно.")
    void getGadgetAll() {
        //given

        Gadget gadget = new Gadget();
        Employee employee = new Employee();
        employee.setId(1);
        gadget.setId(1);
        gadget.setDiagonal(27.);
        gadget.setModel("XC-1760");
        gadget.setMk("acer");
        gadget.setEmployee(employee);
        gadget.setRam("32DDR4");
        gadget.setCpu("i512400");
        gadget.setType(TypeGadget.PC);

        GadgetDto responseGadgetDto = new GadgetDto();
        responseGadgetDto.setId(1);
        responseGadgetDto.setDiagonal(27.);
        responseGadgetDto.setModel("XC-1760");
        responseGadgetDto.setMk("acer");
        responseGadgetDto.setEmployeeId(1);
        responseGadgetDto.setRam("32DDR4");
        responseGadgetDto.setCpu("i512400");
        responseGadgetDto.setType(TypeGadget.PC);

        //when

        when(gadgetRepository.findAll()).thenReturn(List.of(gadget));
        when(gadgetMapper.gadgetToGadgetDto(gadget)).thenReturn(responseGadgetDto);

        //then

        List<GadgetDto> result = gadgetService.getGadgetAll();
        assertEquals(List.of(responseGadgetDto), result);
    }
}