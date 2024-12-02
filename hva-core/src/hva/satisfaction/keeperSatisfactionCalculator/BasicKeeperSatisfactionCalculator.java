package hva.satisfaction.keeperSatisfactionCalculator;

import hva.employee.Keeper;
import hva.habitat.Habitat;
import hva.tree.Tree;
import java.io.Serializable;
import java.util.Collection;

public class BasicKeeperSatisfactionCalculator implements keeperSatisfactionStrategy, Serializable {

    public double habitatWork(Habitat habitat) {
        int area = habitat.getHabitatArea();
        int population = habitat.getAnimals().size();
        Collection<Tree> trees = habitat.getTrees();
        double cleaningEffort = 0.0;
        double total = 0.0;
        
        if (trees != null) { 
            for (Tree tree : trees) {
                cleaningEffort += tree.getCleaningEffort();
            }
        }
        
        total = area + (3 * population) + cleaningEffort;
        
        return total;    
    }

    public double work(Keeper keeper) {
        
        double total = 0.0;
        Collection<Habitat> habitats = keeper.getHabitats();
        
        if (habitats != null) {
            for (Habitat habitat : habitats) {
                int keeperCount = habitat.getResponsibleKeepers().size();
                
                if(keeperCount>0) {
                    total += habitatWork(habitat) / keeperCount;
                }
            }
        }
        return total;
    }

    @Override
    public double calculateSatisfaction(Keeper keeper) {
        return 300 - work(keeper);
    }  

    
    
}
