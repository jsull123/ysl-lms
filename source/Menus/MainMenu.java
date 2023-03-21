package source.Menus;
import source.AccountType;
import source.LMSFacade;
import source.User;

public class MainMenu extends Menu{
    User user;
    public MainMenu(LMSFacade facade, User user){
        header = "***Logged in as "+user.getUsername()+"***";
        options = new String[]{"Enrolled courses", "Created courses", "Log out"};
        this.facade = facade;
        this.user = user;
    }

    public void select(int selection){
        switch (selection){
            case 1:
                facade.setCurrentMenu(new EnrolledCoursesMenu(facade, facade.getCurrentMenu(), user.getAllEC()));
                facade.getCurrentMenu().getSelection();
                break;
            case 2:
                if(user.getAccountType().equals(AccountType.AUTHOR)) {
                    facade.setCurrentMenu(new CreatedCoursesMenu(facade, facade.getCurrentMenu(), user.getAllCC()));
                } else {
                    facade.setCurrentMenu(new MainMenu(facade, user));
                    facade.getCurrentMenu().getSelection("Acess denied. Must be an Author.");
                }
                break;
            case 3:
                facade.logOut();
                break;
        }
    }
}
