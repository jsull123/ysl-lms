package source.Menus;
import source.Module;
import source.LMSFacade;
import java.util.ArrayList;

public class ModifyModules extends ListMenu<Module>{
    
    public ModifyModules(LMSFacade facade, Menu pMenu, ArrayList<Module> modules) {
        super(facade, pMenu, modules);
        
        if (list.size() == 0){
            options = new String[]{"Create Module", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Create Module", "Set title", "Set topic", "Modify content", "Back"};
        }     
    }

    public void select(int selection) {
        if (list.size() == 0){
            switch(selection){
                case 1:
                    facade.createModule(list);
                case 2:
                    back();
            }
        }
        switch(selection) {
            case 1:
                next();
            case 2:
                prev();
            case 3:
                facade.createModule(list);
            case 4:
                facade.setModuleTitle(get());    
                break;
            case 5:
                facade.setModuleTopic(get());         
                break;
            case 6:
                facade.setCurrentMenu(new ModifyContent(facade, this, get().getAllContent())).getSelection();
                break;
            case 7:
                back();
                break;
        }
    }

    protected void updateHeader() {
        if (list.size() == 0){
            header = "This course does not have any modules. Choose option 1 to create a new module.";
        }else{
            header = "Module: " + (index+1) + " of " + list.size() + "\n" + get().toString();
        }       
    }
}
