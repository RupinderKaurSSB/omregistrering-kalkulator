/**
 * Implementerer AvgiftStrategy interface  for å beregne avgiftsbeløpet for et elektrisk kjøretøy basert på dets alder og størrelse.
 *  * Avgiftsbeløpet bestemmes av kjøretøyets klassifisering som nytt, middels eller gammelt.
 */
package no.skatteetaten.rekruttering.ekstern;

import no.skatteetaten.rekruttering.ekstern.model.AvgiftStrategy;
import no.skatteetaten.rekruttering.ekstern.model.Kjoeretoey;

public class ElektriskAvgiftStrategy implements AvgiftStrategy {
    private static final int NY_AVGIFT = 1670;
    private static final int ELLOM_2011_2018_AVGIFT = 1009;
    private static final int GAMMEL_AVGIFT = 432;

    @Override
    /**
     * Beregner avgiftsbeløpet for et elektrisk kjøretøy basert på dets alder og størrelse
     *
     * @param kjoeretoey el bil objekt
     * @param isNew     true hvis kjøretøyet er nytt, ellers false
     * @param isMedium  true hvis kjøretøyet er middels gammelt, ellers false
     * @return det beregnede avgiftsbeløpet for elbilen
     */
    public int beregnAvgift(Kjoeretoey kjoeretoey, boolean isNew, boolean isMedium) {
        if (isNew) return NY_AVGIFT;
        if (isMedium) return ELLOM_2011_2018_AVGIFT;
        return GAMMEL_AVGIFT;
    }
}
