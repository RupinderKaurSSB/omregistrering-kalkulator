package no.skatteetaten.rekruttering.config;

import no.skatteetaten.rekruttering.ekstern.model.Drivstoff;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoeytype;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class KjoeretoeyConfig {
    @Bean
    public Kjoeretoey kjoeretoey() {
        return new Kjoeretoey(
                "AB12345",
                1200,
                3500,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.of(2020, 1, 1)
        );
    }
}

