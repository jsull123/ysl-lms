package source.Menus;
import source.Module;
import source.LMSFacade;
import java.util.ArrayList;
import source.LMSFacade;

public class ModifyModules extends Menu{
    
    private ArrayList<Module> modules;
    private int moduleIndex;

    public ModifyModules(LMSFacade facade, Menu pMenu, ArrayList<Module> modules) {
        this.facade = facade;
        this.pMenu = pMenu;
        this.modules = modules;
        moduleIndex = 0;
        options = new String[]{"Set title", "Set Topic", "View Created Content", "back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
            facade.setModuleTitle(modules.get(moduleIndex));
            break;
        }

    }
}
