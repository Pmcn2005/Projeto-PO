package hva.app.vaccine;

import hva.Hotel;
import hva.app.exceptions.UnknownVaccineKeyException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.VeterinarianNotAuthorizedException;
import hva.exceptions.CannotVaccinate;
import hva.exceptions.EmployeeNotVeterinarian;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.VaccineNotSuitableException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoVaccinateAnimal extends Command<Hotel> {

    DoVaccinateAnimal(Hotel receiver) {
        super(Label.VACCINATE_ANIMAL, receiver);
        addStringField("vaccineId", Prompt.vaccineKey());
        addStringField("veterinarianId", Prompt.veterinarianKey());
        addStringField("animalId", hva.app.animal.Prompt.animalKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {   
            String vaccineId = stringField("vaccineId");
            String veterinarianId = stringField("veterinarianId");
            String animalId = stringField("animalId");

            _receiver.vaccinateAnimal(vaccineId, veterinarianId, animalId);
        }
        catch (UnknownVaccineException e) {
            throw new UnknownVaccineKeyException(stringField("vaccineId"));
        }
        catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(stringField("animalId"));
        }
        catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(stringField("veterinarianId"));
        }
        catch (EmployeeNotVeterinarian e) {
            throw new UnknownVeterinarianKeyException(stringField("veterinarianId"));
        }
        catch (CannotVaccinate e) {
            throw new VeterinarianNotAuthorizedException(e.getVetId(), e.getSpeciesId());
        }
        catch (VaccineNotSuitableException e) {
            _display.popup(Message.wrongVaccine(stringField("vaccineId"), stringField("animalId")));
        }

    }

}
