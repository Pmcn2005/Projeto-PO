package hva.animal;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.Collection;

import hva.habitat.Habitat;
import hva.satisfaction.animalSatisfactionCalculator.AnimalSatisfactionStrategy;
import hva.satisfaction.animalSatisfactionCalculator.BasicAnimalSatisfactionCalculator;
import hva.species.Species;
import hva.vaccine.VaccinationRecord;


public class Animal implements Serializable {
    private String _name;
    private String _animalId;
    private Species _species;
    private Habitat _habitat;
    private List<VaccinationRecord> _vaccinationRecords;
    AnimalSatisfactionStrategy calculator = new BasicAnimalSatisfactionCalculator();

    public Animal(String animalId, String name, Species species, Habitat habitat) {
        _name = name;
        _animalId = animalId;
        _species = species;
        _habitat = habitat;
        _vaccinationRecords = new ArrayList<VaccinationRecord>();
    }

    public String getAnimalName() {
        return _name;
    }

    public String getAnimalId() {
        return _animalId;
    }

    public Species getSpecies() {
        return _species;
    }

    public Habitat getHabitat() {
        return _habitat;
    }

    public Collection<VaccinationRecord> getVaccinationRecords() {
        return _vaccinationRecords;
    }

    public void setHabitat(Habitat habitat) {
        _habitat = habitat;
    }

    public double calculateAnimalSatisfaction() {
        return calculator.calculateSatisfaction(this);
    }

    public void addVaccinationRecord(VaccinationRecord record) {
        _vaccinationRecords.add(record);
    }

    public String getHealthStatus() {
        StringJoiner sj = new StringJoiner(",");

        for(VaccinationRecord record : _vaccinationRecords) {
            sj.add(record.getTermHealthStatus());
        }
        if(sj.length() == 0) { return "VOID"; }

        else { return sj.toString(); }
    }


    @Override
    public String toString() {
        return "ANIMAL|" + _animalId + "|" + _name + "|" + _species.getSpeciesId() + "|" + getHealthStatus() + "|" + _habitat.getHabitatId();
    }


}
