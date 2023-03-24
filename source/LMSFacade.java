package source;

import java.util.Scanner;
import java.util.UUID;

import source.Menus.*;

import java.util.ArrayList;


public class LMSFacade{
    private UserList userList;
    private CourseList courseList;
    private Menu currentMenu;

    private EnrolledCourse currentlyEnrolledCourse;
    private UserDataProcessor userProcessor;
    private CourseDataProcessor courseProcessor;
    private Question question;
    //private Lesson lesson;
    //private Assessment assessment;
    private Module module;
    private Course course;
    private Review review;
    private Comment comment;
    
    public LMSFacade(){
        userList = UserDataProcessor.loadData();
        courseList = CourseDataProcessor.loadData();

        currentMenu = new WelcomeMenu(this);
        currentMenu.getSelection();
    }

    public Menu getCurrentMenu(){
        return currentMenu;
    }

    public Menu setCurrentMenu(Menu menu){
        currentMenu = menu;
        return currentMenu;
    }

    public void logOut(){
        userList.setCurrentUser(null);
        currentMenu = new WelcomeMenu(this);
        currentMenu.getSelection();
    }
    
    public void logIn(){
        // Get username
        String username = LMSUI.promptString("Enter your username: ", true);

        // Check if username exists
        User user = userList.getUser(username);
        if (user == null){
            // Return to menu selection screen with the error message "User (entered name) doesn't exist!"
            currentMenu.getSelection("User "+username+" doesn't exist!");
            return;
        }
        
        // Get password
        String password = LMSUI.promptString("Enter your password: ", false);

        // Check if password is correct
        if (!user.getPassword().equals(password)){
            // Return to menu selection screen with the error message "Incorrect password!"
            currentMenu.getSelection("Incorrect password!");
            return;
        }

        // If information was correct, set current user and activate new menu for user options
        userList.setCurrentUser(user);
        currentMenu = new MainMenu(this, user);
        currentMenu.getSelection();
    }

    public void createAccount() {
        LMSUI.clearScreen();
        int accountType = LMSUI.promptInt(
            "What kind of account would you like to create?\n"+
            "1. Author\n"+
            "2. Student\n"+
            "Enter a number to choose", true);

        if (accountType != 1 && accountType != 2){
            currentMenu.getSelection("Invalid choice");
            return;
        }

        String firstName = LMSUI.promptString("Enter your first name:", false);
        String lastName = LMSUI.promptString("Enter your last name:", false);
        String username = LMSUI.promptString("Enter your username \n(this is how other users will identify you):", false);

        if (userList.getUser(username) != null){
            currentMenu.getSelection("Username \""+username+"\" is taken");
            return;
        }

        String email = LMSUI.promptString("Enter your email:", false);
        String password = LMSUI.promptString("Choose a password:", false);
        String dob = LMSUI.promptString("Enter your date of birth:", false);

        User user = new User(
            UUID.randomUUID(),
            AccountType.fromInt(accountType),
            firstName,
            lastName,
            username,
            email,
            password,
            Date.fromString(dob),
            new ArrayList<>(),
            new ArrayList<>()
        );

        userList.addUser(user);
        userList.setCurrentUser(user);
        UserDataProcessor.saveData(userList);
        currentMenu = new MainMenu(this, user);
        currentMenu.getSelection();
    }

    // Gets the users comment and adds it to the list passed. Saves the data
    public void makeComment(ArrayList<Comment> comments) {
        LMSUI.clearScreen();
        System.out.println("Please type your comment below:");
        Scanner keyboard = new Scanner(System.in);
        String comment = keyboard.nextLine();
        Date date = new Date();
        ArrayList<Comment> replies = new ArrayList<Comment>();
        comments.add(new Comment(userList.getCurrentUser().getID(), comment, date, replies));
        CourseDataProcessor.saveData(CourseList.getInstance(null));
        currentMenu.getSelection("Comment added successfully");
    }
    
    public void addModule(ArrayList<Module> modules) {
        System.out.println("What is the title of the module");
        Scanner keyboard = new Scanner(System.in);
        String title = keyboard.nextLine();
        System.out.println("What is the topic");
        String topic = keyboard.nextLine();
        ArrayList<Content> content = new ArrayList<Content>();
        modules.add(new Module(title, topic, content));
        keyboard.close();
    }

    public void setModuleTitle(Module module) {
        module.setTitle(LMSUI.promptString("Enter a new module title: ", true));
    }

    public void setModuleTopic(Module module) {
        module.setTopic(LMSUI.promptString("Enter a new module topic: ", true));
    }

    public void setContentLesson(Content content) {
        content.setLesson(LMSUI.promptString("Enter a new lesson name: ", true));
    }

    public void setContentTitle(Content content) {
        content.setTitle(LMSUI.promptString("Enter a new title: ", true));
    }

    public void setContentType(Content content) {
        content.setContentType(LMSUI.promptString("Enter a new content type: ", true));
    }
/* 
    public void displaySignInOptions() {
        System.out.println("***Welcome to the YSL programming LMS***");
        System.out.println("***Enter a number to choose an option***");
        System.out.println("1. Sign up as student");
        System.out.println("2. Sign up as author");
        System.out.println("3. Sign in");
    }
    */
    public void displayPathOptions() {
        System.out.println("***Enter a number into the console to choose a path***");
        System.out.println("");
        System.out.println("1. Enroll in a new course");
        System.out.println("2. Resume a course");
        System.out.println("3. Search for a course");
        System.out.println("4. Review a course");
        System.out.println("5. Get certified to create a course");
        System.out.println("6. Go Back");
    }
/* 
    public void displayNewCourseOptions() {
        System.out.println("Are you ready to learn? Look no further");
        System.out.println("Here are some option to start with based on attributes of each langauge");
        System.out.println("");
        System.out.println("If you want to learn the most prolific langauge, start with Java");
        System.out.println("");
        System.out.println("If you want to learn the most niche language, start with Python");
        System.out.println("");
        System.out.println("If you want to learn the easiest language, start with C");
        System.out.println("");
        System.out.println("***Enter a number to choose an option***");
        System.out.println("* mean course is already in progress");
        System.out.println("");
        System.out.println("1. Enroll in Java course");
        System.out.println("2. Enroll in Python course");
        System.out.println("3. Enroll in C course");
        System.out.println("4. Go back");
    }
*/
    public void enrollInJava(Course course){
        User user = UserList getCurrentUser();
        EnrolledCourse Ecourse = new EnrolledCourse(course.getCourseID());
        user.addEC(Ecourse);
    }
    public void enrollInPython(Course course){
        User user = UserList getCurrentUser();
        EnrolledCourse Ecourse = new EnrolledCourse(course.getCourseID());
        user.addEC(Ecourse);
    }
    public void enrollInC(Course course){
        User user = UserList getCurrentUser();
        EnrolledCourse Ecourse = new EnrolledCourse(course.getCourseID());
        user.addEC(Ecourse);
    }

}