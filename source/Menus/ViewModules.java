package source.Menus;

import java.util.ArrayList;
import source.*;
import source.Module;

public class ViewModules extends ListMenu<Module> {

    private EnrolledCourse enrolledCourse;

    public ViewModules(LMSFacade facade, Menu pMenu, ArrayList<Module> modules, EnrolledCourse enrolledCourse){
        super(facade, pMenu, modules, "This course has no modules.");

        this.enrolledCourse = enrolledCourse;
        this.options = new String[]{"Next", "Previous", "Start Module", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing module "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString();
        // Add "You have/haven't completed this module. You scored x% on the quiz"
    }

    public void select(int selection){
        switch (selection){
            case 1:
                next();              
                break;
            case 2:
                prev();        
                break;
            case 3:
                // Will lead to OpenModule
                break;
            case 4:
                back();
                break;
        }
    }
}
