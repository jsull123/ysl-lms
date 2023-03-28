package source.Menus;
import source.LMSFacade;
import source.CourseList;
import source.EnrolledCourse;
import java.util.ArrayList;

public class EnrolledCoursesMenu extends ListMenu<EnrolledCourse> {

    public EnrolledCoursesMenu(LMSFacade facade, Menu pMenu, ArrayList<EnrolledCourse> enrolledCourses) {
        super(facade, pMenu, enrolledCourses, "You are not enrolled in any courses");

        options = new String[]{"Next Course", "Previous Course", "Add Comment", "Add Review", "View Modules", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing enrolled course "+(index+1)+" of "+
        list.size()+"***"+"\n\n"
        + "Title: " + get().toString() +"\n"
        + "Language: " + get().getCourse().getLanguage() + "\n"
        + "Description: " + get().getCourse().getDescription()+ "\n"
        + get().getCourseProgress()*100+"% complete\n";
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
                facade.makeComment(CourseList.getInstance(null).getCourse(get().getID()).getAllComments());
            case 4:
                // add review
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
