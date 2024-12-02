package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoShowGlobalSatisfaction extends Command<HotelManager> {
    DoShowGlobalSatisfaction(HotelManager receiver) {
        super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        _display.popup((int) Math.round(_receiver.getGlobalSatisfaction()));
    }
}
