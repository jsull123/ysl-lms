package source.Menus;
import source.Content;
import source.LMSFacade;
import java.util.ArrayList;

public class ModifyContent extends ListMenu<Content>{

    public ModifyContent(LMSFacade facade, Menu pMenu, ArrayList<Content> content) {
        super(facade, pMenu, content, "There is no content for this module");
       
        options = new String[]{"Next", "Previous", "Set lesson information", "Set title", "Set passing grade", "Modify Questions", "Back"};
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
                facade.setContentLesson(get());
                break;
            case 4:
                facade.setContentTitle(get());   
                break;
            case 5:
                facade.setPassingGrade(get());
                break;
            case 6:
                facade.setCurrentMenu(new ModifyQuestions(facade, this, get().getQuestions())).getSelection();
                break;
            case 7:
                back();
                break;

        }

    }

    protected void updateHeader() {
        header = "Content: " + (index+1) + " of " + list.size() + "\n\n"
         + "Content Type and Title: " + list.get(index).toString() + "\n"
         + "Lesson: " + ((Content)list.get(index)).getLesson() + "\n"
         + "Passing Grade: " + ((Content)list.get(index)).getPassingGrade() + "\n";
    }
}
