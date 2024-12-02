package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.menus.Command;

class DoOpenLookupsMenu extends Command<HotelManager> {
    DoOpenLookupsMenu(HotelManager receiver) {
        super(hva.app.main.Label.MENU_LOOKUPS, receiver);
    }

    @Override
    protected final void execute() {
        (new hva.app.search.Menu(_receiver.getHotel())).open();
    }
}
