package source.Menus;
import source.LMSFacade;
import source.Course;
import java.util.ArrayList;

public class EnrollCourseMenu extends ListMenu<Course> {
    /**
     * @param: LMSFacade facade = object to use Facade methods
     * @param: Menu pMEnu = objecy to use Menu methods and data members
     * @param: ArrayList<Course> courses = List of courses the user is able to take
     *
     */
    public EnrollCourseMenu(LMSFacade facade, Menu pMenu, ArrayList<Course> courses) {
        super(facade, pMenu, courses, "No courses found");
        this.list = courses;
        this.index = 0;
        options = new String[]{"Next", "Previous", "View Comments", "View Reviews", "Enroll in This Course", "Back"};
    }

    public void updateHeader(){
        header = "***Viewing courses "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString() +"\n"
         + get().getLanguage() + "\n"
         + get().getDescription() + "\n";
    }
    /**
     * @param: int selection = The choice that the user has made
     */
    public void select(int selection) {
        switch(selection) {
            case 1:
                next();
                break;
            case 2:
                prev();
                break;
            case 3:
                facade.setCurrentMenu(new ViewComments(facade, this, get().getAllComments())).getSelection();
                updateHeader();
                break;
            case 4:
                facade.setCurrentMenu(new ViewReviews(facade, this, get().getAllReviews())).getSelection();
                break;
            case 5:
                facade.enrollInCourse(get());
                break;
            case 6:
                back();
                break;
        }
    }
}