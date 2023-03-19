package source;

import java.util.Scanner;
import java.util.UUID;

import source.Menus.MainMenu;
import source.Menus.Menu;
import source.Menus.WelcomeMenu;

import java.util.ArrayList;

public class LMSFacade{
    private UserList userList;
    private CourseList courseList;
    private Menu currentMenu;

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
        currentMenu.select(currentMenu.getSelection());
    }

    public void logOut(){
        userList.setCurrentUser(null);
        currentMenu = new WelcomeMenu(this);
        currentMenu.select(currentMenu.getSelection());
    }
    
    public void logIn(){
        String username = LMSUI.getUsername();
        String password = LMSUI.getPassword();

        User user = userList.getUser(username);
        if (user == null){
            currentMenu.select(currentMenu.getSelection("User "+username+" doesn't exist!"));
            return;
        }else if (!user.getPassword().equals(password)){
            currentMenu.select(currentMenu.getSelection("Incorrect password!"));
            return;
        }
        userList.setCurrentUser(user);
        currentMenu = new MainMenu(this, user);
        currentMenu.select(currentMenu.getSelection());
    }
    /* 
    public void login(String password, User user) {
        if ( passoword.equals(user.getPassword)){
            clearScreen();
            System.out.println("Log in success!");
           clearScreen();
        } else { 
            System.out.println("Log in filed. Try again")
        }
    }
    
    public void displaySignInOptions() {
        System.out.println("***Welcome to the YSL programming LMS***")
        System.out.println("1. Sign up as student");
        System.out.println("2. Sign up as author");
        System.out.println("3. Sign in");
    }
    
    public void makeComment() {
        clearScreen();
        System.out.println("Please type your comment below:");
        Scanner keyboard = new Scanner(System.in);
        String comment = keyboard.nextLine();
        UUID uuid = UUID.randomUUID();
    }
    
    public void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    */
    
}
