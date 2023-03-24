package source.Menus;
import source.Course;
import source.LMSFacade;
import java.util.ArrayList;

public class CreatedCoursesMenu extends View {

    public CreatedCoursesMenu(LMSFacade facade, Menu pMenu, ArrayList<Course> courses) {
        super(facade, pMenu, (ArrayList<?>)courses, "You have not created any courses");

        options = new String[]{"Next Course", "Previous Course", "View Comments", "Modify course", "Back"};
    }

    protected void updateHeader(){
        Course c = (Course)list.get(index);
        header = "***Viewing course "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+c.toString();
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
                facade.setCurrentMenu(new ViewComments(facade, this, ((Course)(list.get(index))).getAllComments(), true)).getSelection();
                break;
            case 4:
                facade.setCurrentMenu(new ModifyCourse(facade, this, (Course)list.get(index))).getSelection();
                break;
            case 5:
                back();
                break;
        }
    }

}