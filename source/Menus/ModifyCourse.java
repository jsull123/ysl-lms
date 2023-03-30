package source.Menus;
import source.Course;
import source.LMSFacade;

public class ModifyCourse extends Menu {
    
    private Course course;
    /**
     * Constructs a ModifyCourse object
     * @param: LMSFacade facade = object to use the Facade methods
     * @param: Menu pMenu = object to use the data members and methods of Menu class
     * @param: Course course = The current course to be modified
     */
    public ModifyCourse(LMSFacade facade, Menu pMenu, Course course) {
        super(facade, pMenu);
        this.course = course;
        updateHeader();
        options = new String[]{"Change Title", "Change Language", "Change Description", "Modify Modules", "Back"};
    }
     /**
     * Gives the user options to choose from
     */
    public void updateHeader(){
        header = "Title: " + course.getTitle() +"\n"
        + "Language: " + course.getLanguage() + "\n"
        + "Description: " + course.getDescription() + "\n";
    }
     /**
     * Gives the user options to move through this screen of the LMS
     * @param: int selection = The choice that the user has made
     */
    public void select(int selection) {
        switch(selection) {
            case 1:
                facade.setCourseTitle(course);
                updateHeader();
                getSelection("Title changed");
                break;
            case 2:
                facade.setCourseLanguage(course);
                updateHeader();
                getSelection("Language changed");
                break;
            case 3:
                facade.setCourseDescription(course);
                updateHeader();
                getSelection("Description changed");
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
