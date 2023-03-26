package source.Menus;
import source.Course;
import source.LMSFacade;
import java.util.ArrayList;

public class CreatedCoursesMenu extends ListMenu<Course> {

    public CreatedCoursesMenu(LMSFacade facade, Menu pMenu, ArrayList<Course> courses) {
        super(facade, pMenu, courses, "You have not created any courses");

        options = new String[]{"Next Course", "Previous Course", "View Comments", "View Reviews", "Modify course", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing course "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString()+"\n";
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
                facade.setCurrentMenu(new ViewComments(facade, this, get().getAllComments())).getSelection();
                break;
            case 4:
                facade.setCurrentMenu(new ViewReviews(facade, this, get().getAllReviews())).getSelection();
                break;
            case 5:
                facade.setCurrentMenu(new ModifyCourse(facade, this, get())).getSelection();
                break;
            case 6:
                back();
                break;
        }
    }

}