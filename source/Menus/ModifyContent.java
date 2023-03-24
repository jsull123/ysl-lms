package source.Menus;
import source.Content;
import source.LMSFacade;
import java.util.ArrayList;
import source.LMSFacade;

public class ModifyContent extends ListMenu{
    
    private ArrayList<Content> content;
    private int contentIndex;

    public ModifyContent(LMSFacade facade, Menu pMenu, ArrayList<Content> content) {
        super(facade, pMenu, content, "There is no content for this module");
        this.content = content;
        contentIndex = 0;
        header = "Content: " + (contentIndex+1) + " of " + content.size() + "\n"
        + "Content Type and Title: " + content.get(contentIndex).toString() + "\n"
        + "Lesson: " + content.get(contentIndex).getLesson() + "\n"
        + "Passing Grade: " + content.get(contentIndex).getPassingGrade();
        options = new String[]{"Set lesson", "Set title", "Set passing grade", "Modify Questions", "back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                facade.setContentLesson(content.get(contentIndex));
                updateHeader();
                getSelection("Content lesson changed.");
                break;
            case 2:
                facade.setContentTitle(content.get(contentIndex));
                updateHeader();
                getSelection("Content title changed.");
                break;
            case 3:
                //facade.setCurrentMenu(new ModifyContent();
                //switch to view contents menu
                break;
            case 4:
                
            case 5:
                back();
                break;

        }

    }

    protected void updateHeader() {
        header = "Content: " + (contentIndex+1) + " of " + content.size() + "\n"
         + "Content Type and Title: " + content.get(contentIndex).toString() + "\n"
         + "Lesson: " + content.get(contentIndex).getLesson() + "\n"
         + "Passing Grade: " + content.get(contentIndex).getPassingGrade();
    }
}
