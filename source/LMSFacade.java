package source;

import java.util.Scanner;
import java.util.UUID;

import source.Menus.*;

import java.util.ArrayList;

public class LMSFacade{
    private UserList userList;
    private CourseList courseList;
    private Menu currentMenu;
    
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

    public void exit(){
        UserDataProcessor.saveData(userList);
        CourseDataProcessor.saveData(courseList);
        System.exit(0);
    }

    public void logOut(){
        userList.setCurrentUser(null);
        currentMenu = new WelcomeMenu(this);
        currentMenu.getSelection();
    }
    
    public void makeComment(ArrayList<Comment> comments) {
        comments.add(new Comment(
            userList.getCurrentUser().getID(),
            LMSUI.promptString("Type your comment:", true),
            new Date(),
            new ArrayList<>()
            ));
        currentMenu.getSelection("Comment added successfully");
    }

    public void setModuleTitle(Module module) {
        module.setTitle(LMSUI.promptString("Enter a new module title: ", true));
        currentMenu.getSelection("Module title changed.");
    }

    public void setModuleTopic(Module module) {
        module.setTopic(LMSUI.promptString("Enter a new module topic: ", true));
        currentMenu.getSelection("Module topic changed.");
    }

    public void setContentLesson(Content content) {
        content.setLesson(LMSUI.promptString("Enter a new lesson name: ", true));
        currentMenu.getSelection("Content lesson information changed.");
    }

    public void setContentTitle(Content content) {
        content.setTitle(LMSUI.promptString("Enter a new title: ", true));
        currentMenu.getSelection("Content title changed.");
    }

    public void setPassingGrade(Content content) {
        content.setPassingGrade(LMSUI.promptFloat("Enter a new passing grade: ", true));
        currentMenu.getSelection("Content passing grade changed.");
    }

    public void createQuestion(ArrayList<Question> questions){
        String question = LMSUI.promptString("Enter the questions question:", true);
        ArrayList<String> answerChoices = new ArrayList<>();
        answerChoices.add(LMSUI.promptString("Enter answer choice A:", false));
        answerChoices.add(LMSUI.promptString("Enter answer choice B:", false));
        answerChoices.add(LMSUI.promptString("Enter answer choice C:", false));
        answerChoices.add(LMSUI.promptString("Enter answer choice D:", false));
        String correctStr = LMSUI.promptString("Which is the correct choice (A,B,C,D)?:", false);
        int correct = -1;
        if (correctStr.toUpperCase() == "A") correct = 0;
        if (correctStr.toUpperCase() == "B") correct = 1;
        if (correctStr.toUpperCase() == "C") correct = 2;
        if (correctStr.toUpperCase() == "D") correct = 3;
        
        if (correct == -1) currentMenu.getSelection("Invalid choice! Must be A, B, C, or D");

        questions.add(new Question(question, answerChoices, correct));

        currentMenu.getSelection("Question added");
    }

    public void createCourse(ArrayList<Course> courses){
        courses.add(new Course(UUID.randomUUID(),
        LMSUI.promptString("Enter course title:", true), 
        LMSUI.promptString("Enter course language:", false),
        LMSUI.promptString("Enter course description:", false),
        userList.getCurrentUser().getID(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>()));

        currentMenu.getSelection("Course created");
    }

    public void createModule(ArrayList<Module> modules){
        modules.add(new Module(
            LMSUI.promptString("Enter a title:", true),
            LMSUI.promptString("Enter a topic:", false), 
            new ArrayList<>()));
        currentMenu.getSelection("Module added");
    }

    public void setCourseTitle(Course course){
        course.setTitle(LMSUI.promptString("Enter a new course title:", true));
        currentMenu.getSelection("Title changed.");
    }

    public void setCourseLanguage(Course course){
        course.setLanguage(LMSUI.promptString("Enter a new course language:", true));
        currentMenu.getSelection("Language changed");
    }

    public void setCourseDescription(Course course){
        course.setDescription(LMSUI.promptString("Enter a new course description:", true));
        currentMenu.getSelection("Description changed.");
    }

    public void enrollInCourse(Course course){
        User user = userList.getCurrentUser();
        user.addEC(new EnrolledCourse(course.getCourseID()));
        currentMenu.getSelection("Enrolled in: " + course.toString());
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

}