package hva.seasons;

public class SummerSeason extends SeasonState  {

    @Override
    public int nextSeason(CurrentSeason season) {
        season.setSeasonState(new AutumnSeason());
        return 2;
    }

    public String getEvergreenBiologicalCycle() {
        return "COMFOLHAS";
    }

    public String getDeciduousBiologicalCycle() {
        return "COMFOLHAS";
    }

    public int getEvergreenSeasonalEffort() {
        return 1;
    }

    public int getDeciduousSeasonalEffort() {
        return 2;
    }
}
