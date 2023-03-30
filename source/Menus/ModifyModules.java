package source.Menus;
import source.Module;
import source.LMSFacade;
import java.util.ArrayList;

public class ModifyModules extends ListMenu<Module>{
    /**
     * Constructs a ModifyModules object
     * @param: LMSFacade facade = object to use the Facade methods
     * @param: Menu pMenu = object to use the data members and methods of Menu class
     * @param: ArrayList<Module> modules = Module(s) to be modified
     */
    public ModifyModules(LMSFacade facade, Menu pMenu, ArrayList<Module> modules) {
        super(facade, pMenu, modules);  
    }
    /**
     * Gives the user options based on if they've created a course yet or not
     */
    private void updateOptions(){   
        if (list.size() == 0){
            options = new String[]{"Create New Module", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Create New Module", "Change Title", "Change Topic", "Modify Lessons", "Modify Quiz", "Back"};
        }
    }
     /**
     * Gives the user options to choose from
     */
    protected void updateHeader() {
        if (list.size() == 0){
            header = "This course does not have any modules. Choose option 1 to create a new module.\n";
        }else{
            header = "Module: " + (index+1) + " of " + list.size() + "\n\n" + get().toString();
        }
        updateOptions();
    }
     /**
     * Gives the user options to move through this screen of the LMS
     * @param: int selection = The choice that the user has made
     */
    public void select(int selection) {
        if (list.size() == 0){
            switch(selection){
                case 1:
                    facade.createModule(list);
                case 2:
                    back();
            }
        }
        switch(selection) {
            case 1:
                next();
            case 2:
                prev();
            case 3:
                facade.createModule(list);
            case 4:
                facade.setModuleTitle(get());    
                break;
            case 5:
                facade.setModuleTopic(get());         
                break;
            case 6:
                facade.setCurrentMenu(new ModifyLessons(facade, this, get().getLessons())).getSelection();
                break;
            case 7:
                facade.setCurrentMenu(new ModifyQuiz(facade, this, get().getQuiz())).getSelection();
            case 8:
                back();
                break;
        }
    }
}
