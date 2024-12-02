package hva.exceptions;

public class UnknownSpeciesException extends Exception {
    private String _speciesKey;

    public UnknownSpeciesException() {
    }
    
    public UnknownSpeciesException(String speciesKey) {
        _speciesKey = speciesKey;
    }

    public String getSpeciesKey() {
        return _speciesKey;
    }
}
