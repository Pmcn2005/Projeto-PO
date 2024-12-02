package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.EmployeeNotVeterinarian;


class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

    DoShowMedicalActsByVeterinarian(Hotel receiver) {
        super(Label.MEDICAL_ACTS_BY_VET, receiver);
        addStringField("employeeId", hva.app.employee.Prompt.employeeKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String employeeId = stringField("employeeId");
            _display.popup(_receiver.getMedicalActsByVeterinarian(employeeId));
        } 
        catch (EmployeeNotVeterinarian | UnknownEmployeeException e) {
            throw new UnknownVeterinarianKeyException(stringField("employeeId"));
        }
    }

}
