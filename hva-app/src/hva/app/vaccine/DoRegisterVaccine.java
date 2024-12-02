package hva.app.vaccine;

import hva.Hotel;
import hva.app.exceptions.DuplicateVaccineKeyException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.UnknownSpeciesException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoRegisterVaccine extends Command<Hotel> {

    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);
        addStringField("vaccineId", Prompt.vaccineKey());
        addStringField("vaccineName", Prompt.vaccineName());
        addStringField("properSpecies", Prompt.listOfSpeciesKeys());

    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String vaccineId = stringField("vaccineId");
            String vaccineName = stringField("vaccineName");
            String properSpecies = stringField("properSpecies");
            _receiver.registerVaccine("VACINA", vaccineId, vaccineName, properSpecies);
        }
        catch (DuplicateVaccineException e) {
            throw new DuplicateVaccineKeyException(stringField("vaccineId"));
        }
        catch (UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(e.getSpeciesKey());
        }

    }

}
