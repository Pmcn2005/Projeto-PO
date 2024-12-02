package hva;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import hva.exceptions.ImportFileException;
import hva.exceptions.NotResponsableException;
import hva.exceptions.UnrecognizedEntryException;
import hva.exceptions.DuplicateHabitatException;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateSpeciesException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.EmployeeNotVeterinarian;
import hva.exceptions.DuplicateTreeException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.UnknownTreeException;
import hva.exceptions.NotResponsableException;
import hva.exceptions.CannotVaccinate;
import hva.exceptions.VaccineNotSuitableException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

import hva.animal.Animal;
import hva.tree.Tree;
import hva.tree.EvergreenTree;
import hva.tree.DeciduousTree;
import hva.employee.Employee;
import hva.employee.Veterinarian;
import hva.employee.Keeper;
import hva.habitat.Habitat;
import hva.species.Species;
import hva.vaccine.VaccinationRecord;
import hva.vaccine.Vaccine;
import hva.seasons.CurrentSeason;


public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    private boolean _changed = false;


    private Map<String, Habitat> _habitats = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    
   
    private Map<String, Species> _species = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    
    private Map<String, Animal> _animals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    
    private Map<String, Employee> _employees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    
    private Map<String, Tree> _trees = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    
    private Map<String, Vaccine> _vaccines = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    
    
    private ArrayList<VaccinationRecord> _vaccinationRecords = new ArrayList<>();


    private CurrentSeason _currentSeason = new CurrentSeason();

    public CurrentSeason getCurrentSeason() {
        return _currentSeason;
    }

    public Habitat getHabitat(String habitatId) throws UnknownHabitatException {
        Habitat habitat = _habitats.get(habitatId);

        if (habitat == null) {
            throw new UnknownHabitatException();
        }
        return habitat;
    }

    public Species getSpecies(String speciesId) throws UnknownSpeciesException {
        Species species = _species.get(speciesId);

        if (species == null) {
            throw new UnknownSpeciesException(speciesId);
        }
        return species;
    }

    public Animal getAnimal(String animalId) throws UnknownAnimalException {
        Animal animal = _animals.get(animalId);

        if (animal == null) {
            throw new UnknownAnimalException();
        }
        return animal;
    }

    public Employee getEmployee(String employeeId) throws UnknownEmployeeException {
        Employee employee = _employees.get(employeeId);

        if (employee == null) {
            throw new UnknownEmployeeException();
        }
        return employee;
    }

    public Vaccine getVaccine(String vaccineId) throws UnknownVaccineException {
        Vaccine vaccine = _vaccines.get(vaccineId);

        if (vaccine == null) {
            throw new UnknownVaccineException();
        }
        return vaccine;
    }

    public Tree getTree(String treeId) throws UnknownTreeException {
        Tree tree = _trees.get(treeId);

        if (tree == null) {
            throw new UnknownTreeException();
        }
        return tree;
    }


    public void importFile(String filename) throws ImportFileException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                registerEntry(fields);
            } 
        }
        catch (IOException | UnrecognizedEntryException e) {
            throw new ImportFileException(filename, e);
        }
    }   

    
    public void registerEntry(String... fields) throws UnrecognizedEntryException {

        try {
            switch (fields[0]) {
                case "HABITAT" -> registerHabitat(fields);
                case "ESPÉCIE" -> registerSpecies(fields);
                case "ANIMAL" -> registerAnimal(fields);
                case "VETERINÁRIO", "TRATADOR" -> registerEmployee(fields);
                case "VACINA" -> registerVaccine(fields);
                case "ÁRVORE" -> registerTree(fields);

                default -> throw new UnrecognizedEntryException(fields[0]);
            }
        }
        catch (DuplicateHabitatException | DuplicateTreeException | DuplicateAnimalException | 
               DuplicateEmployeeException | UnknownHabitatException | UnknownSpeciesException |
               DuplicateVaccineException | DuplicateSpeciesException e) {
            // will never happen
            e.printStackTrace();
        }

    }

    public void registerHabitat(String... fields) throws DuplicateHabitatException {
        
        if(_habitats.containsKey(fields[1])) {
            throw new DuplicateHabitatException();
        }
        
        Habitat habitat = new Habitat(fields[1], fields[2], Integer.parseInt(fields[3]));
            
        if(fields.length > 4) {
            String[] treeIds = fields[4].split(",");

            for (String treeId : treeIds) {
                Tree tree = _trees.get(treeId);
                habitat.addTree(tree);
            }
        }

        _habitats.put(habitat.getHabitatId(), habitat);
        
        changed();
    }
    
   
    public void registerSpecies(String... fields) throws DuplicateSpeciesException {
        
        if(_species.containsKey(fields[1])) {
            throw new DuplicateSpeciesException();
        }

        for (Species species : _species.values()) {
            if (species.getSpeciesName().equalsIgnoreCase(fields[2])) {
                throw new DuplicateSpeciesException();
            }
        }
        
        Species species = new Species(fields[1], fields[2]);
        _species.put(species.getSpeciesId(), species);
        
        changed();
    }

    
    public void registerAnimal(String... fields) throws DuplicateAnimalException, UnknownHabitatException, UnknownSpeciesException {
        
        if(_animals.containsKey(fields[1])) {
            throw new DuplicateAnimalException();
        }

        Species species = getSpecies(fields[3]);
        Habitat habitat = getHabitat(fields[4]);
    
        Animal animal = new Animal(fields[1], fields[2], _species.get(fields[3]), _habitats.get(fields[4]));
        
        _animals.put(animal.getAnimalId(), animal);

        habitat.addAnimal(animal);
        species.addAnimal(animal);

        changed();
    }

   
    public void registerEmployee(String... fields) throws UnrecognizedEntryException, DuplicateEmployeeException {
        
        if(_employees.containsKey(fields[1])) {
            throw new DuplicateEmployeeException();
        }
        
        Employee employee;

        switch (fields[0]) {
            case "VETERINÁRIO","VET" -> { 
                Map<String, Species> speciesTemp = new TreeMap<>();
                
                if (fields.length > 3) {
                    for(String speciesId : fields[3].split(",")){
                        
                        Species species = _species.get(speciesId);
                        speciesTemp.put(species.getSpeciesId(),species);
                    }
                }
                
                employee = new Veterinarian(fields[1], fields[2], speciesTemp);
                
                if (fields.length > 3) {
                    for(String speciesId : fields[3].split(",")){
                        
                        Species species = _species.get(speciesId);
                        species.addVeterinarian(employee);
                    }
                }
            }   
            
            case "TRATADOR", "TRT" -> { 
                Map<String, Habitat> habitatsTemp = new TreeMap<>();

                if (fields.length > 3) {    
                    for(String habitatsId : fields[3].split(",")){
                
                        Habitat habitat = _habitats.get(habitatsId);
                        habitatsTemp.put(habitat.getHabitatId(), habitat);
                    }
                }

                employee = new Keeper(fields[1], fields[2], habitatsTemp);
                
                if (fields.length > 3) {    
                    for(String habitatsId : fields[3].split(",")){
                
                        Habitat habitat = _habitats.get(habitatsId);
                        habitat.addKeeper(employee);
                    }
                }
            }

            default -> throw new UnrecognizedEntryException(fields[0]);
        }
        _employees.put(employee.getEmployeeId(), employee);
        
        changed();
    }

    
    public void registerVaccine(String... fields) throws DuplicateVaccineException, UnknownSpeciesException {

        if(_vaccines.containsKey(fields[1])) {
            throw new DuplicateVaccineException();
        }

        Map<String,Species> speciesTemp = new TreeMap<>();
        
        if(fields.length > 3){
            String speciesIdString = fields[3].replaceAll("\\s", "");
            
            for(String speciesId : speciesIdString.split(",")){

                Species species = getSpecies(speciesId);
                speciesTemp.put(species.getSpeciesId(),species);
            }
        }
        
        Vaccine vaccine = new Vaccine(fields[1], fields[2], speciesTemp);
        _vaccines.put(vaccine.getVaccineId(), vaccine);
        
        changed();
    }

    
    public void registerTree(String... fields) throws UnrecognizedEntryException, DuplicateTreeException {

        if(_trees.containsKey(fields[1])) {
            throw new DuplicateTreeException();
        }

        Tree tree;
        switch (fields[5]){
            case "PERENE" -> tree = new EvergreenTree(fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), getCurrentSeason());
            case "CADUCA" -> tree = new DeciduousTree(fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), getCurrentSeason());
            default -> throw new UnrecognizedEntryException(fields[0]);
        }

        _trees.put(tree.getTreeId(), tree);
        
        changed();
    }

   
    public boolean hasChanged() {
        return _changed;

    }

    
    public void changed() {
        _changed = true;
    }
    
    
    public void notChanged() {
        _changed = false;
    }

    
    public Collection<Animal> getAllAnimals() {
        return Collections.unmodifiableCollection(_animals.values());
    }


    public Collection<Habitat> getAllHabitats() {
        return Collections.unmodifiableCollection(_habitats.values());
    }


    public Collection<Tree> getAllTreesFromHabitat(Habitat habitat)  {
        return Collections.unmodifiableCollection(habitat.getTrees());
    }

    
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(_employees.values());
    }

    
    public Collection<Vaccine> getAllVaccines() {
        return Collections.unmodifiableCollection(_vaccines.values());
    }


    public Collection<Animal> getAnimalsFromHabitat(String habitatId) throws UnknownHabitatException {
        Habitat habitat = getHabitat(habitatId);
        return Collections.unmodifiableCollection(habitat.getAnimals());
    }

    public Collection<VaccinationRecord> getAllVaccinationRecords() {
        return Collections.unmodifiableCollection(_vaccinationRecords);
    }

    public Collection<VaccinationRecord> getMedicalActsOnAnimal (String animalId) throws UnknownAnimalException {
        Animal animal = getAnimal(animalId);
        return Collections.unmodifiableCollection(animal.getVaccinationRecords());
    }


    public Collection<VaccinationRecord> getMedicalActsByVeterinarian(String veterinarianId) throws UnknownEmployeeException, EmployeeNotVeterinarian {
        Employee employee = getEmployee(veterinarianId);
        
        if (!(employee instanceof Veterinarian)){
            throw new EmployeeNotVeterinarian();
        }
        
        return Collections.unmodifiableCollection(((Veterinarian) employee).getVaccinationRecords());
    }

    public Collection<VaccinationRecord> getWrongVaccinations() {
        List<VaccinationRecord> wrongVaccinations = new ArrayList<>();
        
        for (VaccinationRecord record : _vaccinationRecords) {
            if(!(record.getTermHealthStatus().equals("NORMAL"))){
                wrongVaccinations.add(record);
            }
        }
        return Collections.unmodifiableCollection(wrongVaccinations);
    }

    
    public int nextSeason() {
        for (Tree tree : _trees.values()) {
            tree.updateTreeAge();
        }
        changed();
        return _currentSeason.nextSeason();
    }


    public void changeHabitatArea (String habitatId, int newArea) throws UnknownHabitatException {
        Habitat habitat = getHabitat(habitatId);
        habitat.setHabitatArea(newArea);
        changed();
    }

    public void changeHabitatInfluence (String habitatId, String speciesId, String influence) throws UnknownHabitatException, UnknownSpeciesException {
        Habitat habitat = getHabitat(habitatId);
        Species species = getSpecies(speciesId);
        habitat.setAnimalInfluence(speciesId, influence);
        changed();
    }


    public void addTreeToHabitat(String... fields) throws UnknownHabitatException, DuplicateTreeException, UnknownTreeException {
        String habitatId = fields[0];
        String treeId = fields[1];
        String treeName = fields[2];
        String age = fields[3];
        String baseCleaningDifficulty = fields[4];
        String type = fields[5];
        
        Habitat habitat = getHabitat(habitatId);
        try{ 
            registerTree("ÁRVORE", treeId, treeName, age, baseCleaningDifficulty, type);
            Tree tree = getTree(treeId);
            habitat.addTree(tree);
        }
        catch (UnrecognizedEntryException e){
            e.printStackTrace();
        }
        changed();
    }

    public void transferAnimalToHabitat(String animalId, String habitatId) throws UnknownAnimalException, UnknownHabitatException {
        Animal animal = getAnimal(animalId);
        Habitat habitat = getHabitat(habitatId);
        Habitat oldHabitat = animal.getHabitat();
        oldHabitat.removeAnimal(animalId);
        habitat.addAnimal(animal);
        animal.setHabitat(habitat);
        changed();
    }

    

    public void addResponsability(String employeeId, String resposibilityId) throws UnknownEmployeeException, UnknownSpeciesException, UnknownHabitatException{
        Employee employee = getEmployee(employeeId);
        
        if (employee instanceof Veterinarian) {
            Species species = getSpecies(resposibilityId);
            ((Veterinarian) employee).addSpecies(species);
            species.addVeterinarian(employee);
        } 
        else if (employee instanceof Keeper) {
            Habitat habitat = getHabitat(resposibilityId);
            ((Keeper) employee).addHabitat(habitat);
            habitat.addKeeper(employee);
        }
        changed();
    }

    public void removeResponsibility(String EmployeeId, String responsibilityId) throws UnknownEmployeeException, UnknownSpeciesException, UnknownHabitatException, NotResponsableException {
        Employee employee = getEmployee(EmployeeId);
        
        if (employee instanceof Veterinarian) {
            Species species = getSpecies(responsibilityId);
            ((Veterinarian) employee).removeSpecies(species);
            species.removeVeterinarian(employee);
        } 
        else if (employee instanceof Keeper) {
            Habitat habitat = getHabitat(responsibilityId);
            ((Keeper) employee).removeHabitat(habitat);
            habitat.removeKeeper(employee);
        }
        changed();
    }

    public double getSatisfactionOfAnimal(String animalId) throws UnknownAnimalException {
        Animal animal = getAnimal(animalId);
        return animal.calculateAnimalSatisfaction();
    }

    public double getSatisfactionOfEmployee(String employeeId) throws UnknownEmployeeException {
        Employee employee = getEmployee(employeeId);
        return employee.calculateSatisfaction();
    }

    public double getSatisfactionOfHotel() {
        double satisfaction = 0;
        Collection<Animal> animals = getAllAnimals();
        Collection<Employee> employees = getAllEmployees();

        if(animals != null) {
            for (Animal animal : animals) {
                satisfaction += animal.calculateAnimalSatisfaction();
            }
        }
        
        if(employees != null) {
            for (Employee employee : employees) {
                satisfaction += employee.calculateSatisfaction();
            }
        }

        return satisfaction;
    }

    public void vaccinateAnimal(String vaccineId, String veternarianId, String animalId) throws UnknownVaccineException, UnknownEmployeeException, UnknownAnimalException, CannotVaccinate, VaccineNotSuitableException, EmployeeNotVeterinarian {
        Vaccine vaccine = getVaccine(vaccineId);
        Animal animal = getAnimal(animalId);
        Employee employee = getEmployee(veternarianId);

        if (!(employee instanceof Veterinarian)){
            throw new EmployeeNotVeterinarian();
        }

        Veterinarian veterinarian = (Veterinarian) employee;
        
        if (!veterinarian.canVaccinate(animal.getSpecies())) {
            throw new CannotVaccinate(veternarianId, animal.getSpecies().getSpeciesId());
        }

        int damage = vaccine.giveVaccine(veterinarian, animal);

        VaccinationRecord record = new VaccinationRecord(vaccine, animal, veterinarian, damage);

        _vaccinationRecords.add(record);
        animal.addVaccinationRecord(record);
        veterinarian.addVaccinationRecord(record);

        if(!(record.getTermHealthStatus().equals("NORMAL"))){ 
            throw new VaccineNotSuitableException();
        }
        changed();
    }

    
}
