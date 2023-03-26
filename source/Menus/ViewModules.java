package source.Menus;

import java.util.ArrayList;
import source.*;
import source.Module;

public class ViewModules extends ListMenu<Module> {

    private EnrolledCourse enrolledCourse;

    public ViewModules(LMSFacade facade, Menu pMenu, ArrayList<Module> modules, EnrolledCourse enrolledCourse){
        super(facade, pMenu, modules, "This course has no modules.");

        this.enrolledCourse = enrolledCourse;
        this.options = new String[]{"Next", "Previous", "View Content", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing module "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString();
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
                facade.setCurrentMenu(new ViewContent(
                    facade, this, get().getAllContent(), 
                    enrolledCourse.getModuleProgress().get(index))).getSelection();
                break;
            case 4:
                back();
                break;
        }
    }
}
