package source.Menus;
import source.Course;
import source.Module;
import source.LMSFacade;
import java.util.ArrayList;

public class ModifyCourse extends Menu {
    private ArrayList<Module> modules;
    private Course course;
    private int moduleIndex;
    private Menu prevMenu;
    
    public ModifyCourse(LMSFacade facade, Menu prevMenu, Course incourse) {
        if (modules.size() == 0){
            facade.setCurrentMenu(prevMenu);
            facade.getCurrentMenu().getSelection("There are no modules to modify");
            return;
        }

        this.course = incourse;
        this.modules = course.getAllModules();
        this.facade = facade;
        this.moduleIndex = 0;
        this.prevMenu = prevMenu;
        header = modules.get(moduleIndex).toString();
        options = new String[]{"Next module", "Previous module", "Add module after current module", "Remove module", "Modify Module", "Back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                moduleIndex++;
                if (moduleIndex >= modules.size() - 1) moduleIndex = 0;
                header = modules.get(moduleIndex).toString();
                getSelection();
                break;
            case 2:
                if (moduleIndex <= 0) {
                    moduleIndex = modules.size() - 1;
                }
                else moduleIndex--;
                header = modules.get(moduleIndex).toString();
                getSelection();
                break;
            case 3:
                facade.addModule(modules);
                moduleIndex++;
                header = modules.get(moduleIndex).toString();
                getSelection();
                break;
            case 4:
                modules.remove(moduleIndex);
                System.out.println("Module removed");
                header = modules.get(moduleIndex).toString();
                getSelection();
                break;
            case 5:
                // Modify Module
                break;
            case 6:
                facade.setCurrentMenu(prevMenu);
                facade.getCurrentMenu().getSelection();
                break;
        }
    }
}
