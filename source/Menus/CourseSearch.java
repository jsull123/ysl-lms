package source.Menus;
import source.CourseList;
import source.LMSFacade;
import source.*;
import java.util.ArrayList;

public class CourseSearch extends Menu {

    public CourseSearch(LMSFacade facade) {
        super(facade, null);
        header = "***Enter a number to choose a course*** \n"+
        "Don't know what to learn? No worries!\n"+
        "Here are some suggestions\n"+
        "\n If you want to learn the most prolific langauge, start with Java\n"+
        "\n If you want to learn the most niche language, start with Python\n"+
        "\n If you want to learn the easiest language, start with C\n"+
        "* means course is already in progress";
        options = new String[]{"Search for Java Courses", "Search for Python Courses", "Search for C Courses", "Back"};
    }

    public void select(int selection){
        switch (selection){
            case 1:
                facade.setCurrentMenu(new EnrollCourseMenu(facade, this, CourseList.getInstance(null).getAllByLanguage("Java")));
            case 2:
                facade.setCurrentMenu(new EnrollCourseMenu(facade, this, CourseList.getInstance(null).getAllByLanguage("Python")));
            case 3:
                facade.setCurrentMenu(new EnrollCourseMenu(facade, this, CourseList.getInstance(null).getAllByLanguage("C")));
            case 4:
                back();
        }
    }
}