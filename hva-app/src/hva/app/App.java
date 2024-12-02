package hva.app;

import hva.exceptions.ImportFileException;
import pt.tecnico.uilib.Dialog;

public class App {

    public static void main(String[] args) {
        try (var ui = Dialog.UI) {
            var receiver = new hva.HotelManager();
            String datafile = System.getProperty("import");
            if (datafile != null) {
                try {
                    receiver.importFile(datafile);
                } catch (ImportFileException e) {
                    // no behavior described: just present the problem
                    e.printStackTrace();
                }
            }

            (new hva.app.main.Menu(receiver)).open();
        }
    }

}