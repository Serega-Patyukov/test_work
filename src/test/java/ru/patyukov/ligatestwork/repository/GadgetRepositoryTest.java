package ru.patyukov.ligatestwork.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.patyukov.ligatestwork.entity.Employee;
import ru.patyukov.ligatestwork.entity.Gadget;
import ru.patyukov.ligatestwork.entity.TypeGadget;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GadgetRepositoryTest {

    @Autowired
    private GadgetRepository gadgetRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Получение всех гаджетов у определенного работника. Должно пройти успешно.")
    void findAllByEmployeeId() {
        //given

        Employee employee = new Employee();
        employee.setName("name");
        employee.setLastname("lastname");
        employee.setSurname("surname");
        employee.setEmail("serega-patyukov@mail.ru");
        employee.setPhone("89009553902");

        employee = employeeRepository.save(employee);

        Gadget gadget0 = new Gadget();
        gadget0.setDiagonal(27.);
        gadget0.setModel("XC-1760");
        gadget0.setMk("acer");
        gadget0.setEmployee(employee);
        gadget0.setRam("32DDR4");
        gadget0.setCpu("i512400");
        gadget0.setType(TypeGadget.PC);

        Gadget gadget1 = new Gadget();
        gadget1.setDiagonal(27.);
        gadget1.setModel("XC-1760");
        gadget1.setMk("acer");
        gadget1.setEmployee(employee);
        gadget1.setRam("32DDR4");
        gadget1.setCpu("i512400");
        gadget1.setType(TypeGadget.PC);

        gadgetRepository.save(gadget0);
        gadgetRepository.save(gadget1);

        //when

        List<Gadget> result = gadgetRepository.findAllByEmployeeId(1);

        //then

        assertEquals(List.of(gadget0, gadget1), result);
    }
}