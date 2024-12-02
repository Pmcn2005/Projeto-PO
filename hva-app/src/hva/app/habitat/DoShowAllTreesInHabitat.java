package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException; 
//FIXME import other classes if needed

class DoShowAllTreesInHabitat extends Command<Hotel> {

    DoShowAllTreesInHabitat(Hotel receiver) {
        super(Label.SHOW_TREES_IN_HABITAT, receiver);
        addStringField("habitatId", Prompt.habitatKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String habitatId = stringField("habitatId");
            _display.popup(_receiver.getAllTreesFromHabitat(_receiver.getHabitat(habitatId)));  
        }
        catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(stringField("habitatId"));
        }
    }
}
