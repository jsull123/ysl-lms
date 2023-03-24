package source.Menus;
import source.LMSFacade;
import source.CourseList;
import source.EnrolledCourse;
import java.util.ArrayList;

public class EnrolledCoursesMenu extends ListMenu {

    public EnrolledCoursesMenu(LMSFacade facade, Menu pMenu, ArrayList<EnrolledCourse> enrolledCourses) {
        super(facade, pMenu, (ArrayList<?>)enrolledCourses, "You are not enrolled in any courses");

        options = new String[]{"Next Course", "Previous Course", "View Comments", "View Modules", "Back"};
    }

    protected void updateHeader(){
        EnrolledCourse ec = (EnrolledCourse)list.get(index);
        header = "***Viewing enrolled course "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+ec.toString();
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
                facade.setCurrentMenu(new ViewComments(facade, this,
                  CourseList.getInstance(null).getCourse(
                    ((EnrolledCourse)list.get(index)).getID()).getAllComments(), true)).getSelection();
                break;
            case 4:
                facade.setCurrentMenu(new ViewModules(
                    facade, pMenu, ((EnrolledCourse)(list.get(index))).getCourse().getAllModules(),
                    (EnrolledCourse)list.get(index))).getSelection();
            case 5:
                back();
                break;
        }
    }

}
