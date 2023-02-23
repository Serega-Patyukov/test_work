package ru.patyukov.ligatestwork.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.patyukov.ligatestwork.repository.GadgetRepository;

@Slf4j
@Service
@AllArgsConstructor
public class GadgetServiceImpl implements GadgetService {
    private final GadgetRepository gadgetRepository;
}
