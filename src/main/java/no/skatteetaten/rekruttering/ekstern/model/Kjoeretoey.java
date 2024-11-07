package no.skatteetaten.rekruttering.ekstern.model;

import java.time.LocalDate;

public class Kjoeretoey {
    private String kjennemerke;
    private int egenvekt;
    private int totalvekt;
    private final Kjoeretoeytype kjoeretoeytype;
    private final Drivstoff drivstoff;
    private LocalDate foerstegangsregistreringsdato;

    /**
     * Et kjøretøy med følgende tekniske spesifikasjoner
     *
     * @param kjennemerke                   Kjennemerke / registreringsnummer på bilen
     * @param egenvekt                      Bilens egenvekt (m/fører) på 75 kg
     * @param totalvekt                     Bilens tillatte totalvekt (lastet bil)
     * @param kjoeretoeytype                Kjøretøytype (personbil, varebil)
     * @param drivstoff                     Bensin, diesel eller elektrisitet
     * @param foerstegangsregistreringsdato Dato bilen ble førstegansregistrert
     */
    public Kjoeretoey(
        String kjennemerke,
        int egenvekt,
        int totalvekt,
        Kjoeretoeytype kjoeretoeytype,
        Drivstoff drivstoff,
        LocalDate foerstegangsregistreringsdato
    ) {
        this.kjennemerke = kjennemerke;
        this.egenvekt = egenvekt;
        this.totalvekt = totalvekt;
        this.kjoeretoeytype = kjoeretoeytype;
        this.drivstoff = drivstoff;
        this.foerstegangsregistreringsdato = foerstegangsregistreringsdato;
    }

    public String getKjennemerke() {
        return kjennemerke;
    }

    public void setKjennemerke(String kjennemerke) {
        this.kjennemerke = kjennemerke;
    }

    public int getEgenvekt() {
        return egenvekt;
    }

    public void setEgenvekt(int egenvekt) {
        this.egenvekt = egenvekt;
    }

    public int getTotalvekt() {
        return totalvekt;
    }

    public void setTotalvekt(int totalvekt) {
        this.totalvekt = totalvekt;
    }

    public Kjoeretoeytype getKjoeretoeytype() {
        return kjoeretoeytype;
    }

    public Drivstoff getDrivstoff() {
        return drivstoff;
    }

    public LocalDate getFoerstegangsregistreringsdato() {
        return foerstegangsregistreringsdato;
    }

    public void setFoerstegangsregistreringsdato(LocalDate foerstegangsregistreringsdato) {
        this.foerstegangsregistreringsdato = foerstegangsregistreringsdato;
    }
}
