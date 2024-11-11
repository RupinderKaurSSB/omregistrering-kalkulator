package no.skatteetaten.rekruttering.ekstern;

import no.skatteetaten.rekruttering.ekstern.model.AvgiftStrategy;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;

public class ElektriskAvgiftStrategy implements AvgiftStrategy {
    private static final int NY_AVGIFT = 1670;
    private static final int ELLOM_2011_2018_AVGIFT = 1009;
    private static final int GAMMEL_AVGIFT = 432;

    @Override
    public int beregnAvgift(Kjoeretoey kjoeretoey, boolean isNew, boolean isMedium) {
        if (isNew) return NY_AVGIFT;
        if (isMedium) return ELLOM_2011_2018_AVGIFT;
        return GAMMEL_AVGIFT;
    }
}
