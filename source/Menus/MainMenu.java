package source.Menus;
import source.LMSFacade;
import source.User;

public class MainMenu extends Menu{
    
    public MainMenu(LMSFacade facade, User user){
        header = "***Logged in as "+user.getUsername()+"***";
        options = new String[]{"My courses", "Other stuff1", "Log out"};
        error = "";
        this.facade = facade;
    }

    public void select(int selection){
        switch (selection){
            case 1:
                //facade.logIn();
            case 2:
                // facade.createAccount();
            case 3:
                 facade.logOut();
        }
    }
}
