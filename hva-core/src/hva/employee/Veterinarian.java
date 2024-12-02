package hva.employee;

import java.io.Serializable;
import java.util.StringJoiner;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import hva.exceptions.NotResponsableException;
import hva.satisfaction.veterinarianSatisfactionCalculator.veterinarianSatisfactionStrategy;
import hva.satisfaction.veterinarianSatisfactionCalculator.BasicVeterinarianSatisfactionCalculator;
import hva.species.Species;
import hva.tree.Tree;
import hva.vaccine.VaccinationRecord;

public class Veterinarian extends Employee {

    private Map<String, Species> _species = new TreeMap<String, Species>(String.CASE_INSENSITIVE_ORDER);
    private List<VaccinationRecord> _vaccinationRecords;
    private veterinarianSatisfactionStrategy _calculator = new BasicVeterinarianSatisfactionCalculator();

    public Veterinarian(String employeeId, String name, Map<String, Species> species) {
        super(employeeId, name);
        _species = species;
        _vaccinationRecords = new ArrayList<VaccinationRecord>();
    }

    public Collection<Species> getSpecies() {
        return _species.values();
    }

    public void addSpecies(Species species) {
        _species.put(species.getSpeciesId(), species);
    }

    public void removeSpecies(Species species) throws NotResponsableException {
       if (!_species.containsKey(species.getSpeciesId()))
            throw new NotResponsableException();
        _species.remove(species.getSpeciesId());
    }

    public double calculateSatisfaction() {
        return _calculator.calculateSatisfaction(this);
    }

    public boolean canVaccinate(Species species) {
        return _species.containsKey(species.getSpeciesId());
    }

    public void addVaccinationRecord(VaccinationRecord record) {
        _vaccinationRecords.add(record);
    }
    
    public Collection<VaccinationRecord> getVaccinationRecords() {
        return _vaccinationRecords;
    }


    public String presentSpecies() {
        if (_species.isEmpty()) {
            return "";
        }

        StringJoiner result = new StringJoiner(",");
        for (Species species : _species.values()) {
            result.add(species.getSpeciesId());
        }
        return "|" + result.toString();
    }


    @Override
    public String toString() {
        return "VET|" + this.getEmployeeId() + "|" + this.getEmployeeName() + presentSpecies();
    }
}