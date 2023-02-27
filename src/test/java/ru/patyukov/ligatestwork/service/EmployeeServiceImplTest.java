package ru.patyukov.ligatestwork.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.patyukov.ligatestwork.dto.EmployeeDto;
import ru.patyukov.ligatestwork.entity.Employee;
import ru.patyukov.ligatestwork.exception.NotFoundException;
import ru.patyukov.ligatestwork.mapper.EmployeeMapper;
import ru.patyukov.ligatestwork.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    @DisplayName("Создание работника. Должно пройти успешно.")
    void createEmployee() {
        //given

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("name");
        employeeDto.setLastname("lastname");
        employeeDto.setSecondname("surname");
        employeeDto.setEmail("serega-patyukov@mail.ru");
        employeeDto.setPhone("89009553902");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("name");
        employee.setLastname("lastname");
        employee.setSecondname("surname");
        employee.setEmail("serega-patyukov@mail.ru");
        employee.setPhone("89009553902");

        EmployeeDto responseEmployeeDto = new EmployeeDto();
        responseEmployeeDto.setId(1);
        responseEmployeeDto.setName("name");
        responseEmployeeDto.setLastname("lastname");
        responseEmployeeDto.setSecondname("surname");
        responseEmployeeDto.setEmail("serega-patyukov@mail.ru");
        responseEmployeeDto.setPhone("89009553902");

        //when

        when(employeeMapper.employeeDtoToEmployee(employeeDto)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.employeeToEmployeeDto(employee)).thenReturn(responseEmployeeDto);

        //then

        EmployeeDto result = employeeService.createEmployee(employeeDto);
        assertEquals(responseEmployeeDto, result);
    }

    @Test
    @DisplayName("Наличие работника в бд. Должно пройти успешно.")
    void existsById() {
        //given

        Integer employeeId = 1;

        //when

        when(employeeRepository.existsById(employeeId)).thenReturn(true);

        //then

        boolean result = employeeService.existsById(employeeId);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Наличие работника в бд. Не должно пройти успешно.")
    void existsByIdFailed() {
        //given

        Integer employeeId = 1;

        //when

        when(employeeRepository.existsById(employeeId)).thenReturn(false);

        //then

        boolean result = employeeService.existsById(employeeId);
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Удаление работника. Должно пройти успешно.")
    void deleteEmployee() {
        //given

        Integer employeeId = 1;

        //when

        when(employeeRepository.existsById(employeeId)).thenReturn(true);

        //then

        employeeService.deleteEmployee(employeeId);
    }

    @Test
    @DisplayName("Удаление работника. Не должно пройти успешно.")
    void deleteEmployeeFailed() {
        //given

        Integer employeeId = 1;

        //when

        when(employeeRepository.existsById(employeeId)).thenReturn(false);

        //then

        assertThrows(NotFoundException.class, () -> employeeService.deleteEmployee(employeeId));
    }

    @Test
    @DisplayName("Обновление работника. Должно пройти успешно.")
    void updateEmployee() {
        //given

        Integer employeeId = 1;

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("name");
        employeeDto.setLastname("lastname");
        employeeDto.setSecondname("surname");
        employeeDto.setEmail("serega-patyukov@mail.ru");
        employeeDto.setPhone("89009553902");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("name");
        employee.setLastname("lastname");
        employee.setSecondname("surname");
        employee.setEmail("serega-patyukov@mail.ru");
        employee.setPhone("89009553902");

        EmployeeDto responseEmployeeDto = new EmployeeDto();
        responseEmployeeDto.setId(1);
        responseEmployeeDto.setName("name");
        responseEmployeeDto.setLastname("lastname");
        responseEmployeeDto.setSecondname("surname");
        responseEmployeeDto.setEmail("serega-patyukov@mail.ru");
        responseEmployeeDto.setPhone("89009553902");

        //when

        when(employeeMapper.employeeDtoToEmployee(employeeDto)).thenReturn(employee);
        when(employeeRepository.existsById(employeeId)).thenReturn(true);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeMapper.employeeToEmployeeDto(employee)).thenReturn(responseEmployeeDto);

        //then

        EmployeeDto result = employeeService.updateEmployee(employeeId, employeeDto);
        assertEquals(responseEmployeeDto, result);
    }

    @Test
    @DisplayName("Обновление работника. Не должно пройти успешно.")
    void updateEmployeeFailed() {
        //given

        Integer employeeId = 1;

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("name");
        employeeDto.setLastname("lastname");
        employeeDto.setSecondname("surname");
        employeeDto.setEmail("serega-patyukov@mail.ru");
        employeeDto.setPhone("89009553902");

        Employee employee = new Employee();
        employee.setName("name");
        employee.setLastname("lastname");
        employee.setSecondname("surname");
        employee.setEmail("serega-patyukov@mail.ru");
        employee.setPhone("89009553902");

        //when

        when(employeeMapper.employeeDtoToEmployee(employeeDto)).thenReturn(employee);
        when(employeeRepository.existsById(employeeId)).thenReturn(false);

        //then

        assertThatThrownBy(() -> employeeService.updateEmployee(employeeId, employeeDto))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Получение работника. Должно пройти успешно.")
    void getEmployeeById() {
        //given

        Integer employeeId = 1;

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("name");
        employee.setLastname("lastname");
        employee.setSecondname("surname");
        employee.setEmail("serega-patyukov@mail.ru");
        employee.setPhone("89009553902");

        EmployeeDto responseEmployeeDto = new EmployeeDto();
        responseEmployeeDto.setId(1);
        responseEmployeeDto.setName("name");
        responseEmployeeDto.setLastname("lastname");
        responseEmployeeDto.setSecondname("surname");
        responseEmployeeDto.setEmail("serega-patyukov@mail.ru");
        responseEmployeeDto.setPhone("89009553902");

        //when

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeMapper.employeeToEmployeeDto(employee)).thenReturn(responseEmployeeDto);

        //then

        EmployeeDto result = employeeService.getEmployeeById(employeeId);
        assertEquals(responseEmployeeDto, result);
    }

    @Test
    @DisplayName("Получение работника. Не должно пройти успешно.")
    void getEmployeeByIdFailed() {
        //given

        Integer employeeId = 1;

        //when

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        //then

        assertThrows(NotFoundException.class, () -> employeeService.getEmployeeById(employeeId));
    }

    @Test
    @DisplayName("Получение всех работников. Должно пройти успешно.")
    void getEmployeeAll() {
        //given

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("name");
        employee.setLastname("lastname");
        employee.setSecondname("surname");
        employee.setEmail("serega-patyukov@mail.ru");
        employee.setPhone("89009553902");

        EmployeeDto responseEmployeeDto = new EmployeeDto();
        responseEmployeeDto.setId(1);
        responseEmployeeDto.setName("name");
        responseEmployeeDto.setLastname("lastname");
        responseEmployeeDto.setSecondname("surname");
        responseEmployeeDto.setEmail("serega-patyukov@mail.ru");
        responseEmployeeDto.setPhone("89009553902");

        //when

        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        when(employeeMapper.employeeToEmployeeDto(employee)).thenReturn(responseEmployeeDto);

        //then

        List<EmployeeDto> result = employeeService.getEmployeeAll();
        assertEquals(List.of(responseEmployeeDto), result);
    }
}