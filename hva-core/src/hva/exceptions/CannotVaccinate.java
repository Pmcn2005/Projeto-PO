package hva.exceptions;

public class CannotVaccinate extends Exception {
    private String _speciesId;
    private String _vetId;
    
    public CannotVaccinate(String vetId, String speciesId) {
        _vetId = vetId;
        _speciesId = speciesId;
    }

    public String getSpeciesId() {
        return _speciesId;
    }  

    public String getVetId() {
        return _vetId;
    }
    
}
