package ru.patyukov.ligatestwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.patyukov.ligatestwork.entity.Gadget;

public interface GadgetRepository extends JpaRepository<Gadget, Integer> {
}
