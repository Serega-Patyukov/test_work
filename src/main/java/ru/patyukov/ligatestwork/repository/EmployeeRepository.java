package ru.patyukov.ligatestwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patyukov.ligatestwork.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
