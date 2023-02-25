package ru.patyukov.ligatestwork.web.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.patyukov.ligatestwork.entity.TypeGadget;
import ru.patyukov.ligatestwork.facade.FacadeImpl;
import ru.patyukov.ligatestwork.web.constant.WebConstant;
import ru.patyukov.ligatestwork.web.request.EmployeeRequest;
import ru.patyukov.ligatestwork.web.request.GadgetRequest;
import ru.patyukov.ligatestwork.web.response.EmployeeResponse;
import ru.patyukov.ligatestwork.web.response.GadgetResponse;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class MainControllerTest {

    @MockBean
    private FacadeImpl facade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Создание сотрудника")
    void createEmployee() throws Exception {
        //given

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("serega-patyukov@mail.ru");
        employeeRequest.setName("name2");
        employeeRequest.setPhone("89009553902");
        employeeRequest.setLastname("lastname2");
        employeeRequest.setSurname("surname2");

        EmployeeResponse employeeResponse2 = new EmployeeResponse();
        employeeResponse2.setId(2);
        employeeResponse2.setEmail("serega-patyukov@mail.ru");
        employeeResponse2.setName("name2");
        employeeResponse2.setPhone("89009553902");
        employeeResponse2.setLastname("lastname2");
        employeeResponse2.setSurname("surname2");

        //when

        when(facade.createEmployee(employeeRequest)).thenReturn(employeeResponse2);

        //then

        mockMvc.perform(post(WebConstant.VERSION_URL + "/create/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                        "    \"lastname\" : \"lastname2\",\n" +
                        "    \"name\" : \"name2\",\n" +
                        "    \"surname\" : \"surname2\",\n" +
                        "    \"phone\" : \"89009553902\",\n" +
                        "    \"email\" : \"serega-patyukov@mail.ru\"\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "    \"id\": 2,\n" +
                        "    \"lastname\": \"lastname2\",\n" +
                        "    \"name\": \"name2\",\n" +
                        "    \"surname\": \"surname2\",\n" +
                        "    \"phone\": \"89009553902\",\n" +
                        "    \"email\": \"serega-patyukov@mail.ru\",\n" +
                        "    \"gadgets\": []\n" +
                        "}"));
    }

    @Test
    @DisplayName("Создание гаджета")
    void createGadget() throws Exception {
        //given

        Integer employeeId = 1;

        GadgetRequest gadgetRequest = new GadgetRequest();
        gadgetRequest.setDiagonal(27.0);
        gadgetRequest.setModel("XC-1760");
        gadgetRequest.setMk("acer");
        gadgetRequest.setRam("32DDR4");
        gadgetRequest.setCpu("i512400");
        gadgetRequest.setType(TypeGadget.PC);

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

        when(facade.createGadget(employeeId, gadgetRequest)).thenReturn(gadgetResponse);

        //then

        mockMvc.perform(post(WebConstant.VERSION_URL + "/create/gadget/employeeid/" + employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"type\": \"PC\",\n" +
                                "    \"cpu\": \"i512400\",\n" +
                                "    \"diagonal\": 27.0,\n" +
                                "    \"ram\": \"32DDR4\",\n" +
                                "    \"mk\": \"acer\",\n" +
                                "    \"model\": \"XC-1760\"\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"type\": \"PC\",\n" +
                        "    \"cpu\": \"i512400\",\n" +
                        "    \"diagonal\": 27.0,\n" +
                        "    \"ram\": \"32DDR4\",\n" +
                        "    \"mk\": \"acer\",\n" +
                        "    \"model\": \"XC-1760\",\n" +
                        "    \"employeeId\": 1\n" +
                        "}"));
    }

    @Test
    @DisplayName("Удаление гаджета")
    void deleteGadget() throws Exception {
        //given

        Integer gadgetId = 1;

        //when

        //then

        mockMvc.perform(delete(WebConstant.VERSION_URL + "/delete/gadget/gadgetid/" + gadgetId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Удаление сотрудника")
    void deleteEmployee() throws Exception {
        //given

        Integer employeeId = 1;

        //when

        //then

        mockMvc.perform(delete(WebConstant.VERSION_URL + "/delete/employee/employeeid/" + employeeId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Обновление сотрудника")
    void updateEmployee() throws Exception {
        //given

        Integer employeeId = 2;

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmail("serega-patyukov@mail.ru");
        employeeRequest.setName("name2");
        employeeRequest.setPhone("89009553902");
        employeeRequest.setLastname("lastname2");
        employeeRequest.setSurname("surname2");

        EmployeeResponse employeeResponse2 = new EmployeeResponse();
        employeeResponse2.setId(2);
        employeeResponse2.setEmail("serega-patyukov@mail.ru");
        employeeResponse2.setName("name2");
        employeeResponse2.setPhone("89009553902");
        employeeResponse2.setLastname("lastname2");
        employeeResponse2.setSurname("surname2");

        //when

        when(facade.updateEmployee(employeeId, employeeRequest)).thenReturn(employeeResponse2);

        //then

        mockMvc.perform(put(WebConstant.VERSION_URL + "/update/employee/employeeid/" + employeeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"lastname\" : \"lastname2\",\n" +
                                "    \"name\" : \"name2\",\n" +
                                "    \"surname\" : \"surname2\",\n" +
                                "    \"phone\" : \"89009553902\",\n" +
                                "    \"email\" : \"serega-patyukov@mail.ru\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 2,\n" +
                        "    \"lastname\": \"lastname2\",\n" +
                        "    \"name\": \"name2\",\n" +
                        "    \"surname\": \"surname2\",\n" +
                        "    \"phone\": \"89009553902\",\n" +
                        "    \"email\": \"serega-patyukov@mail.ru\",\n" +
                        "    \"gadgets\": []\n" +
                        "}"));
    }

    @Test
    @DisplayName("Обновление гаджета")
    void updateGadget() throws Exception {
        //given

        Integer employeeId = 1;
        Integer gadgetId = 1;

        GadgetRequest gadgetRequest = new GadgetRequest();
        gadgetRequest.setDiagonal(27.0);
        gadgetRequest.setModel("XC-1760");
        gadgetRequest.setMk("acer");
        gadgetRequest.setRam("32DDR4");
        gadgetRequest.setCpu("i512400");
        gadgetRequest.setType(TypeGadget.PC);

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

        when(facade.updateGadget(employeeId, gadgetId, gadgetRequest)).thenReturn(gadgetResponse);

        //then

        mockMvc.perform(put(WebConstant.VERSION_URL + "/update/gadget/employeeid/" + employeeId + " /gadgetid/" + gadgetId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"type\": \"PC\",\n" +
                                "    \"cpu\": \"i512400\",\n" +
                                "    \"diagonal\": 27.0,\n" +
                                "    \"ram\": \"32DDR4\",\n" +
                                "    \"mk\": \"acer\",\n" +
                                "    \"model\": \"XC-1760\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"type\": \"PC\",\n" +
                        "    \"cpu\": \"i512400\",\n" +
                        "    \"diagonal\": 27.0,\n" +
                        "    \"ram\": \"32DDR4\",\n" +
                        "    \"mk\": \"acer\",\n" +
                        "    \"model\": \"XC-1760\",\n" +
                        "    \"employeeId\": 1\n" +
                        "}"));
    }

    @Test
    @DisplayName("Получение сотрудника")
    void getEmployeeById() throws Exception {
        //given

        Integer employeeId = 1;

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employeeId);
        employeeResponse.setEmail("serega-patyukov@mail.ru");
        employeeResponse.setName("name");
        employeeResponse.setPhone("89009553902");
        employeeResponse.setLastname("lastname");
        employeeResponse.setSurname("surname");

        //when

        when(facade.getEmployeeById(employeeId)).thenReturn(employeeResponse);

        //then

        mockMvc.perform(get(WebConstant.VERSION_URL + "/get/employee/employeeid/" + employeeId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"lastname\": \"lastname\",\n" +
                        "    \"name\": \"name\",\n" +
                        "    \"surname\": \"surname\",\n" +
                        "    \"phone\": \"89009553902\",\n" +
                        "    \"email\": \"serega-patyukov@mail.ru\",\n" +
                        "    \"gadgets\": []\n" +
                        "}"));
    }

    @Test
    @DisplayName("Получение всех сотрудников")
    void getEmployeeAll() throws Exception {
        //given

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(1);
        gadgetResponse.setDiagonal(27.0);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);

        EmployeeResponse employeeResponse1 = new EmployeeResponse();
        employeeResponse1.setId(1);
        employeeResponse1.setEmail("serega-patyukov@mail.ru");
        employeeResponse1.setName("name");
        employeeResponse1.setPhone("89009553902");
        employeeResponse1.setLastname("lastname");
        employeeResponse1.setSurname("surname");
        employeeResponse1.getGadgets().add(gadgetResponse);

        EmployeeResponse employeeResponse2 = new EmployeeResponse();
        employeeResponse2.setId(2);
        employeeResponse2.setEmail("serega-patyukov@mail.ru");
        employeeResponse2.setName("name2");
        employeeResponse2.setPhone("89009553902");
        employeeResponse2.setLastname("lastname2");
        employeeResponse2.setSurname("surname2");

        //when

        when(facade.getEmployeeAll()).thenReturn(List.of(employeeResponse1, employeeResponse2));

        //then

        mockMvc.perform(get(WebConstant.VERSION_URL + "/get/employee/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"lastname\": \"lastname\",\n" +
                        "        \"name\": \"name\",\n" +
                        "        \"surname\": \"surname\",\n" +
                        "        \"phone\": \"89009553902\",\n" +
                        "        \"email\": \"serega-patyukov@mail.ru\",\n" +
                        "        \"gadgets\": [\n" +
                        "            {\n" +
                        "                \"id\": 1,\n" +
                        "                \"type\": \"PC\",\n" +
                        "                \"cpu\": \"i512400\",\n" +
                        "                \"diagonal\": 27.0,\n" +
                        "                \"ram\": \"32DDR4\",\n" +
                        "                \"mk\": \"acer\",\n" +
                        "                \"model\": \"XC-1760\",\n" +
                        "                \"employeeId\": 1\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"lastname\": \"lastname2\",\n" +
                        "        \"name\": \"name2\",\n" +
                        "        \"surname\": \"surname2\",\n" +
                        "        \"phone\": \"89009553902\",\n" +
                        "        \"email\": \"serega-patyukov@mail.ru\",\n" +
                        "        \"gadgets\": []\n" +
                        "    }\n" +
                        "]"));
    }

    @Test
    @DisplayName("Получение гаджета")
    void getGadgetById() throws Exception {
        //given

        Integer gadgetId = 1;

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(1);
        gadgetResponse.setDiagonal(27.0);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);

        //when

        when(facade.getGadgetById(gadgetId)).thenReturn(gadgetResponse);

        //then

        mockMvc.perform(get(WebConstant.VERSION_URL + "/get/gadget/gadgetid/" + gadgetId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"type\": \"PC\",\n" +
                        "    \"cpu\": \"i512400\",\n" +
                        "    \"diagonal\": 27.0,\n" +
                        "    \"ram\": \"32DDR4\",\n" +
                        "    \"mk\": \"acer\",\n" +
                        "    \"model\": \"XC-1760\",\n" +
                        "    \"employeeId\": 1\n" +
                        "}"));
    }

    @Test
    @DisplayName("Получение всех гаджетов")
    void getGadgetAll() throws Exception {
        //given

        GadgetResponse gadgetResponse = new GadgetResponse();
        gadgetResponse.setId(1);
        gadgetResponse.setEmployeeId(1);
        gadgetResponse.setDiagonal(27.0);
        gadgetResponse.setModel("XC-1760");
        gadgetResponse.setMk("acer");
        gadgetResponse.setRam("32DDR4");
        gadgetResponse.setCpu("i512400");
        gadgetResponse.setType(TypeGadget.PC);

        //when

        when(facade.getGadgetAll()).thenReturn(List.of(gadgetResponse));

        //then

        mockMvc.perform(get(WebConstant.VERSION_URL + "/get/gadget/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"type\": \"PC\",\n" +
                        "        \"cpu\": \"i512400\",\n" +
                        "        \"diagonal\": 27.0,\n" +
                        "        \"ram\": \"32DDR4\",\n" +
                        "        \"mk\": \"acer\",\n" +
                        "        \"model\": \"XC-1760\",\n" +
                        "        \"employeeId\": 1\n" +
                        "    }\n" +
                        "]"));
    }
}