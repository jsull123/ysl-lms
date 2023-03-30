package source.Menus;
import java.util.ArrayList;
import source.*;
import source.Module;

public class TakeModule extends ListMenu<String>{

    public TakeModule(LMSFacade facade, Menu pMenu, Module module, EnrolledCourse course) {
        super(facade, pMenu, module.getLessons());

        if (list.size() == 0){
            options = new String[]{"Take Quiz", "Back"};
        }else{
            options = new String[]{"Next Lesson", "Previous Lesson", "Take Quiz", "Back"};
        }
    }

    protected void updateHeader(){ 
        if (list.size() == 0){
            header = "This module has no lessons\n";
        }else{
            header = "***Viewing lesson "+(index+1)+" of "+
            list.size()+"***\n";
        }
    }

    public void select(int selection){
        if (list.size() == 0){
            switch(selection){
                case 1:
                    // take quiz
                case 2:
                    back();
            }
        }
        switch(selection){
            case 1:
                next();
            case 2:
                prev();
            case 3:
                //take quiz
            case 4:
                back();
        }
    }
}
