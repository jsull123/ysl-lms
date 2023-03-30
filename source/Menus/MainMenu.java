package source.Menus;
import source.AccountType;
import source.LMSFacade;
import source.User;

public class MainMenu extends Menu{
    private User user;
    /**
     * Constructs a MainMenu object
     * @param: LMSFacade facade = object to use the Facade methods
     * @param: User user: The current user
     */
    public MainMenu(LMSFacade facade, User user){
        super(facade, null);
        header = "***Logged in as "+user.getUsername()+"***";
        options = new String[]{"My Enrolled Courses", "My Created Courses", "Search For Courses", "My Certificates", "Log out"};
        this.user = user;
    }
    /**
     * Gives the user options to move through this screen of the LMS
     * @param: int selection = The choice that the user has made
     */
    public void select(int selection){
        switch (selection){
            case 1:
                facade.setCurrentMenu(new EnrolledCoursesMenu(facade, this, user.getAllEC())).getSelection();
                break;
            case 2:
                if(user.getAccountType().equals(AccountType.AUTHOR)) {
                    facade.setCurrentMenu(new CreatedCoursesMenu(facade, this, user.getAllCreatedCourses())).getSelection();
                } else {
                    getSelection("Access denied. Must be an Author.");
                }
                break;
            case 3:
                facade.setCurrentMenu(new CourseSearch(facade, this)).getSelection();;
            case 4:
                facade.setCurrentMenu(new CertificateMenu(facade, this, facade.getCompletedCourses())).getSelection();
            case 5:
                facade.logOut();
                break;
        }
    }
}
