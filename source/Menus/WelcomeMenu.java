package source.Menus;
import source.LMSFacade;

public class WelcomeMenu extends Menu {

    public WelcomeMenu(LMSFacade facade){
        super(facade, null);
        header = "***Welcome to the YSL programming LMS***";
        options = new String[]{"Log In", "Sign Up", "Exit"};
    }

    public void select(int selection){
        switch (selection){
            case 1:
                facade.logIn();
                break;
            case 2:
                facade.createAccount();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }
}
