package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.DuplicateEmployeeKeyException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.UnrecognizedEntryException;
//FIXME import other classes if needed
import hva.exceptions.UnrecognizedEntryException;

class DoRegisterEmployee extends Command<Hotel> {

    DoRegisterEmployee(Hotel receiver) {
        super(Label.REGISTER_EMPLOYEE, receiver);
        addStringField("employeeId",Prompt.employeeKey());
        addStringField("employeeName",Prompt.employeeName());
        addOptionField("employeeType",Prompt.employeeType(), "VET", "TRT");
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command
        try {
            String employeeId = stringField("employeeId");
            String employeeName = stringField("employeeName");
            String employeeType = optionField("employeeType");
            _receiver.registerEmployee(employeeType, employeeId, employeeName);
        }
        catch (DuplicateEmployeeException e){
            throw new DuplicateEmployeeKeyException(stringField("employeeId"));
        }
        catch (UnrecognizedEntryException e){
            e.printStackTrace();
        }
    }
}
  