package no.skatteetaten.rekruttering.service;

import no.skatteetaten.rekruttering.ekstern.KjoeretoeyRegister;
import no.skatteetaten.rekruttering.ekstern.model.Drivstoff;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoeytype;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OmregistreringKalkulatorTest {

    private OmregistreringKalkulator kalkulator = new OmregistreringKalkulator();
    private KjoeretoeyRegister kjoeretoeyRegister;

    private Kjoeretoey kjoeretoey;

    @BeforeEach
    void setUp() {

    }

    @Test
    void veteranBilShouldHaveZeroFee() {
        Kjoeretoey veteranBil = new Kjoeretoey(
                "VT1234",
                1000,
                1500,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.now().minusYears(31)
        );

        assertEquals(0, kalkulator.beregnOmregistreringsavgift(veteranBil));
    }

    @Test
    void newElectricCarShouldHaveCorrectFee() {
        Kjoeretoey elbil = new Kjoeretoey(
                "EL1234",
                1000,
                1500,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.ELEKTRISITET,
                LocalDate.of(2020, 1, 1)
        );

        assertEquals(1670, kalkulator.beregnOmregistreringsavgift(elbil));
    }

    @Test
    void oldElectricCarShouldHaveCorrectFee() {
        Kjoeretoey elbil = new Kjoeretoey(
                "EL1234",
                1000,
                1500,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.ELEKTRISITET,
                LocalDate.of(2010, 1, 1)
        );

        assertEquals(432, kalkulator.beregnOmregistreringsavgift(elbil));
    }

    @Test
    void mediumAgeElectricCarShouldHaveCorrectFee() {
        Kjoeretoey elbil = new Kjoeretoey(
                "EL1234",
                1000,
                1500,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.ELEKTRISITET,
                LocalDate.of(2015, 1, 1)
        );

        assertEquals(1009, kalkulator.beregnOmregistreringsavgift(elbil));
    }

    @Test
    void newLightPersonbilShouldHaveCorrectFee() {
        Kjoeretoey personbil = new Kjoeretoey(
                "PB1234",
                1100,
                1500,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.of(2020, 1, 1)
        );

        assertEquals(4378, kalkulator.beregnOmregistreringsavgift(personbil));
    }

    @Test
    void newHeavyPersonbilShouldHaveCorrectFee() {
        Kjoeretoey personbil = new Kjoeretoey(
                "PB1234",
                1300,
                1800,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.of(2020, 1, 1)
        );

        assertEquals(6681, kalkulator.beregnOmregistreringsavgift(personbil));
    }

    @Test
    void averageHeavyPersonbilShouldHaveCorrectFee() {
        Kjoeretoey personbil = new Kjoeretoey(
                "PB1234",
                1300,
                1800,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.of(2015, 1, 1)
        );

        assertEquals(4034, kalkulator.beregnOmregistreringsavgift(personbil));
    }

    @Test
    void averageLightPersonbilShouldHaveCorrectFee() {
        Kjoeretoey personbil = new Kjoeretoey(
                "PB1234",
                1100,
                1800,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.of(2015, 1, 1)
        );

        assertEquals(2880, kalkulator.beregnOmregistreringsavgift(personbil));
    }

    @Test
    void oldHeavyPersonbilShouldHaveCorrectFee() {
        Kjoeretoey personbil = new Kjoeretoey(
                "PB1234",
                1300,
                1800,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.of(2009, 1, 1)
        );

        assertEquals(1729, kalkulator.beregnOmregistreringsavgift(personbil));
    }

    @Test
    void oldLightPersonbilShouldHaveCorrectFee() {
        Kjoeretoey personbil = new Kjoeretoey(
                "PB1234",
                1100,
                1800,
                Kjoeretoeytype.PERSONBIL,
                Drivstoff.BENSIN,
                LocalDate.of(2009, 1, 1)
        );

        assertEquals(1729, kalkulator.beregnOmregistreringsavgift(personbil));
    }

    @Test
    void newVarebilShouldHaveCorrectFee() {
        Kjoeretoey varebil = new Kjoeretoey(
                "VB1234",
                1500,
                2000,
                Kjoeretoeytype.VAREBIL,
                Drivstoff.DIESEL,
                LocalDate.of(2020, 1, 1)
        );

        assertEquals(2189, kalkulator.beregnOmregistreringsavgift(varebil));
    }

    @Test
    void averageVarebilShouldHaveCorrectFee() {
        Kjoeretoey varebil = new Kjoeretoey(
                "VB1234",
                1500,
                2000,
                Kjoeretoeytype.VAREBIL,
                Drivstoff.DIESEL,
                LocalDate.of(2015, 1, 1)
        );

        assertEquals(1383, kalkulator.beregnOmregistreringsavgift(varebil));
    }

    @Test
    void oldVarebilShouldHaveCorrectFee() {
        Kjoeretoey varebil = new Kjoeretoey(
                "VB1234",
                1500,
                2000,
                Kjoeretoeytype.VAREBIL,
                Drivstoff.DIESEL,
                LocalDate.of(2010, 1, 1)
        );

        assertEquals(1154, kalkulator.beregnOmregistreringsavgift(varebil));
    }

    @Test
    void kjoeretoey_PR12345_shouldHaveCorrectFee() {
        assertEquals(6681, kalkulator.beregnOmregistreringsavgift(KjoeretoeyRegister.hentKjoeretoey("PR12345")));
    }
}
