package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.app.exceptions.FileOpenFailedException;
import hva.exceptions.UnavailableFileException;
import java.io.FileNotFoundException;
import java.io.IOException;

//FIXME import other classes if needed

class DoOpenFile extends Command<HotelManager> {
    DoOpenFile(HotelManager receiver) {
        super(Label.OPEN_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            if (_receiver.hasUnsavedChanges() && Form.confirm(Prompt.saveBeforeExit())) {
                DoSaveFile cmd = new DoSaveFile(_receiver);
                cmd.execute();
            }
            _receiver.load(Form.requestString(Prompt.openFile()));
        } catch (UnavailableFileException e) {
        throw new FileOpenFailedException(e);
        }
        catch (FileNotFoundException e) {
            throw new FileOpenFailedException(e);
        }
        catch (IOException e) {
            throw new FileOpenFailedException(e);
        }
        catch (ClassNotFoundException e) {
            throw new FileOpenFailedException(e);
        }
    }
}


