package source.Menus;
import source.CourseList;
import source.LMSFacade;
import source.*;
import java.util.ArrayList;

public class CourseSearch extends Menu {

    public CourseSearch(LMSFacade facade, Menu pMenu) {
        super(facade, pMenu);
        header = "***Enter a number to choose a course*** \n\n"+
        "Don't know what to learn? No worries!\n"+
        "Here are some suggestions\n"+
        "\nIf you want to learn the most prolific langauge, start with Java\n"+
        "\nIf you want to learn the most niche language, start with Python\n"+
        "\nIf you want to learn the easiest language, start with C\n";
        options = new String[]{"Search for Java Courses", "Search for Python Courses", "Search for C Courses", "Back"};
    }

    public void select(int selection){
        switch (selection){
            case 1:
                facade.setCurrentMenu(new EnrollCourseMenu(facade, this, CourseList.getInstance(null).getAllByLanguage("Java"))).getSelection();
            case 2:
                facade.setCurrentMenu(new EnrollCourseMenu(facade, this, CourseList.getInstance(null).getAllByLanguage("Python"))).getSelection();
            case 3:
                facade.setCurrentMenu(new EnrollCourseMenu(facade, this, CourseList.getInstance(null).getAllByLanguage("C"))).getSelection();
            case 4:
                back();
        }
    }
}