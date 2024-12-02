package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
//FIXME import other classes if needed

class DoShowAllAnimals extends Command<Hotel> {

    DoShowAllAnimals(Hotel receiver) {
        super(Label.SHOW_ALL_ANIMALS, receiver);
    }

    @Override
    protected final void execute() {
        _display.popup(_receiver.getAllAnimals());
    }

}
