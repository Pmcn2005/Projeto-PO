package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.NoResponsibilityException;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.UnknownEmployeeException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoAddResponsibility extends Command<Hotel> {

    DoAddResponsibility(Hotel receiver) {
        super(Label.ADD_RESPONSABILITY, receiver);
        addStringField("employeeId", Prompt.employeeKey());
        addStringField("responsibilityId", Prompt.responsibilityKey());
    }

    @Override
    protected void execute() throws CommandException {
        String employeeId = stringField("employeeId");
        String responsibilityId = stringField("responsibilityId");

        try{
            _receiver.addResponsability(employeeId, responsibilityId);
        }
        catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(stringField("employeeId"));
        }
        catch (UnknownSpeciesException e) {
            throw new NoResponsibilityException(stringField("employeeId"), stringField("responsiblityId"));
        }
        catch(UnknownHabitatException e) {
            throw new NoResponsibilityException(stringField("employeeId"), stringField("responsiblityId"));
        }
    }

}
