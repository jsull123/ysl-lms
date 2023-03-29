package source;

import java.util.Scanner;
import java.util.UUID;

import source.Menus.*;

import java.util.ArrayList;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

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

    public void createQuestion(ArrayList<Question> questions){
        String question = LMSUI.promptString("Enter the questions question:", true);
        ArrayList<String> answerChoices = new ArrayList<>();
        answerChoices.add(LMSUI.promptString("Enter answer choice A:", false));
        answerChoices.add(LMSUI.promptString("Enter answer choice B:", false));
        answerChoices.add(LMSUI.promptString("Enter answer choice C:", false));
        answerChoices.add(LMSUI.promptString("Enter answer choice D:", false));
        String correctStr = LMSUI.promptString("Which is the correct choice. A, B, C, or D?:", false);
        int correct = -1;
        if (correctStr.toUpperCase().equals("A")) correct = 0;
        if (correctStr.toUpperCase().equals("B")) correct = 1;
        if (correctStr.toUpperCase().equals("C")) correct = 2;
        if (correctStr.toUpperCase().equals("D")) correct = 3;
        
        if (correct == -1) currentMenu.getSelection("Invalid choice! Must be A, B, C, or D");

        questions.add(new Question(question, answerChoices, correct));

        currentMenu.getSelection("Question added");
    }

    public void createLesson(ArrayList<String> lessons){
        lessons.add(LMSUI.promptString("Type your new lesson here: ", true));
        currentMenu.getSelection("Lesson added");
    }

    public void createCourse(ArrayList<UUID> courses){
        Course course = new Course(UUID.randomUUID(),
        LMSUI.promptString("Enter course title:", true), 
        Language.fromString(LMSUI.promptString("Enter course language:", false)),
        LMSUI.promptString("Enter course description:", false),
        userList.getCurrentUser().getID(),
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>());

        courses.add(course.getCourseID());
        courseList.addCourse(course);

        currentMenu.getSelection("Course created. Use Modify Course to add content to your course");
    }

    public void createModule(ArrayList<Module> modules){
        modules.add(new Module(
            LMSUI.promptString("Enter module title: ", true), 
            LMSUI.promptString("Enter module topic: ", false), 
            new ArrayList<>(), 
            new Quiz(new ArrayList<>(), 0)));
        currentMenu.getSelection("Module created. Use Modify Lessons and Modify Quiz to add lessons and a quiz to your module");
    }

    public void setCourseTitle(Course course){
        course.setTitle(LMSUI.promptString("Enter a new course title:", true));
    }

    public void setCourseLanguage(Course course){
        course.setLanguage(Language.fromString(LMSUI.promptString("Enter a new course language:", true)));
    }

    public void setCourseDescription(Course course){
        course.setDescription(LMSUI.promptString("Enter a new course description:", true));
    }

    public void enrollInCourse(Course course){
        User user = userList.getCurrentUser();
        user.addEC(new EnrolledCourse(course.getCourseID()));
        currentMenu.getSelection("Enrolled in: " + course.toString());
    }

    public boolean answerQuestion(Question question) {
        return question.isCorrect(LMSUI.promptInt(question.toString(), true));
    }
    
    public void createReview(ArrayList<Review> reviews){
        reviews.add(new Review(
            userList.getCurrentUser().getID(), 
            LMSUI.promptFloat("How do you rate this course, 0-5?: ", true), 
            LMSUI.promptString("What do you have to say about this course?", false), 
            new Date()));

        currentMenu.getSelection("Review added");
    }

    public void changePassingGrade(Quiz quiz){
        float passingGrade = LMSUI.promptFloat("Enter the passing grade:", true);
        if (passingGrade < 0){
            currentMenu.getSelection("Passing grade cannot be less than 0");
        }
        quiz.setPassingGrade(passingGrade/100);
        currentMenu.getSelection("Passing grade set to "+passingGrade+"%");
    }

    public void printCertificate(User user, EnrolledCourse enrolledCourse) {
        Course course = enrolledCourse.getCourse();
        try {
            File file = new File(""+course.getTitle()+"Certificate.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(user.getFirstName()+" "+user.getLastName()+" completed the course "+course.getTitle());
            writer.close();
            currentMenu.getSelection("Successfully created certificate");
          } catch (IOException e) {
            currentMenu.getSelection("An error occurred");
            e.printStackTrace();
          }
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