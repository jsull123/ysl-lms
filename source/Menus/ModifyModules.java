package source.Menus;
import source.Module;
import source.LMSFacade;
import java.util.ArrayList;
import source.LMSFacade;

public class ModifyModules extends ListMenu{
    
    private ArrayList<Module> modules;
    private int moduleIndex;

    public ModifyModules(LMSFacade facade, Menu pMenu, ArrayList<Module> modules) {
        super(facade, pMenu, modules, "There are no modules");
        this.modules = modules;
        moduleIndex = 0;
        header = "Module: " + (moduleIndex+1) + " of " + modules.size() + "\n" + modules.get(moduleIndex).toString();
        options = new String[]{"Set title", "Set topic", "Modify content", "back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                facade.setModuleTitle(modules.get(moduleIndex));
                updateHeader();
                getSelection("Module title changed.");
                break;
            case 2:
                facade.setModuleTopic(modules.get(moduleIndex));
                updateHeader();
                getSelection("Module topic changed.");
                break;
            case 3:
                facade.setCurrentMenu(new ModifyContent(facade, pMenu, modules.get(moduleIndex).getAllContent())).getSelection();
                //switch to view contents menu
                break;
            case 4:
                back();
                break;

        }

    }

    protected void updateHeader() {
        header = "Module: " + (moduleIndex+1) + " of " + modules.size() + "\n" + modules.get(moduleIndex).toString();
    }
}
