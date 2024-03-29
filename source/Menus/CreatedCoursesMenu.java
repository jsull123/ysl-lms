package source.Menus;
import source.Course;
import source.CourseList;
import source.LMSFacade;
import java.util.ArrayList;
import java.util.UUID;

public class CreatedCoursesMenu extends ListMenu<UUID> {
    /**
     * Constructs a CreatedCourseMenu object
     * @param: LMSFacade facade = object to use Facade methods
     * @param: Menu pMEnu = objecy to use Menu methods and data members
     * @param: ArrayList<UUID> courses: The courses created by a user
     */
    public CreatedCoursesMenu(LMSFacade facade, Menu pMenu, ArrayList<UUID> courses) {
        super(facade, pMenu, courses);   
    }
    /**
     * Gives the user options based on if they've created a course yet or not
     */
    private void updateOptions(){   
        if (list.size() == 0){
            options = new String[]{"Create a Course", "Back"};
        }else{
            options = new String[]{"Next Course", "Previous Course", "Create a Course", "View Comments", "View Reviews", "Modify Course", "Back"};
        }
    }
    /**
     * Displays the options that the user will choose from
     */
    protected void updateHeader(){
        if (list.size() == 0) {
            header = "You have not created any courses. Choose option 1 to create a new course\n";
        }else{
            CourseList cl = CourseList.getInstance(null);
            header = "***Viewing course "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+"Title: " + cl.getCourse(get()).getTitle() +"\n"
            + "Language: " + cl.getCourse(get()).getLanguage() + "\n"
            + "Description: " + cl.getCourse(get()).getDescription()+ "\n";
        } 
        updateOptions();
    }
    /**
     * Gives the user options to move through this screen of the LMS
     * @param: int selection = The choice the user has made
     */
    public void select(int selection) {
        if (list.size() == 0){
            switch(selection){
                case 1:
                    facade.createCourse(list);
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
                facade.createCourse(list);
            case 4:
                facade.setCurrentMenu(new ViewComments(facade, this, CourseList.getInstance(null).getCourse(get()).getAllComments())).getSelection();
                break;
            case 5:
                facade.setCurrentMenu(new ViewReviews(facade, this, CourseList.getInstance(null).getCourse(get()).getAllReviews())).getSelection();
                break;
            case 6:
                facade.setCurrentMenu(new ModifyCourse(facade, this, CourseList.getInstance(null).getCourse(get()))).getSelection();
                break;
            case 7:
                back();
                break;
        }
    }

}