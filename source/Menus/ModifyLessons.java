package source.Menus;
import java.util.ArrayList;
import source.*;

public class ModifyLessons extends ListMenu<String>{
    /**
     * Constructs a ModifyLessons object
     * @param: LMSFacade facade = object to use the Facade methods
     * @param: Menu pMenu = object to use the data members and methods of Menu class
     * @param: ArrayList<String> lessons = Lesson(s) to be modified
     */
    public ModifyLessons(LMSFacade facade, Menu pMenu, ArrayList<String> lessons) {
        super(facade, pMenu, lessons);
    }
    /**
     * Gives the user options based on if they've created a course yet or not
     */
    private void updateOptions(){
        if (list.size() == 0){
            options = new String[]{"Create New Lesson", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Create New Lesson", "Remove This Lesson", "Back"};
        }
    }
     /**
     * Gives the user options to choose from
     */
    protected void updateHeader() {
        if (list.size() == 0){
            header = "This module has no lessons. Choose option 1 to add a new lesson\n";
        }else{
            header = "***Viewing lesson "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+get()+"\n"; 
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
                    facade.createLesson(list);
                case 2:
                    back();
            }
        }
        switch(selection) {
            case 1:
                next();
                break;
            case 2:
                prev();      
                break;
            case 3:
                facade.createLesson(list);
                break;
            case 4:
                remove();
            case 5:
                back();
        }
    }
}
