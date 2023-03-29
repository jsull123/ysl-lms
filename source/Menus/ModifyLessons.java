package source.Menus;
import java.util.ArrayList;
import source.*;

public class ModifyLessons extends ListMenu<String>{
  
    public ModifyLessons(LMSFacade facade, Menu pMenu, ArrayList<String> lessons) {
        super(facade, pMenu, lessons);
    }

    private void updateOptions(){
        if (list.size() == 0){
            options = new String[]{"Create New Lesson", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Create New Lesson", "Remove This Lesson", "Back"};
        }
    }

    protected void updateHeader() {
        if (list.size() == 0){
            header = "This module has no lessons. Choose option 1 to add a new lesson\n";
        }else{
            header = "***Viewing lesson "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+get()+"\n"; 
        }
        updateOptions();
    } 

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
