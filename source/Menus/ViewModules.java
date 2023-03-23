package source.Menus;

import java.util.ArrayList;
import source.*;
import source.Module;

public class ViewModules extends Menu {
    private int index;
    private ArrayList<Module> modules;
    private Menu pMenu;
    private EnrolledCourse enrolledCourse;

    public ViewModules(LMSFacade facade, Menu pMenu, ArrayList<Module> modules, EnrolledCourse enrolledCourse){
        if (modules == null)
            pMenu.getSelection();
        if (modules.size() == 0) 
            pMenu.getSelection("This course has no modules.");

        index = 0;

        this.enrolledCourse = enrolledCourse;
        header = "***Viewing module "+(index+1)+" of "+
        modules.size()+"***"+"\n\n"+modules.get(index).toString();

        this.facade = facade;
        this.pMenu = pMenu;
        this.modules = modules;
        this.options = new String[]{"Next", "Previous", "View Content", "Back"};
    }

    private void prev(){
        index--;
        if (index < 0) index = modules.size()-1;

        header = "***Viewing module "+(index+1)+" of "+
        modules.size()+"***"+"\n\n"+modules.get(index).toString()+
        "\n"+enrolledCourse.getModuleProgress(index)*100+"% complete";
        getSelection();
    }

    private void next(){
        index++;
        if (index >= modules.size()) index = 0;

        header = "***Viewing module "+(index+1)+" of "+
        modules.size()+"***"+"\n\n"+modules.get(index).toString()+
        "\n"+enrolledCourse.getModuleProgress(index)*100+"% complete";
        getSelection();
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
                // view content menu
                break;
            case 4:
                facade.setCurrentMenu(pMenu).getSelection();
                break;
        }
    }
}
