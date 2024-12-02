package hva.vaccine;

import java.io.Serializable;

import hva.vaccine.Vaccine;
import hva.animal.Animal;
import hva.employee.Veterinarian;


public class VaccinationRecord implements Serializable {

    private Vaccine _vaccine;
    private Animal _animal;
    private Veterinarian _veterinarian;
    private int _damage;
    private String _termHealthStatus;

    public VaccinationRecord(Vaccine vaccine, Animal animal, Veterinarian veterinarian, int damage) {
        _vaccine = vaccine;
        _animal = animal;
        _veterinarian = veterinarian;
        _damage = damage;


        if (damage == 0 && vaccine.getCompatibleSpecies().contains(animal.getSpecies().getSpeciesId())) {
            _termHealthStatus = "NORMAL";
        } else  if (damage == 0 && !(vaccine.getCompatibleSpecies().contains(animal.getSpecies().getSpeciesId()))) {
            _termHealthStatus = "CONFUSÃO";
        } else if (1 <= damage && damage <= 4) {
            _termHealthStatus = "ACIDENTE";
        } else {
            _termHealthStatus = "ERRO";
        }
        
    }

    public void setTermHealthStatus(String termHealthStatus) {
        _termHealthStatus = termHealthStatus;
    }

    public String getTermHealthStatus() {
        return _termHealthStatus;
    }

    @Override
    public String toString() {
        return "REGISTO-VACINA|" + _vaccine.getVaccineId() + "|" + _veterinarian.getEmployeeId() + "|" + _animal.getSpecies().getSpeciesId();
    }


}
