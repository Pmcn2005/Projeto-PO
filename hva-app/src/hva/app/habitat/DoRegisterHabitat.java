package hva.app.habitat;

import hva.Hotel;
import hva.app.exceptions.DuplicateHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.DuplicateHabitatException;
//FIXME import other classes if needed

class DoRegisterHabitat extends Command<Hotel> {

    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);
        //FIXME add command fields if needed
        addStringField("id", Prompt.habitatKey());
        addStringField("name", Prompt.habitatName());
        addStringField("area", Prompt.habitatArea());
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
        try{
            String id = stringField("id");
            String name = stringField("name");
            String area = stringField("area");
            _receiver.registerHabitat("Habitat", id, name, area);
        } catch (DuplicateHabitatException e){
            throw new DuplicateHabitatKeyException(stringField("id"));
        }   
    }
}
