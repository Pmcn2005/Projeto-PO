package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownAnimalException;
import hva.app.exceptions.UnknownAnimalKeyException;
//FIXME import other classes if needed

class DoShowSatisfactionOfAnimal extends Command<Hotel> {

    DoShowSatisfactionOfAnimal(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
        addStringField("animalId",Prompt.animalKey());
        
    }

    @Override
    protected final void execute() throws CommandException {
        try{
            String animalId = stringField("animalId");
            _display.popup((int)Math.round(_receiver.getSatisfactionOfAnimal(animalId)));
        }
        catch(UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(stringField("animalId"));
        }
    }

}
