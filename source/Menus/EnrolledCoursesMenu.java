package source.Menus;
import source.*;
import java.util.ArrayList;

public class EnrolledCoursesMenu extends ListMenu<EnrolledCourse> {
    /**
     * @param: LMSFacade facade = object to use Facade methods
     * @param: Menu pMEnu = objecy to use Menu methods and data members
     * @param: ArrayList<EnrolledCourse> enrolledCourses = List of courses that the user is currently enrolled in
     */
    public EnrolledCoursesMenu(LMSFacade facade, Menu pMenu, ArrayList<EnrolledCourse> enrolledCourses) {
        super(facade, pMenu, enrolledCourses, "You are not enrolled in any courses");

        options = new String[]{"Next Course", "Previous Course", "Comments", "Reviews", "Open Modules", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing enrolled course "+(index+1)+" of "+
        list.size()+"***"+"\n\n"
        + "Title: " + get().toString() +"\n"
        + "Language: " + get().getCourse().getLanguage() + "\n"
        + "Description: " + get().getCourse().getDescription()+ "\n"
        + get().getCourseProgress()*100+"% complete\n";
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
                // view comments
                ArrayList<Comment> comments = CourseList.getInstance(null).getCourse(get().getID()).getAllComments();
                facade.setCurrentMenu(new ViewComments(facade, this, comments)).getSelection();
            case 4:
                // view reviews
                ArrayList<Review> reviews = CourseList.getInstance(null).getCourse(get().getID()).getAllReviews();
                facade.setCurrentMenu(new ViewReviews(facade, this, reviews)).getSelection();
            case 5:
                facade.setCurrentMenu(new ViewModules(
                    facade, this, get().getCourse().getAllModules(),
                    get())).getSelection();
            case 6:
                back();
                break;
        }
    }

}
