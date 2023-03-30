package source.Menus;
import source.LMSFacade;

public class WelcomeMenu extends Menu {
    /**
     * Constructs a WelcomeMenu object
     * @param: LMSFacade facade = object to use the Facade methods
     */
    public WelcomeMenu(LMSFacade facade){
        super(facade, null);
        header = "***Welcome to the YSL programming LMS***";
        options = new String[]{"Log In", "Sign Up", "Exit"};
    }
     /**
     * Gives the user options to move through this screen of the LMS
     * @param: int selection = The choice that the user has made
     */
    public void select(int selection){
        switch (selection){
            case 1:
                facade.logIn();
                break;
            case 2:
                facade.createAccount();
                break;
            case 3:
                facade.exit();
                break;
        }
    }
}
