package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.menus.Command;

class DoOpenEmployeesMenu extends Command<HotelManager> {
    DoOpenEmployeesMenu(HotelManager receiver) {
        super(Label.MENU_EMPLOYEES, receiver);
    }

    @Override
    protected final void execute() {
        (new hva.app.employee.Menu(_receiver.getHotel())).open();
    }

}
