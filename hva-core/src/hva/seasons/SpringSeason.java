package hva.seasons;

public class SpringSeason extends SeasonState{
 
    @Override
    public int nextSeason(CurrentSeason season) {
        season.setSeasonState(new SummerSeason());
        return 1;
    }

    public String getEvergreenBiologicalCycle() {
        return "GERARFOLHAS";
    }

    public String getDeciduousBiologicalCycle() {
        return "GERARFOLHAS";
    }

    public int getEvergreenSeasonalEffort() {
        return 1;
    }

    public int getDeciduousSeasonalEffort() {
        return 1;
    }
}
