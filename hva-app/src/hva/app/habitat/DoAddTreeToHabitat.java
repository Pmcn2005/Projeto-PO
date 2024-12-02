package hva.app.habitat;

import hva.Hotel;
import hva.app.exceptions.DuplicateTreeKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.UnknownTreeKeyException;
import hva.exceptions.DuplicateTreeException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownTreeException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME import other classes if needed

class DoAddTreeToHabitat extends Command<Hotel> {

    DoAddTreeToHabitat(Hotel receiver) {
        super(Label.ADD_TREE_TO_HABITAT, receiver);
        addStringField("habitatId", Prompt.habitatKey());
        addStringField("treeId", Prompt.treeKey());
        addStringField("treeName", Prompt.treeName());
        addStringField("treeAge", Prompt.treeAge());
        addStringField("treeDificulty", Prompt.treeDifficulty());
        addOptionField("treeType", Prompt.treeType(), "PERENE", "CADUCA");
    }

    @Override
    protected void execute() throws CommandException {
        try{
            String habitatId = stringField("habitatId");
            String treeId = stringField("treeId");
            String treeName = stringField("treeName");
            String treeAge = stringField("treeAge");
            String treeDificulty = stringField("treeDificulty");
            String treeType = optionField("treeType");
            _receiver.addTreeToHabitat(habitatId, treeId, treeName, treeAge, treeDificulty, treeType);
            _display.popup(_receiver.getTree(treeId));
        }
        catch(UnknownHabitatException e){
            throw new UnknownHabitatKeyException(stringField("habitatId"));
        }
        catch(DuplicateTreeException e){
            throw new DuplicateTreeKeyException(stringField("treeId"));
        }
        catch(UnknownTreeException e){
            throw new UnknownTreeKeyException(stringField("treeId"));
        }

    }

}
