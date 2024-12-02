package hva.app.animal;

import hva.Hotel;
import hva.app.exceptions.DuplicateAnimalKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateSpeciesException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoRegisterAnimal extends Command<Hotel> {

    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        addStringField("animalId", Prompt.animalKey());
        addStringField("animalName", Prompt.animalName());
        addStringField("speciesId", Prompt.speciesKey());
        addStringField("habitatId", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected final void execute() throws CommandException {
        
        String animalId = stringField("animalId");
        String animalName = stringField("animalName");
        String speciesId = stringField("speciesId");
        String habitatId = stringField("habitatId");
        
        try {
            _receiver.registerAnimal("ANIMAL",animalId, animalName, speciesId, habitatId);
        }
        catch (UnknownSpeciesException e) {
            try {
                Form form = new Form();
                form.addStringField("speciesName", Prompt.speciesName());
                form.parse();

                String speciesName = form.stringField("speciesName");
                _receiver.registerSpecies("ESPÉCIE", speciesId, speciesName);
                _receiver.registerAnimal("ANIMAL",animalId, animalName, speciesId, habitatId);
            } 
            catch (DuplicateAnimalException ex){
                throw new DuplicateAnimalKeyException(stringField("animalId"));
            }
            catch (UnknownHabitatException ex){
                throw new UnknownHabitatKeyException(stringField("habitatId"));
            }
            catch (UnknownSpeciesException ex){
                ex.printStackTrace();
            }
            catch (DuplicateSpeciesException ex){
                ex.printStackTrace();
            }
        }
        catch (DuplicateAnimalException e){
            throw new DuplicateAnimalKeyException(stringField("animalId"));
        }
        catch (UnknownHabitatException e){
            throw new UnknownHabitatKeyException(stringField("habitatId"));
        }
        
    }


}
