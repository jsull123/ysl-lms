package source.Menus;
import source.Course;
import source.LMSFacade;
import java.util.ArrayList;

public class CreatedCoursesMenu extends Menu{
    private ArrayList<Course> courses;
    private int courseIndex;
    private Menu prevMenu;
    
    public CreatedCoursesMenu(LMSFacade facade, Menu prevMenu, ArrayList<Course> courses) {
        if (courses.size() == 0){
            facade.setCurrentMenu(prevMenu);
            facade.getCurrentMenu().getSelection("You have not created any courses");
            return;
        }

        this.courses = courses;
        this.facade = facade;
        this.courseIndex = 0;
        this.prevMenu = prevMenu;
        header = courses.get(courseIndex).toString();
        options = new String[]{"Next Course", "Previous Course", "View Comments", "Modify course", "Back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                courseIndex++;
                if (courseIndex >= courses.size() - 1) courseIndex = 0;
                header = courses.get(courseIndex).toString();
                getSelection();
                break;
            case 2:
                if (courseIndex <= 0) {
                    courseIndex = courses.size() - 1;
                }
                else courseIndex--;
                header = courses.get(courseIndex).toString();
                getSelection();
                break;
            case 3:
                facade.setCurrentMenu(new ViewComments(facade, this, courses.get(courseIndex).getAllComments())).getSelection();
                break;
            case 4:
                facade.setCurrentMenu(new ModifyCourse(facade, this, courses.get(courseIndex))).getSelection();
                break;
            case 5:
                facade.setCurrentMenu(prevMenu).getSelection();
                break;
        }
    }

}