package source.Menus;
import java.util.ArrayList;
import source.*;
import source.Module;

public class TakeModule extends ListMenu<String>{

    private Module module;
    private ModuleProgress moduleProgress;

    public TakeModule(LMSFacade facade, Menu pMenu, Module module, ModuleProgress moduleProgress) {
        super(facade, pMenu, module.getLessons());
        this.module = module;
        this.moduleProgress = moduleProgress;
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
                facade.takeQuiz(module.getQuiz(), moduleProgress);
                break;
            case 4:
                back();
        }
    }
}
