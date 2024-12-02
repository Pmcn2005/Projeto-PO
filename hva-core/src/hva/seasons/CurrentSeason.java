package hva.seasons;

import java.io.Serializable;

public class CurrentSeason implements Serializable {
    private SeasonState state;

    public CurrentSeason() {
        state = new SpringSeason();
    }

    public int nextSeason() {
        return state.nextSeason(CurrentSeason.this);
    }

    public SeasonState getSeasonState() {
        return state;
    }

    public void setSeasonState(SeasonState state) {
        this.state = state;
    }



}
