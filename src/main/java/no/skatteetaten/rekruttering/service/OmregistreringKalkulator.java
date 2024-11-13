/**
 * Calculates the registration fee for a vehicle based on its type, fuel type, and first registration date.
 * The calculation is performed using different strategies for different vehicle types.
 * Vehicles older than 30 years are exempt from the registration fee.
 */
package no.skatteetaten.rekruttering.service;

import no.skatteetaten.rekruttering.ekstern.ElektriskAvgiftStrategy;
import no.skatteetaten.rekruttering.ekstern.KjoeretoeyRegister;
import no.skatteetaten.rekruttering.ekstern.PersonbilAvgiftStrategy;
import no.skatteetaten.rekruttering.ekstern.VarebilAvgiftStrategy;
import no.skatteetaten.rekruttering.ekstern.model.AvgiftStrategy;
import no.skatteetaten.rekruttering.ekstern.model.Drivstoff;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoeytype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Map;


public class OmregistreringKalkulator {
    private final Map<Kjoeretoeytype, AvgiftStrategy> avgiftStrategies;
    private final AvgiftStrategy elektriskStrategy;

    public OmregistreringKalkulator() {
        avgiftStrategies = new EnumMap<>(Kjoeretoeytype.class);
        avgiftStrategies.put(Kjoeretoeytype.VAREBIL, new VarebilAvgiftStrategy());
        avgiftStrategies.put(Kjoeretoeytype.PERSONBIL, new PersonbilAvgiftStrategy());
        elektriskStrategy = new ElektriskAvgiftStrategy();
    }

    /**
     * Beregner omregistreringavgiften for et kjøretøy basert på type, drivstofftype og førsteregistreringsdato.
     * Beregningen utføres ved hjelp av ulike strategier for ulike kjøretøytyper.
     * Kjøretøy eldre enn 30 år er fritatt for registreringsavgift.
     *
     * @param kjoeretoey kjøretøyet det skal beregnes registreringsavgift for
     * @return den beregnede registreringsavgiften
     */
    public int beregnOmregistreringsavgift(Kjoeretoey kjoeretoey) {
        java.time.LocalDate now = java.time.LocalDate.now();
        if (ChronoUnit.YEARS.between(kjoeretoey.getFoerstegangsregistreringsdato(), now) >= 30) {
            return 0;
        }
        int registreringsYear = kjoeretoey.getFoerstegangsregistreringsdato().getYear();

        boolean isNew = registreringsYear >= 2019;
        boolean isMedium = registreringsYear >= 2011 && registreringsYear <= 2018;

        if (kjoeretoey.getDrivstoff() == Drivstoff.ELEKTRISITET) {
            return elektriskStrategy.beregnAvgift(kjoeretoey, isNew, isMedium);
        }

        AvgiftStrategy strategy = avgiftStrategies.get(kjoeretoey.getKjoeretoeytype());
        return strategy.beregnAvgift(kjoeretoey, isNew, isMedium);
    }
}

