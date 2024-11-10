package no.skatteetaten.rekruttering.ekstern;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.skatteetaten.rekruttering.ekstern.model.Drivstoff;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoeytype;

public class KjoeretoeyRegister {

    /**
     * Forenklet representasjon av et kjøretøyregister
     */
    private static final Map<String, Kjoeretoey> kjoeretoeyRegister = new HashMap<>() {
        {
            put("PR12345",
                new Kjoeretoey(
                    "PR12345",
                    1300,
                    1700,
                    Kjoeretoeytype.PERSONBIL,
                    Drivstoff.BENSIN,
                    LocalDate.of(2020, 3, 12)
                ));
            put("PD12345",
                new Kjoeretoey(
                    "PD12345",
                    900,
                    1300,
                    Kjoeretoeytype.PERSONBIL,
                    Drivstoff.DIESEL,
                    LocalDate.of(2017, 6, 30)
                ));
            put("PN98765",
                new Kjoeretoey(
                    "PN98765",
                    1200,
                    1700,
                    Kjoeretoeytype.PERSONBIL,
                    Drivstoff.BENSIN,
                    LocalDate.of(1990, 1, 1)
                ));
            put("VR98723",
                    new Kjoeretoey(
                            "VR98723",
                            2000,
                            3000,
                            Kjoeretoeytype.VAREBIL,
                            Drivstoff.BENSIN,
                            LocalDate.of(2015, 4, 20)
                    ));
            put("VR98013",
                    new Kjoeretoey(
                            "VR98013",
                            2500,
                            3500,
                            Kjoeretoeytype.VAREBIL,
                            Drivstoff.BENSIN,
                            LocalDate.of(1990, 1, 1)
                    ));

        }
    };

    /**
     * @return Liste over alle kjøretøy i registeret
     */
    public static List<Kjoeretoey> hentAlleKjoeretoey() {
        return new ArrayList<>(kjoeretoeyRegister.values());
    }

    /**
     * Hent kjøretøy basert på kjennemerke
     *
     * @param kjennemerke Kjennemerke / registreringsnummer på kjøretøyet du ønsker å hente
     * @return Kjøretøy-objekt tilknyttet kjennemerke
     */
    public static Kjoeretoey hentKjoeretoey(String kjennemerke) {
        // TODO håndter ikke funnet
        return kjoeretoeyRegister.get(kjennemerke);
    }

    /**
     * @param kjennemerke Kjennemerke / registreringsnummer på kjøretøyet du ønsker å opprette
     * @param kjoeretoey  Kjøretøy-objekt tilknyttet kjennemerke
     * @return kjoeretoey Kjøretøy-objekt som er blitt opprettet
     */
    public static Kjoeretoey opprettKjoeretoey(String kjennemerke, Kjoeretoey kjoeretoey) {
        return kjoeretoeyRegister.putIfAbsent(kjennemerke, kjoeretoey);
    }

    /**
     * @param kjennemerke Kjennemerke / registreringsnummer på kjøretøyet du ønsker å oppdatere
     * @param kjoeretoey  Kjøretøy-objekt tilknyttet kjennemerke
     * @return kjoeretoey Kjøretøy-objekt som er blitt oppdatert
     */
    public static Kjoeretoey oppdaterKjoeretoey(String kjennemerke, Kjoeretoey kjoeretoey) {
        return kjoeretoeyRegister.put(kjennemerke, kjoeretoey);
    }

    /**
     * @param kjennemerke Kjennemerke / registreringsnummer på kjøretøyet du ønsker å slette
     * @return kjoeretoey Kjøretøy-objekt som er blitt slettet
     */
    public static Kjoeretoey fjernKjoeretoey(String kjennemerke) {
        return kjoeretoeyRegister.remove(kjennemerke);
    }
}
