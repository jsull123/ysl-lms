package source.Menus;
import source.LMSFacade;
import source.LMSUI;

public class SearchCourseMenu extends Menu {

    public SearchCourseMenu(LMSFacade facade){
        header = "Search a course by its language:";
        options = new String[]{"Java", "C", "Python"};
        this.facade = facade;
    }
    
    public void select(int selection){
        switch (selection){
            case 1:
                
        }
    }
}