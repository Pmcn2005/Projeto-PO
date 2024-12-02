package hva.species;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import hva.animal.Animal;
import hva.employee.Employee;
import hva.employee.Veterinarian;

public class Species implements Serializable {
    private String _speciesId;
    private String _name;
    private Map<String, Animal> _animals;
    private Map<String, Veterinarian> _responsableVeterinarians = new TreeMap<String, Veterinarian>(String.CASE_INSENSITIVE_ORDER);

    public Species(String speciesId, String name) {
        _speciesId = speciesId;
        _name = name;
        _animals = new TreeMap<>();
    }

    public void addAnimal(Animal animal) {
        _animals.put(animal.getAnimalId(), animal);
    }

    public String getSpeciesId() {
        return _speciesId;
    }

    public String getSpeciesName() {
        return _name;
    }

    public Collection<Animal> getAnimals() {
        return _animals.values();
    }

    public Collection<Veterinarian> getResponsableVeterinarians() {
        return _responsableVeterinarians.values();
    }

    public void addVeterinarian(Employee veterinarian) {
        _responsableVeterinarians.put(veterinarian.getEmployeeId(), (Veterinarian) veterinarian);
    }

    public void removeVeterinarian(Employee veterinarian) {
        _responsableVeterinarians.remove(veterinarian.getEmployeeId());
    }
    
}
