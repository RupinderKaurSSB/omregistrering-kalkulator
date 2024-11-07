package no.skatteetaten.rekruttering.ekstern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import no.skatteetaten.rekruttering.ekstern.model.Drivstoff;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoeytype;

public class KjoeretoeyRegisterTest {
    @Test
    void skalReturnereRiktigKjoeretoeyGittKjennemerke() {
        Kjoeretoey kjoeretoey = KjoeretoeyRegister.hentKjoeretoey("PR12345");

        assertEquals(kjoeretoey.getKjennemerke(), "PR12345");
        assertEquals(kjoeretoey.getEgenvekt(), 1300);
        assertEquals(kjoeretoey.getTotalvekt(), 1700);
        assertEquals(kjoeretoey.getKjoeretoeytype(), Kjoeretoeytype.PERSONBIL);
        assertEquals(kjoeretoey.getDrivstoff(), Drivstoff.BENSIN);
        assertEquals(kjoeretoey.getFoerstegangsregistreringsdato(), LocalDate.of(2020, 3, 12));
    }

    @Test
    void skalOppretteKjoeretoey() {
        String kjennemerke = "AA12345";
        Kjoeretoey kjoeretoeyFoerOpprettelse = KjoeretoeyRegister.hentKjoeretoey(kjennemerke);

        assertNull(kjoeretoeyFoerOpprettelse);

        KjoeretoeyRegister.opprettKjoeretoey(
            kjennemerke, new Kjoeretoey(
                kjennemerke,
                1000,
                1450,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.of(2014, 8, 29)
            )
        );

        Kjoeretoey kjoeretoeyEtterOpprettelse = KjoeretoeyRegister.hentKjoeretoey(kjennemerke);

        assertNotNull(kjoeretoeyEtterOpprettelse);
    }
}
