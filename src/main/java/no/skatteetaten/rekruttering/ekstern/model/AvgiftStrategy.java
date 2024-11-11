package no.skatteetaten.rekruttering.ekstern.model;

import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;

public interface AvgiftStrategy {
    int beregnAvgift(Kjoeretoey kjoeretoey, boolean isNew, boolean isMedium);
}