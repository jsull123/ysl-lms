import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;

private User user;
private EnrolledCourse currentlyEnrolledCourse;
private UserDataProcessor userProcessor;
private CourseDataProcessor courseProcessor;
private Question question;
private Lesson lesson;
private Assessment assessment;
private Module module;
private Course course;
private Review review;
private Comment comment;

public LMSFacade(){

}

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

