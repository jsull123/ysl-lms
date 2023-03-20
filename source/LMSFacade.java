package source;

import java.util.Scanner;
import java.util.UUID;

import source.Menus.MainMenu;
import source.Menus.Menu;
import source.Menus.WelcomeMenu;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LMSFacade{
    private UserList userList;
    private CourseList courseList;
    private Menu currentMenu;
    private LMSUI lmsui;

    private User user;
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
      //  courseList = CourseDataProcessor.loadData();

        currentMenu = new WelcomeMenu(this);
        currentMenu.getSelection();
    }

    public Menu getCurrentMenu(){
        return currentMenu;
    }

    public void setCurrentMenu(Menu menu){
        currentMenu = menu;
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

    public void login(String password, User user) {
        if (password.equals(user.getPassword())){
            clearScreen();
            System.out.println("Log in success!");
        clearScreen();
        } else { 
            System.out.println("Log in filed. Try again")
        }
    }

    public void displaySignInOptions() {
        System.out.println("***Welcome to the YSL programming LMS***");
        System.out.println("***Enter a number to choose an option***");
        System.out.println("1. Sign up as student");
        System.out.println("2. Sign up as author");
        System.out.println("3. Sign in");
    }

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
    public Comment makeComment() {
        clearScreen();
        System.out.println("Please type your comment below:");
        Scanner keyboard = new Scanner(System.in);
        String comment = keyboard.nextLine();
        UUID uuid = UUID.randomUUID();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        ArrayList<Comment> replies = new ArrayList<Comment>();
        return new Comment(uuid, comment, date, replies);
    }

}