package source.Menus;
import java.util.ArrayList;
import source.*;
import source.Module;

public class TakeModule extends ListMenu<String>{

    private Module module;
    private ModuleProgress moduleProgress;
    /**
     * Constructs a TakeModule object
     * @param: LMSFacade facade = object to use the Facade methods
     * @param: Menu pMenu = object to use the data members and methods of Menu class
     * @param: Module module = The module that the user is about to start
     * @param: ModuleProgress moduleProgress = The user's progress in their current module
     */
    public TakeModule(LMSFacade facade, Menu pMenu, Module module, ModuleProgress moduleProgress) {
        super(facade, pMenu, module.getLessons());
        this.module = module;
        this.moduleProgress = moduleProgress;
        if (list.size() == 0){
            options = new String[]{"Take Quiz", "Back"};
        }else{
            options = new String[]{"Next Lesson", "Previous Lesson", "Print Lesson", "Take Quiz", "Back"};
        }
    }
     /**
     * Gives the user options to choose from
     */
    protected void updateHeader(){ 
        if (list.size() == 0){
            header = "This module has no lessons\n";
        }else{
            header = "***Viewing lesson "+(index+1)+" of "+
            list.size()+"***\n\n" + get()+"\n";
        }
    }
     /**
     * Gives the user options to move through this screen of the LMS
     * @param: int selection = The choice that the user has made
     */
    public void select(int selection){
        if (list.size() == 0){
            switch(selection){
                case 1:
                    facade.takeQuiz(module.getQuiz(), moduleProgress);
                    break;
                case 2:
                    back();
            }
        }
        switch(selection){
            case 1:
                next();
                break;
            case 2:
                prev();
                break;
            case 3:
                facade.outputToFile(get(), "SavedLesson.txt");
                getSelection("Printed lesson to \"SavedLesson.txt\"");
            case 4:
                facade.takeQuiz(module.getQuiz(), moduleProgress);
                break;
            case 5:
                back();
        }
    }
}
