package hva.seasons;

public class AutumnSeason extends SeasonState{
    
    @Override
    public int nextSeason(CurrentSeason season) {
        season.setSeasonState(new WinterSeason());
        return 3;
    }

    public String getEvergreenBiologicalCycle() {
        return "COMFOLHAS";
    }

    public String getDeciduousBiologicalCycle() {
        return "LARGARFOLHAS";
    }

    public int getEvergreenSeasonalEffort() {
        return 1;
    } 

    public int getDeciduousSeasonalEffort() {
        return 5;
    }
}
