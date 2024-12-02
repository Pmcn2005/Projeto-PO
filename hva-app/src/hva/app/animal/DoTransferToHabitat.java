package hva.app.animal;

import hva.Hotel;
import hva.exceptions.UnknownAnimalException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownHabitatException;
//FIXME import other classes if needed

class DoTransferToHabitat extends Command<Hotel> {

    DoTransferToHabitat(Hotel hotel) {
        super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
        addStringField("animalId", Prompt.animalKey());
        addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try{
            String animalId = stringField("animalId");
            String habitatId = stringField("habitatId");
            _receiver.transferAnimalToHabitat(animalId, habitatId);
        }
        catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(stringField("animalId"));
        }
        catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(stringField("habitatId"));
        }
    }

}
