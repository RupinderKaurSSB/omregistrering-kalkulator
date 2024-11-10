package no.skatteetaten.rekruttering.service;

import no.skatteetaten.rekruttering.ekstern.KjoeretoeyRegister;
import no.skatteetaten.rekruttering.ekstern.model.Drivstoff;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoeytype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Service
public class OmregistreringKalkulator {
    @Autowired
    private final KjoeretoeyRegister kjoeretoeyRegister;

    private final Kjoeretoey kjoeretoey;

    @Autowired
    public OmregistreringKalkulator(KjoeretoeyRegister kjoeretoeyRegister, Kjoeretoey kjoeretoey) {
        this.kjoeretoeyRegister = kjoeretoeyRegister;
        this.kjoeretoey = kjoeretoey;
    }

    public int beregnOmregistreringsavgift(Kjoeretoey kjoeretoey) {
        LocalDate now = LocalDate.now();

        // Check for veteran car exemption (30+ years)
        if (ChronoUnit.YEARS.between(kjoeretoey.getFoerstegangsregistreringsdato(), now) >= 30) {
            return 0;
        }

        int registreringsYear = kjoeretoey.getFoerstegangsregistreringsdato().getYear();
        boolean isNew = registreringsYear >= 2019;
        boolean isMedium = registreringsYear >= 2011 && registreringsYear <= 2018;
        boolean isOld = registreringsYear <= 2010;

        // Electric vehicles have same rates regardless of vehicle type or weight
        if (kjoeretoey.getDrivstoff() == Drivstoff.ELEKTRISITET) {
            if (isNew) return 1670;
            if (isMedium) return 1009;
            return 432;
        }

        // Varebil rates
        if (kjoeretoey.getKjoeretoeytype() == Kjoeretoeytype.VAREBIL) {
            if (isNew) return 2189;
            if (isMedium) return 1383;
            return 1154;
        }

        // Personbil rates
        if (kjoeretoey.getEgenvekt() <= 1200) {
            if (isNew) return 4378;
            if (isMedium) return 2880;
            return 1729;
        } else {
            if (isNew) return 6681;
            if (isMedium) return 4034;
            return 1729;
        }
    }

    public double beregnOmregistreringsAvgift(String kjennemerke) {
        Kjoeretoey kjoeretoey = kjoeretoeyRegister.hentKjoeretoey(kjennemerke);

        if (kjoeretoey == null) {
            throw new IllegalArgumentException("Kjennemerke finnes ikke");
        }

        return beregnOmregistreringsavgift(kjoeretoey);
    }
}
