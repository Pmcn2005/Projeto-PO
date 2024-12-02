package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.menus.Command;

class DoOpenAnimalsMenu extends Command<HotelManager> {
    DoOpenAnimalsMenu(HotelManager receiver) {
        super(Label.MENU_ANIMALS, receiver);
    }

    @Override
    protected final void execute() {
        (new hva.app.animal.Menu(_receiver.getHotel())).open();
    }
}
