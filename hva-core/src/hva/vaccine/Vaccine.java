package hva.vaccine;

import java.io.Serializable;

import hva.animal.Animal;
import hva.employee.Veterinarian;
import hva.species.Species;
import java.util.StringJoiner;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Vaccine implements Serializable {
    private String _vaccineId;
    private String _name;
    private int _timesAdministered = 0;
    private Map<String,Species> _compatibleSpecies = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public Vaccine(String vaccineId, String name, Map<String,Species> species) {
        _vaccineId = vaccineId;
        _name = name;
        _compatibleSpecies = species;
    }

    public String getVaccineId() {
        return _vaccineId;
    }

    public Collection<String> getCompatibleSpecies() {
        return _compatibleSpecies.keySet();
    }

    public String presentCompatibleSpecies() {
        StringJoiner result = new StringJoiner(",");

        if (_compatibleSpecies.size() == 0) {
            return "";
        }

        for (Species species : _compatibleSpecies.values()) {
            result.add(species.getSpeciesId());
        }
        return "|" + result.toString();
    }

    public int maxNameSize(String species1, String species2) {
        return Math.max(species1.length(), species2.length());
    }

    public int commonCharacters(String species1, String species2) {
        
        Set<Character> setA = new HashSet<>();
        for (char c : species1.toCharArray()) {
            setA.add(c);
        }
        
        int commonCharactersCounter = 0;
            
        for (char c : species2.toCharArray()) {
            if (setA.contains(c)) {
                commonCharactersCounter++;
                setA.remove(c); 
            }
        }
        
        return commonCharactersCounter;
    }
    
    public int damage(Animal animal) {
        int maior = 0;
        int temp = 0;

        if (_compatibleSpecies.containsKey(animal.getSpecies().getSpeciesId())) {
            return 0;
        }

        for (Species species : _compatibleSpecies.values()) {
            temp = maxNameSize(animal.getSpecies().getSpeciesName(), species.getSpeciesName());
            temp -= commonCharacters(animal.getSpecies().getSpeciesName(), species.getSpeciesName());

            if (temp > maior) {
                maior = temp;
            }
        }
        return maior;    
    }
    
    public int giveVaccine(Veterinarian veterinarian, Animal animal) {
        int damage = damage(animal);
        _timesAdministered++;
        return damage;
    }

    @Override
    public String toString() {
        return "VACINA|" + _vaccineId + "|" + _name + "|" + _timesAdministered + presentCompatibleSpecies();
    }
}
