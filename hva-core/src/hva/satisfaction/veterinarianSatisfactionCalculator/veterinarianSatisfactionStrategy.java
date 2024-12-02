package hva.satisfaction.veterinarianSatisfactionCalculator;

import hva.employee.Veterinarian;

public interface veterinarianSatisfactionStrategy {
    public double calculateSatisfaction(Veterinarian veterinarian);
}
