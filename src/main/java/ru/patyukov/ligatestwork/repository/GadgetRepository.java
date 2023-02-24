package ru.patyukov.ligatestwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patyukov.ligatestwork.entity.Gadget;

import java.util.List;

public interface GadgetRepository extends JpaRepository<Gadget, Integer> {
    List<Gadget> findAllByEmployeeId(Integer employeeId);
}
