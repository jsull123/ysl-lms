package source.Menus;
import source.LMSFacade;

public class WelcomeMenu extends Menu {

    public WelcomeMenu(LMSFacade facade){
        header = "***Welcome to the YSL programming LMS***";
        options = new String[]{"Log In", "Sign Up", "Exit"};
        error = "";
        this.facade = facade;
    }

    public void select(int selection){
        switch (selection){
            case 1:
                facade.logIn();
            case 2:
                // facade.createAccount();
            case 3:
                // facade.exit();
        }
    }
}
