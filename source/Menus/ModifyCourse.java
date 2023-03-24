package source.Menus;
import source.Course;
import source.Module;
import source.LMSFacade;
import java.util.ArrayList;

public class ModifyCourse extends ListMenu {
    
    public ModifyCourse(LMSFacade facade, Menu pMenu, Course course) {
        super(facade, pMenu, (ArrayList<?>)course.getAllModules(), "There are no modules to modify");

        options = new String[]{"Next module", "Previous module", "Add module after current module", "Remove module", "Modify Module", "Back"};
    }

    protected void updateHeader(){
        Module module = (Module)list.get(index);
        header = "***Viewing module "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+module.toString();     
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                next();
                break;
            case 2:
                prev();
                break;
            case 3:
                facade.addModule((ArrayList<Module>)list);
                index++;
                getSelection("Module added, select Modify Module to add content");
                break;
            case 4:
                list.remove(index);
                getSelection("Module Removed");
                break;
            case 5:
                // Modify Module
                break;
            case 6:
                back();
                break;
        }
    }
}
