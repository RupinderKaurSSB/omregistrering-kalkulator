/**
 * Controller for handling requests related to vehicle registration fee calculation.
 * Provides an endpoint to calculate the registration fee for a given vehicle registration number.
 */
package no.skatteetaten.rekruttering.rest;

import no.skatteetaten.rekruttering.ekstern.KjoeretoeyRegister;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;
import no.skatteetaten.rekruttering.service.OmregistreringKalkulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/omregistrering")
public class OmregistreringKalkulatorController {
    @Autowired
    private KjoeretoeyRegister kjoeretoeyRegister;

    private Kjoeretoey kjoeretoey;

    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

    /**
     * Beregner registreringsavgiften for et kjøretøy med oppgitt registreringsnummer og returnerer resultatet som et JSON-svar
     *
     * @param kjennemerke kjøretøyets registreringsnummer
     * @return en map som inneholder registreringsnummer og beregnet registreringsavgift
     */
    @GetMapping("/{kjennemerke}/beregnAvgift")
    public ResponseEntity<Map<String, Object>> beregnOmregistreringsAvgift(@PathVariable String kjennemerke) {

        Kjoeretoey kjoeretoey = KjoeretoeyRegister.hentKjoeretoey(kjennemerke);
        KjoeretoeyRegister kjoeretoeyRegister = new KjoeretoeyRegister();

        OmregistreringKalkulator omregistreringKalkulator = new OmregistreringKalkulator();

        double avgift = omregistreringKalkulator.beregnOmregistreringsavgift(kjoeretoey);

        Map<String, Object> response = new HashMap<>();
        response.put("kjennemerke", kjennemerke);
        response.put("avgift", avgift);

        return ResponseEntity.ok(response);
    }

}
