package com.posfarmacia.adapter.out.external.clock;

import com.posfarmacia.application.port.out.ClockPort;
import java.time.Instant;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class SystemClockAdapter implements ClockPort {

    @Override
    public LocalDate hoy() {
        return LocalDate.now();
    }

    @Override
    public Instant ahora() {
        return Instant.now();
    }
}
