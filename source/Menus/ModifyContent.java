package source.Menus;
import source.Content;
import source.LMSFacade;
import java.util.ArrayList;
import source.LMSFacade;

public class ModifyContent extends ListMenu{

    public ModifyContent(LMSFacade facade, Menu pMenu, ArrayList<Content> content) {
        super(facade, pMenu, content, "There is no content for this module");
        this.list = content;
        index = 0;
        header = "Content: " + (index+1) + " of " + content.size() + "\n"
        + "Content Type and Title: " + content.get(index).toString() + "\n"
        + "Lesson: " + content.get(index).getLesson() + "\n"
        + "Passing Grade: " + content.get(index).getPassingGrade();
        options = new String[]{"Set lesson information", "Set title", "Set passing grade", "Modify Questions", "back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                facade.setContentLesson((Content)list.get(index));
                updateHeader();
                getSelection("Content lesson information changed.");
                selection = 1;
                break;
            case 2:
                facade.setContentTitle((Content)list.get(index));
                updateHeader();
                getSelection("Content title changed.");
                selection = 1;
                break;
            case 3:
                facade.setPassingGrade((Content)list.get(index));
                updateHeader();
                getSelection("Content passing grade changed.");
                selection = 1;
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
         + "Lesson: " + list.get(index).getLesson() + "\n"
         + "Passing Grade: " + list.get(index).getPassingGrade();
    }
}
