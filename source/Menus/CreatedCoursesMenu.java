package source.Menus;
import source.Course;
import source.LMSFacade;
import java.util.ArrayList;

public class CreatedCoursesMenu extends ListMenu<Course> {

    public CreatedCoursesMenu(LMSFacade facade, Menu pMenu, ArrayList<Course> courses) {
        super(facade, pMenu, courses);
        if (courses.size() == 0){
            options = new String[]{"Create a Course", "Back"};
        }else{
            options = new String[]{"Next Course", "Previous Course", "Create a Course", "View Comments", "View Reviews", "Modify Course", "Back"};
        }
    }

    protected void updateHeader(){
        if (list.size() == 0) {
            header = "You have not created any courses. Choose option 1 to create a new course\n";
        }else{
            header = "***Viewing course "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+get().toString()+"\n";
        }    
    }

    public void select(int selection) {
        if (list.size() == 0){
            switch(selection){
                case 1:
                    facade.createNewCourse(list);
                case 2:
                    back();
            }
        }
        switch(selection) {
            case 1:
                next();
                break;
            case 2:
                prev();
                break;
            case 3:
                facade.createNewCourse(list);
            case 4:
                facade.setCurrentMenu(new ViewComments(facade, this, get().getAllComments())).getSelection();
                break;
            case 5:
                facade.setCurrentMenu(new ViewReviews(facade, this, get().getAllReviews())).getSelection();
                break;
            case 6:
                facade.setCurrentMenu(new ModifyCourse(facade, this, get())).getSelection();
                break;
            case 7:
                back();
                break;
        }
    }

}