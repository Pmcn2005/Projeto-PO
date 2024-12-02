package hva.satisfaction.animalSatisfactionCalculator;

import hva.animal.Animal;
import hva.habitat.Habitat;
import hva.species.Species;
import java.io.Serializable;

public class BasicAnimalSatisfactionCalculator implements AnimalSatisfactionStrategy, Serializable {
    
    public int identical(Animal animal1) {
        Habitat habitat = animal1.getHabitat();
        int count = 0;
        String speciesId1 = animal1.getSpecies().getSpeciesId();
        
        for (Animal animal2 : habitat.getAnimals()) {
            
            String speciesId2 = animal2.getSpecies().getSpeciesId();
            if(speciesId1.equals(speciesId2)) {
                count++;    
            }
        }
        return count - 1;
    }

    public int different(Animal animal1) {
       int count = animal1.getHabitat().getAnimals().size() - identical(animal1) - 1;
        return count;
    }

    @Override
    public double calculateSatisfaction(Animal animal) {
        double satisfaction = 0;
        int iguais = identical(animal);
        int diferentes = different(animal);
        double habitatArea = animal.getHabitat().getHabitatArea();
        double population = animal.getHabitat().getAnimals().size();
        int influence = animal.getHabitat().getInfluence(animal.getSpecies().getSpeciesId());

        satisfaction = 20 + 3*iguais - 2*diferentes + (habitatArea/population) + influence;
        
        return satisfaction;
    }
}
