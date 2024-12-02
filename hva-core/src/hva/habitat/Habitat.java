package hva.habitat;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;

import hva.animal.Animal;
import hva.employee.Employee;
import hva.employee.Keeper;
import hva.species.Species;
import hva.tree.Tree;

public class Habitat implements Serializable{
    private String _habitatId;
    private String _name;
    private int _area;

    private Map<String, Animal> _animals;
    private Map<String, Integer> _speciesInfluence; // <speciesId, influence>
    private Map<String, Tree> _trees;
    private Map<String, Keeper> _responsibleKeepers = new TreeMap<String, Keeper>(String.CASE_INSENSITIVE_ORDER);

    public Habitat(String habitatId, String name, int area) {
        _name = name;
        _habitatId = habitatId;
        _area = area;
        _animals = new TreeMap<>();
        _speciesInfluence = new TreeMap<>();
        _trees = new TreeMap<>();
    }

    public void addAnimal(Animal animal) {
        _animals.put(animal.getAnimalId(), animal);
    }

    public void removeAnimal(String animalId) {
        _animals.remove(animalId);
    }

    public void addTree(Tree tree) {
        _trees.put(tree.getTreeId(), tree);
    }

    public void removeTree(String treeId) {
        _trees.remove(treeId);
    }

    public String getHabitatId() {
        return _habitatId;
    }

    public int getHabitatArea() {
        return _area;
    }

    public void setHabitatArea(int area) {
        _area = area;
    }

    public Collection<Keeper> getResponsibleKeepers() {
        return _responsibleKeepers.values();
    }

    public Collection<Tree> getTrees() {
        return _trees.values();
    }

    public Collection<Animal> getAnimals() {
        return _animals.values();
    }

    public int getInfluence(String speciesId) {
        
        if(!_speciesInfluence.containsKey(speciesId)){
            return 0;
        }

        return _speciesInfluence.get(speciesId);
    }

    public void setAnimalInfluence(String speciesid, String influence) {     
        switch (influence) {
            case "POS" -> _speciesInfluence.put(speciesid, 20);
            case "NEG" -> _speciesInfluence.put(speciesid, -20);
            default -> _speciesInfluence.put(speciesid, 0);
            
        }
    }

    public void addKeeper(Employee keeper) {
        _responsibleKeepers.put(keeper.getEmployeeId(), (Keeper) keeper);
    }

    public void removeKeeper(Employee keeper) {
        _responsibleKeepers.remove(keeper.getEmployeeId());
    }
    
    @Override
    public String toString() {
        return "HABITAT|" + _habitatId + "|" + _name + "|" + _area + "|" + _trees.size();
    }
}
