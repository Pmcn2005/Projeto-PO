package hva.seasons;

public class WinterSeason extends SeasonState{

    @Override
    public int nextSeason(CurrentSeason season) {
        season.setSeasonState(new SpringSeason());
        return 0;
    }
    
    public String getEvergreenBiologicalCycle() {
        return "LARGARFOLHAS";
    }

    public String getDeciduousBiologicalCycle() {
        return "SEMFOLHAS";
    }  

    public int getEvergreenSeasonalEffort() {
        return 2;
    }

    public int getDeciduousSeasonalEffort() {
        return 0;
    } 
}
