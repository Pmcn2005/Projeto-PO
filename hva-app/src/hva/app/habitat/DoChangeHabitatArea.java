package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException;


class DoChangeHabitatArea extends Command<Hotel> {

    DoChangeHabitatArea(Hotel receiver) {
        super(Label.CHANGE_HABITAT_AREA, receiver);
        addStringField("habitatId", Prompt.habitatKey());
        addIntegerField("habitatArea", Prompt.habitatArea());
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
        try {
            String habitatId = stringField("habitatId");
            int habitatArea = integerField("habitatArea");
            _receiver.changeHabitatArea(habitatId, habitatArea);
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(stringField("habitatId"));
        }
    }

}
