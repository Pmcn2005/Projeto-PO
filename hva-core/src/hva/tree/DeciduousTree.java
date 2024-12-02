package hva.tree;

import java.io.Serializable;
import hva.seasons.CurrentSeason;

public class DeciduousTree extends Tree {
    public DeciduousTree(String treeId,String name, int age, int baseCleaningDifficulty, CurrentSeason season) {
        super(treeId, name, age, baseCleaningDifficulty, season);
    }

    public String getBiologicalCycle() {
        return getCurrentSeason().getSeasonState().getDeciduousBiologicalCycle();
    }

    public int getSeasonalEffort() {
        return getCurrentSeason().getSeasonState().getDeciduousSeasonalEffort();
    }

    public double getCleaningEffort() {
        return getBaseCleaningDifficulty()*getSeasonalEffort()*Math.log(getAge()+1);
    }

    @Override
    public String toString() {
        return super.toString() + "|CADUCA|" + getBiologicalCycle();
    }

    

}