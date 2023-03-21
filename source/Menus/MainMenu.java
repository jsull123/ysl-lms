package source.Menus;
import source.LMSFacade;
import source.User;

public class MainMenu extends Menu{
    
    public MainMenu(LMSFacade facade, User user){
        header = "***Logged in as "+user.getUsername()+"***";
        options = new String[]{"My registered courses", "My created courses","Other stuff", "Log out"};
        this.facade = facade;
    }

    public void select(int selection){
        switch (selection){
            case 1:
                //facade.logIn();
                break;
            case 2:
                // facade.createAccount();
                break;
            case 3:
                facade.logOut();
                break;
        }
    }
}
