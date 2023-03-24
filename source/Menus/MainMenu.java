package source.Menus;
import source.AccountType;
import source.LMSFacade;
import source.User;

public class MainMenu extends Menu{
    private User user;

    public MainMenu(LMSFacade facade, User user){
        super(facade, null);
        header = "***Logged in as "+user.getUsername()+"***";
        options = new String[]{"Enrolled courses", "Created courses", "Log out"};
        this.user = user;
    }

    public void select(int selection){
        switch (selection){
            case 1:
                facade.setCurrentMenu(new EnrolledCoursesMenu(facade, this, user.getAllEC())).getSelection();
                break;
            case 2:
                if(user.getAccountType().equals(AccountType.AUTHOR)) {
                    facade.setCurrentMenu(new CreatedCoursesMenu(facade, facade.getCurrentMenu(), user.getAllCreatedCourses())).getSelection();
                } else {
                    getSelection("Access denied. Must be an Author.");
                }
                break;
            case 3:
                facade.logOut();
                break;
        }
    }
}
