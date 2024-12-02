package hva.seasons;

import java.io.Serializable;

public abstract class SeasonState implements Serializable {
    public abstract int nextSeason(CurrentSeason season);
    public abstract String getEvergreenBiologicalCycle();
    public abstract String getDeciduousBiologicalCycle();
    public abstract int getEvergreenSeasonalEffort();
    public abstract int getDeciduousSeasonalEffort();
}
