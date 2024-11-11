package no.skatteetaten.rekruttering.ekstern;

import no.skatteetaten.rekruttering.ekstern.model.AvgiftStrategy;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;

public class VarebilAvgiftStrategy implements AvgiftStrategy {
    private static final int NY_AVGIFT = 2189;
    private static final int MEDIUM_AVGIFT = 1383;
    private static final int GAMMEL_AVGIFT = 1154;

    @Override
    public int beregnAvgift(Kjoeretoey kjoeretoey, boolean isNew, boolean isMedium) {
        if (isNew) return NY_AVGIFT;
        if (isMedium) return MEDIUM_AVGIFT;
        return GAMMEL_AVGIFT;
    }
}
