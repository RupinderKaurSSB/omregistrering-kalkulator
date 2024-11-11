package no.skatteetaten.rekruttering.ekstern;

import no.skatteetaten.rekruttering.ekstern.model.AvgiftStrategy;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;

public class PersonbilAvgiftStrategy implements AvgiftStrategy {
    private static final int VEKTGRENSE = 1200;
    private static final int UNDER_1200_2019_OG_NY_AVGIFT = 4378;
    private static final int UNDER_1200_OG_MELLOM_2011_2018_AVGIFT = 2880;
    private static final int UNDER_1200_OG_2010_ELDRE_AVGIFT = 1729;
    private static final int OVER_1200_2019_OG_NY_AVGIFT = 6681;
    private static final int OVER_1200_OG_MELLOM_2011_2018_AVGIFT = 4034;
    private static final int OVER_1200_OG_2010_ELDRE_AVGIFT = 1729;

    @Override
    public int beregnAvgift(Kjoeretoey kjoeretoey, boolean isNew, boolean isMedium) {
        if (kjoeretoey.getEgenvekt() <= VEKTGRENSE) {
            if (isNew) return UNDER_1200_2019_OG_NY_AVGIFT;
            if (isMedium) return UNDER_1200_OG_MELLOM_2011_2018_AVGIFT;
            return UNDER_1200_OG_2010_ELDRE_AVGIFT;
        } else {
            if (isNew) return OVER_1200_2019_OG_NY_AVGIFT;
            if (isMedium) return OVER_1200_OG_MELLOM_2011_2018_AVGIFT;
            return OVER_1200_OG_2010_ELDRE_AVGIFT;
        }
    }
}
