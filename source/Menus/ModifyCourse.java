package source.Menus;
import source.Course;
import source.LMSFacade;

public class ModifyCourse extends Menu {
    
    private Course course;

    public ModifyCourse(LMSFacade facade, Menu pMenu, Course course) {
        super(facade, pMenu);
        this.course = course;
        updateHeader();

        options = new String[]{"Change Title", "Change Language", "Change Description", "Modify Modules", "Back"};
    }

    public void updateHeader(){
        header = "Title: "+course.getTitle()+"\n"+
        "Language: "+course.getLanguage()+"\n"+
        "Description: "+course.getDescription()+"\n";
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                facade.setCourseTitle(course);
                updateHeader();
                break;
            case 2:
                facade.setCourseLanguage(course);
                updateHeader();
                break;
            case 3:
                facade.setCourseDescription(course);
                updateHeader();
                break;
            case 4:
                facade.setCurrentMenu(new ModifyModules(facade, this, course.getAllModules())).getSelection();
                break;
            case 5:
                back();
                break;
        }
    }
}
