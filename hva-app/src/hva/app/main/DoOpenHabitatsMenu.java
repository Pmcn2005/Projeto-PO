package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.menus.Command;

class DoOpenHabitatsMenu extends Command<HotelManager> {
    DoOpenHabitatsMenu(HotelManager receiver) {
        super(hva.app.main.Label.MENU_HABITATS, receiver);
    }

    @Override
    protected final void execute() {
        (new hva.app.habitat.Menu(_receiver.getHotel())).open();
    }
}
