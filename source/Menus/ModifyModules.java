package source.Menus;
import source.Module;
import source.LMSFacade;
import java.util.ArrayList;

public class ModifyModules extends ListMenu<Module>{
    
    public ModifyModules(LMSFacade facade, Menu pMenu, ArrayList<Module> modules) {
        super(facade, pMenu, modules, "There are no modules");

        options = new String[]{"Next", "Previous", "Set title", "Set topic", "Modify content", "Back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                next();
            case 2:
                prev();
            case 3:
                facade.setModuleTitle(get());    
                break;
            case 4:
                facade.setModuleTopic(get());         
                break;
            case 5:
                facade.setCurrentMenu(new ModifyContent(facade, pMenu, get().getAllContent())).getSelection();
                break;
            case 6:
                back();
                break;
        }
    }

    protected void updateHeader() {
        header = "Module: " + (index+1) + " of " + list.size() + "\n" + get().toString();
    }
}
