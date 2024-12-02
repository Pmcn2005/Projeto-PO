package hva.tree;

import java.io.Serializable;
import hva.seasons.CurrentSeason;

public class EvergreenTree extends Tree {

    public EvergreenTree(String treeId, String name, int age, int baseCleaningDifficulty, CurrentSeason currentSeason) {
        super(treeId, name, age, baseCleaningDifficulty, currentSeason);
    }

    public String getBiologicalCycle() {
        return getCurrentSeason().getSeasonState().getEvergreenBiologicalCycle();
    }

    public int getSeasonalEffort() {
        return getCurrentSeason().getSeasonState().getEvergreenSeasonalEffort();
    }

    public double getCleaningEffort() {
        return getBaseCleaningDifficulty()*getSeasonalEffort()*Math.log(getAge()+1);
    }

    @Override
    public String toString() {
        return super.toString() + "|PERENE|" + getBiologicalCycle();
    }
}