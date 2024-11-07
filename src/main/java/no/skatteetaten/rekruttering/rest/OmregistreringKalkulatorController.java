package no.skatteetaten.rekruttering.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/omregistrering")
public class OmregistreringKalkulatorController {
    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

    // TODO
}
