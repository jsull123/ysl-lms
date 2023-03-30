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
        ModuleProgress progress = enrolledCourse.getModuleProgress(index);
        if (progress.getHasPassed()){
            header += "You have completed the module. "+"You scored a "+progress.getQuizGrade()*100+"% on the quiz.";
        }else{
            header += "You haven't completed this module.";
        }
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
                facade.setCurrentMenu(new TakeModule(facade, this, get(), enrolledCourse.getModuleProgress(index))).getSelection();
                break;
            case 4:
                back();
                break;
        }
    }
}
