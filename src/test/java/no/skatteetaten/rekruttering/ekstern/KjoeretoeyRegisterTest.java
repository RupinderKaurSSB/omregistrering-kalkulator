package no.skatteetaten.rekruttering.ekstern;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import no.skatteetaten.rekruttering.ekstern.model.Drivstoff;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoeytype;

import static org.junit.jupiter.api.Assertions.*;

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
    void hentAlleKjoeretoey_shouldReturnAllVehicles() {
        var kjoeretoeyList = KjoeretoeyRegister.hentAlleKjoeretoey();

        assertNotNull(kjoeretoeyList);
        assertEquals(6, kjoeretoeyList.size());

        assertTrue(kjoeretoeyList.stream()
                .anyMatch(k -> k.getKjennemerke().equals("PR12345")));
        assertTrue(kjoeretoeyList.stream()
                .anyMatch(k -> k.getKjennemerke().equals("PD12345")));
        assertTrue(kjoeretoeyList.stream()
                .anyMatch(k -> k.getKjennemerke().equals("PN98765")));
        assertTrue(kjoeretoeyList.stream()
                .anyMatch(k -> k.getKjennemerke().equals("VR98723")));
        assertTrue(kjoeretoeyList.stream()
                .anyMatch(k -> k.getKjennemerke().equals("VR98013")));
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
