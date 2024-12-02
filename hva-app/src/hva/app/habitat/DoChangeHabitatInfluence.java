package hva.app.habitat;

import hva.Hotel;
import hva.app.exceptions.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;
//FIXME import other classes if needed

class DoChangeHabitatInfluence extends Command<Hotel> {

    DoChangeHabitatInfluence(Hotel receiver) {
        super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
        addStringField("habitatId", Prompt.habitatKey());
        addStringField("speciesId", hva.app.animal.Prompt.speciesKey());
        addOptionField("influence", Prompt.habitatInfluence(), "POS", "NEG", "NEU");

    }

    @Override
    protected void execute() throws CommandException {
        try{
            String habitatId = stringField("habitatId");
            String speciesId = stringField("speciesId");
            String influence = optionField("influence");
            _receiver.changeHabitatInfluence(habitatId, speciesId, influence);
        }
        catch (UnknownHabitatException e){
            throw new UnknownHabitatKeyException(stringField("habitatId"));
        }
        catch (UnknownSpeciesException e){
            throw new UnknownSpeciesKeyException(stringField("speciesId"));
        }
    }

}
