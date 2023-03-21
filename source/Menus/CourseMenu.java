package source.Menus;
import source.LMSFacade;
import source.LMSUI;

public class CourseMenu extends Menu {

    public CourseMenu(LMSFacade facade) {
        header = "***Enter a number to choose a course*** \n"+
        "Don't know what to learn? No worries!\n"+
        "Here are some suggestions\n"+
        "\n If you want to learn the most prolific langauge, start with Java\n"+
        "\n If you want to learn the most niche language, start with Python\n"+
        "\n If you want to learn the easiest language, start with C\n"+
        "* mean course is already in progress";
        options = new String[]{"Enroll in a Java course",
        "Enroll in Python", "Enroll in C course"};
        this.facade = facade;
    }

    public void select(int selection){
        switch (selection){
            case 1:
                facade.enrollInJava();
            case 2:
                facade.enrollInPython();
            case 3:
                facade.enrollInC();
            case 4:
                facade.exit();
        }
    }
}