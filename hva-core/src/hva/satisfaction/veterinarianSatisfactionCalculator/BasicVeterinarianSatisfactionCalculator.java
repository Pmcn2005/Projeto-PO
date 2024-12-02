package hva.satisfaction.veterinarianSatisfactionCalculator;

import hva.employee.Veterinarian;
import java.util.Collection;
import hva.species.Species;
import java.io.Serializable;


public class BasicVeterinarianSatisfactionCalculator implements veterinarianSatisfactionStrategy, Serializable {
    
    public double work(Veterinarian veterinarian){
        double work = 0.0;
        Collection<Species> species = veterinarian.getSpecies();

        if (species != null) {
            for(Species s : species){
                int animals = s.getAnimals().size();
                int veterinarians = s.getResponsableVeterinarians().size();
                if (animals > 0 && veterinarians > 0) {
                    work += animals/veterinarians;
                }
            }
        }
        return work;
    }
    
    public double calculateSatisfaction(Veterinarian veterinarian) {
        double satisfaction = 20.0;
        
        satisfaction -= work(veterinarian);
        return satisfaction;
    }
}
