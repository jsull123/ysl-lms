package source.Menus;
import source.Content;
import source.LMSFacade;
import java.util.ArrayList;

public class ModifyContent extends ListMenu<Content>{

    public ModifyContent(LMSFacade facade, Menu pMenu, ArrayList<Content> content) {
        super(facade, pMenu, content, "There is no content for this module");
       
        options = new String[]{"Set lesson information", "Set title", "Set passing grade", "Modify Questions", "Back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                facade.setContentLesson(get());
                break;
            case 2:
                facade.setContentTitle(get());   
                break;
            case 3:
                facade.setPassingGrade(get());
                break;
            case 4:
                //facade.setCurrentMenu(new ModifyQuestion()).getSelection();
                break;
            case 5:
                back();
                break;

        }

    }

    protected void updateHeader() {
        header = "Content: " + (index+1) + " of " + list.size() + "\n"
         + "Content Type and Title: " + list.get(index).toString() + "\n"
         + "Lesson: " + ((Content)list.get(index)).getLesson() + "\n"
         + "Passing Grade: " + ((Content)list.get(index)).getPassingGrade();
    }
}
