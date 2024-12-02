package hva.employee;

import java.io.Serializable;
import java.util.StringJoiner;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;

import hva.exceptions.NotResponsableException;

import hva.habitat.Habitat;
import hva.satisfaction.keeperSatisfactionCalculator.BasicKeeperSatisfactionCalculator;
import hva.satisfaction.keeperSatisfactionCalculator.keeperSatisfactionStrategy;

public class Keeper extends Employee {

        private Map<String, Habitat> _habitats = new TreeMap<String, Habitat>(String.CASE_INSENSITIVE_ORDER);
        private keeperSatisfactionStrategy _calculator = new BasicKeeperSatisfactionCalculator();

        public Keeper(String employeeId, String name, Map<String, Habitat> habitats) {
            super(employeeId, name);
            _habitats = habitats;
        }

        public void addHabitat(Habitat habitat) {
            _habitats.put(habitat.getHabitatId(), habitat);
        }

        public void removeHabitat(Habitat habitat) throws NotResponsableException{
            if (!_habitats.containsKey(habitat.getHabitatId())) {
                throw new NotResponsableException();}
            _habitats.remove(habitat.getHabitatId());
        }

        public double calculateSatisfaction() {
            return _calculator.calculateSatisfaction(this);
        }

        public Collection<Habitat> getHabitats() {
            return _habitats.values();
        }

        public String presentHabitats() {
            if (_habitats.isEmpty()) {
                return "";
            }

            StringJoiner result = new StringJoiner(",");
            for (Habitat habitat : _habitats.values()) {
                 result.add(habitat.getHabitatId());
            }
            return "|" + result.toString();
        }

        @Override
        public String toString() {
            return "TRT|" + this.getEmployeeId() + "|" + this.getEmployeeName() + presentHabitats();
        }
}