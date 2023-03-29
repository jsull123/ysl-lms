package source.Menus;
import source.AccountType;
import source.LMSFacade;
import source.User;

public class MainMenu extends Menu{
    private User user;

    public MainMenu(LMSFacade facade, User user){
        super(facade, null);
        header = "***Logged in as "+user.getUsername()+"***";
        options = new String[]{"My Enrolled Courses", "My Created Courses", "Search For Courses", "My Certificates", "Log out"};
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
                // Search for courses
            case 4:
                // Certificates?
            case 5:
                facade.logOut();
                break;
        }
    }
}
