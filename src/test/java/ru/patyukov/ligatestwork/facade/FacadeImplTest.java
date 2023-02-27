package ru.patyukov.ligatestwork.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.patyukov.ligatestwork.dto.EmployeeDto;
import ru.patyukov.ligatestwork.dto.GadgetDto;
import ru.patyukov.ligatestwork.entity.TypeGadget;
import ru.patyukov.ligatestwork.exception.NotFoundException;
import ru.patyukov.ligatestwork.mapper.EmployeeMapper;
import ru.patyukov.ligatestwork.mapper.GadgetMapper;
import ru.patyukov.ligatestwork.service.EmployeeServiceImpl;
import ru.patyukov.ligatestwork.service.GadgetServiceImpl;
import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.request.GadgetRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;
import ru.patyukov.ligatestwork.web.response.GadgetResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class FacadeImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private GadgetServiceImpl gadgetService;

    @Mock
    private GadgetMapper gadgetMapper;

    @InjectMocks
    private FacadeImpl facade;

    @Test
    @DisplayName("Создание работника. Должно пройти успешно.")
    void createEmployee() {
        //given

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("serega-patyukov@mail.ru");
        employeeRequest.setName("name");
        employeeRequest.setPhone("89009553902");
        employeeRequest.setLastname("lastname");
        employeeRequest.setSecondname("surname");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmail("serega-patyukov@mail.ru");
        employeeDto.setName("name");
        employeeDto.setPhone("89009553902");
        employeeDto.setLastname("lastname");
        employeeDto.setSecondname("surname");

        EmployeeDto responseEmployeeDto = new EmployeeDto();
        responseEmployeeDto.setId(1);
        responseEmployeeDto.setEmail("serega-patyukov@mail.ru");
        responseEmployeeDto.setName("name");
        responseEmployeeDto.setPhone("89009553902");
        responseEmployeeDto.setLastname("lastname");
        responseEmployeeDto.setSecondname("surname");

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1);
        employeeResponse.setEmail("serega-patyukov@mail.ru");
        employeeResponse.setName("name");
        employeeResponse.setPhone("89009553902");
        employeeResponse.setLastname("lastname");
        employeeResponse.setSecondname("surname");

        //when

        when(employeeMapper.employeeRequestToEmployeeDto(employeeRequest)).thenReturn(employeeDto);
        when(employeeService.createEmployee(employeeDto)).thenReturn(responseEmployeeDto);
        when(employeeMapper.employeeDtoToEmployeeResponse(responseEmployeeDto)).thenReturn(employeeResponse);

        //then

        EmployeeResponse result = facade.createEmployee(employeeRequest);
        assertEquals(employeeResponse, result);
    }

    @Test
    @DisplayName("Создание гаджета. Должно пройти успешно.")
    void createGadget() {
        //given

        Integer employeeId = 1;

        GadgetRequest gadgetRequest = new GadgetRequest();
        gadgetRequest.setDiagonal(27.);
        gadgetRequest.setModel("XC-1760");
        gadgetRequest.setMk("acer");
        gadgetRequest.setRam("32DDR4");
        gadgetRequest.setCpu("i512400");
        gadgetRequest.setType(TypeGadget.PC);

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        GadgetDto gadgetDtoSetEmployeeId = new GadgetDto();
        gadgetDtoSetEmployeeId.setEmployeeId(employeeId);
        gadgetDtoSetEmployeeId.setDiagonal(27.);
        gadgetDtoSetEmployeeId.setModel("XC-1760");
        gadgetDtoSetEmployeeId.setMk("acer");
        gadgetDtoSetEmployeeId.setRam("32DDR4");
        gadgetDtoSetEmployeeId.setCpu("i512400");
        gadgetDtoSetEmployeeId.setType(TypeGadget.PC);

        GadgetDto responseGadgetDto = new GadgetDto();
        responseGadgetDto.setId(1);
        responseGadgetDto.setEmployeeId(employeeId);
        responseGadgetDto.setDiagonal(27.);
        responseGadgetDto.setModel("XC-1760");
        responseGadgetDto.setMk("acer");
        responseGadgetDto.setRam("32DDR4");
        responseGadgetDto.setCpu("i512400");
        responseGadgetDto.setType(TypeGadget.PC);

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(employeeId);
        gadgetResponse.setDiagonal(27.);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);


        //when

        when(gadgetMapper.gadgetRequestToGadgetDto(gadgetRequest)).thenReturn(gadgetDto);
        when(employeeService.existsById(employeeId)).thenReturn(true);
        when(gadgetService.createGadget(gadgetDtoSetEmployeeId)).thenReturn(responseGadgetDto);
        when(gadgetMapper.gadgetDtoToGadgetResponse(responseGadgetDto)).thenReturn(gadgetResponse);

        //then

        GadgetResponse result = facade.createGadget(employeeId, gadgetRequest);
        assertEquals(gadgetResponse, result);
    }

    @Test
    @DisplayName("Создание гаджета. Не должно пройти успешно.")
    void createGadgetFailed() {
        //given

        Integer employeeId = 1;

        GadgetRequest gadgetRequest = new GadgetRequest();
        gadgetRequest.setDiagonal(27.);
        gadgetRequest.setModel("XC-1760");
        gadgetRequest.setMk("acer");
        gadgetRequest.setRam("32DDR4");
        gadgetRequest.setCpu("i512400");
        gadgetRequest.setType(TypeGadget.PC);

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        //when

        when(gadgetMapper.gadgetRequestToGadgetDto(gadgetRequest)).thenReturn(gadgetDto);
        when(employeeService.existsById(employeeId)).thenReturn(false);

        //then

        assertThrows(NotFoundException.class, () -> facade.createGadget(employeeId, gadgetRequest));
    }

    @Test
    @DisplayName("Удаление гаджета. Должно пройти успешно.")
    void deleteGadget() {
        //given

        Integer gadgetId = 1;

        //when

        //then

        facade.deleteGadget(gadgetId);
    }

    @Test
    @DisplayName("Удаление работника. Должно пройти успешно.")
    void deleteEmployee() {
        //given

        Integer employeeId = 1;

        //when

        //then

        facade.deleteEmployee(employeeId);
    }

    @Test
    @DisplayName("Обновление работника. Должно пройти успешно.")
    void updateEmployee() {
        //given

        Integer employeeId = 1;

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("serega-patyukov@mail.ru");
        employeeRequest.setName("name");
        employeeRequest.setPhone("89009553902");
        employeeRequest.setLastname("lastname");
        employeeRequest.setSecondname("surname");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmail("serega-patyukov@mail.ru");
        employeeDto.setName("name");
        employeeDto.setPhone("89009553902");
        employeeDto.setLastname("lastname");
        employeeDto.setSecondname("surname");

        EmployeeDto responseEmployeeDto = new EmployeeDto();
        responseEmployeeDto.setId(1);
        responseEmployeeDto.setEmail("serega-patyukov@mail.ru");
        responseEmployeeDto.setName("name");
        responseEmployeeDto.setPhone("89009553902");
        responseEmployeeDto.setLastname("lastname");
        responseEmployeeDto.setSecondname("surname");

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1);
        employeeResponse.setEmail("serega-patyukov@mail.ru");
        employeeResponse.setName("name");
        employeeResponse.setPhone("89009553902");
        employeeResponse.setLastname("lastname");
        employeeResponse.setSecondname("surname");

        //when

        when(employeeMapper.employeeRequestToEmployeeDto(employeeRequest)).thenReturn(employeeDto);
        when(employeeService.updateEmployee(employeeId, employeeDto)).thenReturn(responseEmployeeDto);
        when(employeeMapper.employeeDtoToEmployeeResponse(responseEmployeeDto)).thenReturn(employeeResponse);

        //then

        EmployeeResponse result = facade.updateEmployee(employeeId, employeeRequest);
        assertEquals(employeeResponse, result);
    }

    @Test
    @DisplayName("Обновление гаджета. Должно пройти успешно.")
    void updateGadget() {
        //given

        Integer employeeId = 1;

        Integer gadgetId = 1;

        GadgetRequest gadgetRequest = new GadgetRequest();
        gadgetRequest.setDiagonal(27.);
        gadgetRequest.setModel("XC-1760");
        gadgetRequest.setMk("acer");
        gadgetRequest.setRam("32DDR4");
        gadgetRequest.setCpu("i512400");
        gadgetRequest.setType(TypeGadget.PC);

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        GadgetDto gadgetDtoSetEmployeeId = new GadgetDto();
        gadgetDtoSetEmployeeId.setEmployeeId(employeeId);
        gadgetDtoSetEmployeeId.setDiagonal(27.);
        gadgetDtoSetEmployeeId.setModel("XC-1760");
        gadgetDtoSetEmployeeId.setMk("acer");
        gadgetDtoSetEmployeeId.setRam("32DDR4");
        gadgetDtoSetEmployeeId.setCpu("i512400");
        gadgetDtoSetEmployeeId.setType(TypeGadget.PC);

        GadgetDto responseGadgetDto = new GadgetDto();
        responseGadgetDto.setId(1);
        responseGadgetDto.setEmployeeId(employeeId);
        responseGadgetDto.setDiagonal(27.);
        responseGadgetDto.setModel("XC-1760");
        responseGadgetDto.setMk("acer");
        responseGadgetDto.setRam("32DDR4");
        responseGadgetDto.setCpu("i512400");
        responseGadgetDto.setType(TypeGadget.PC);

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(employeeId);
        gadgetResponse.setDiagonal(27.);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);

        //when

        when(gadgetMapper.gadgetRequestToGadgetDto(gadgetRequest)).thenReturn(gadgetDto);
        when(employeeService.existsById(employeeId)).thenReturn(true);
        when(gadgetService.updateGadget(gadgetId, gadgetDtoSetEmployeeId)).thenReturn(responseGadgetDto);
        when(gadgetMapper.gadgetDtoToGadgetResponse(responseGadgetDto)).thenReturn(gadgetResponse);

        //then

        GadgetResponse result = facade.updateGadget(employeeId, gadgetId, gadgetRequest);
        assertEquals(gadgetResponse, result);
    }

    @Test
    @DisplayName("Обновление гаджета. Не должно пройти успешно.")
    void updateGadgetFailed() {
        //given

        Integer employeeId = 1;

        Integer gadgetId = 1;

        GadgetRequest gadgetRequest = new GadgetRequest();
        gadgetRequest.setDiagonal(27.);
        gadgetRequest.setModel("XC-1760");
        gadgetRequest.setMk("acer");
        gadgetRequest.setRam("32DDR4");
        gadgetRequest.setCpu("i512400");
        gadgetRequest.setType(TypeGadget.PC);

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        //when

        when(gadgetMapper.gadgetRequestToGadgetDto(gadgetRequest)).thenReturn(gadgetDto);
        when(employeeService.existsById(employeeId)).thenReturn(false);

        //then

        assertThrows(NotFoundException.class, () -> facade.updateGadget(employeeId, gadgetId, gadgetRequest));
    }

    @Test
    @DisplayName("Получение работника. Должно пройти успешно.")
    void getEmployeeById() {
        //given

        Integer employeeId = 1;

        GadgetDto responseGadgetDto = new GadgetDto();
        responseGadgetDto.setId(1);
        responseGadgetDto.setEmployeeId(employeeId);
        responseGadgetDto.setDiagonal(27.);
        responseGadgetDto.setModel("XC-1760");
        responseGadgetDto.setMk("acer");
        responseGadgetDto.setRam("32DDR4");
        responseGadgetDto.setCpu("i512400");
        responseGadgetDto.setType(TypeGadget.PC);

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(employeeId);
        gadgetResponse.setDiagonal(27.);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);

        EmployeeDto responseEmployeeDto = new EmployeeDto();
        responseEmployeeDto.setId(1);
        responseEmployeeDto.setEmail("serega-patyukov@mail.ru");
        responseEmployeeDto.setName("name");
        responseEmployeeDto.setPhone("89009553902");
        responseEmployeeDto.setLastname("lastname");
        responseEmployeeDto.setSecondname("surname");

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1);
        employeeResponse.setEmail("serega-patyukov@mail.ru");
        employeeResponse.setName("name");
        employeeResponse.setPhone("89009553902");
        employeeResponse.setLastname("lastname");
        employeeResponse.setSecondname("surname");
        employeeResponse.setGadgets(List.of(gadgetResponse));

        //when

        when(employeeService.getEmployeeById(employeeId)).thenReturn(responseEmployeeDto);
        when(employeeMapper.employeeDtoToEmployeeResponse(responseEmployeeDto)).thenReturn(employeeResponse);
        when(gadgetService.findAllByEmployeeId(employeeId)).thenReturn(List.of(responseGadgetDto));
        when(gadgetMapper.gadgetDtoToGadgetResponse(responseGadgetDto)).thenReturn(gadgetResponse);

        //then

        EmployeeResponse result = facade.getEmployeeById(employeeId);
        assertEquals(employeeResponse, result);
    }

    @Test
    @DisplayName("Получение всех работников. Должно пройти успешно.")
    void getEmployeeAll() {
        //given

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setId(1);
        gadgetDto.setEmployeeId(1);
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(1);
        gadgetResponse.setDiagonal(27.);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1);
        employeeDto.setEmail("serega-patyukov@mail.ru");
        employeeDto.setName("name");
        employeeDto.setPhone("89009553902");
        employeeDto.setLastname("lastname");
        employeeDto.setSecondname("surname");

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1);
        employeeResponse.setEmail("serega-patyukov@mail.ru");
        employeeResponse.setName("name");
        employeeResponse.setPhone("89009553902");
        employeeResponse.setLastname("lastname");
        employeeResponse.setSecondname("surname");
        employeeResponse.setGadgets(List.of(gadgetResponse));

        //when

        when(employeeService.getEmployeeAll()).thenReturn(List.of(employeeDto));
        when(employeeMapper.employeeDtoToEmployeeResponse(employeeDto)).thenReturn(employeeResponse);
        when(gadgetService.findAllByEmployeeId(employeeResponse.getId())).thenReturn(List.of(gadgetDto));
        when(gadgetMapper.gadgetDtoToGadgetResponse(gadgetDto)).thenReturn(gadgetResponse);

        //then

        List<EmployeeResponse> result = facade.getEmployeeAll();
        assertEquals(List.of(employeeResponse), result);
    }

    @Test
    @DisplayName("Получение гаджета. Должно пройти успешно.")
    void getGadgetById() {
        //given

        Integer gadgetId = 1;

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setId(1);
        gadgetDto.setEmployeeId(1);
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(1);
        gadgetResponse.setDiagonal(27.);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);

        //when

        when(gadgetService.getGadgetById(gadgetId)).thenReturn(gadgetDto);
        when(gadgetMapper.gadgetDtoToGadgetResponse(gadgetDto)).thenReturn(gadgetResponse);

        //then

        GadgetResponse result = facade.getGadgetById(gadgetId);
        assertEquals(gadgetResponse, result);
    }

    @Test
    @DisplayName("Получение всех гаджета. Должно пройти успешно.")
    void getGadgetAll() {
        //given

        GadgetDto gadgetDto = new GadgetDto();
        gadgetDto.setId(1);
        gadgetDto.setEmployeeId(1);
        gadgetDto.setDiagonal(27.);
        gadgetDto.setModel("XC-1760");
        gadgetDto.setMk("acer");
        gadgetDto.setRam("32DDR4");
        gadgetDto.setCpu("i512400");
        gadgetDto.setType(TypeGadget.PC);

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(1);
        gadgetResponse.setDiagonal(27.);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);

        //when

        when(gadgetService.getGadgetAll()).thenReturn(List.of(gadgetDto));
        when(gadgetMapper.gadgetDtoToGadgetResponse(gadgetDto)).thenReturn(gadgetResponse);

        //then

        List<GadgetResponse> result = facade.getGadgetAll();
        assertEquals(List.of(gadgetResponse), result);
    }
}