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
    /**
     * Constructor for the Facade
     */
    public LMSFacade(){
        userList = UserDataProcessor.loadData();
        courseList = CourseDataProcessor.loadData();

        currentMenu = new WelcomeMenu(this);
        currentMenu.getSelection();
    }
    /**
     * @return The current menu
     */
    public Menu getCurrentMenu(){
        return currentMenu;
    }
    /**
     * @return A new current menu
     * @param Menu menu = The menu to set as the new current menu
     */
    public Menu setCurrentMenu(Menu menu){
        currentMenu = menu;
        return currentMenu;
    }
    /**
     * Logs a user in
     */
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
    /**
     * Creates an account for a new user
     */
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
    /**
     * Exits the user out of the sytem and saves progress
     */
    public void exit(){
        UserDataProcessor.saveData(userList);
        CourseDataProcessor.saveData(courseList);
        System.exit(0);
    }
    /**
     * Logs the user out of the current session
     */
    public void logOut(){
        userList.setCurrentUser(null);
        currentMenu = new WelcomeMenu(this);
        currentMenu.getSelection();
    }
    /**
     * creates and posts a comment from the user
     * @param ArrayList<Comment> comments = The list that the new comment
     * will be added to
     */
    public void makeComment(ArrayList<Comment> comments) {
        comments.add(new Comment(
            userList.getCurrentUser().getID(),
            LMSUI.promptString("Type your comment:", true),
            new Date(),
            new ArrayList<>()
            ));
        currentMenu.getSelection("Comment added successfully");
    }
    /**
     * Set a new module title
     * @param Module module = The module who's title will be changed
     */
    public void setModuleTitle(Module module) {
        module.setTitle(LMSUI.promptString("Enter a new module title: ", true));
        currentMenu.getSelection("Module title changed.");
    }
    /**
     * Set a new module topic
     * @param Module module = The module who's topic will be changed
     */
    public void setModuleTopic(Module module) {
        module.setTopic(LMSUI.promptString("Enter a new module topic: ", true));
        currentMenu.getSelection("Module topic changed.");
    }
    /**
     * Creates a new question
     * @param ArrayList<Question> questions = The list of questions to which
     * The new question will be added
     */
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
    /**
     * Create a new lesson
     * @param ArrayList<String> lessons = The list to which the new
     * lesson will be added
     */
    public void createLesson(ArrayList<String> lessons){
        lessons.add(LMSUI.promptString("Type your new lesson here: ", true));
        currentMenu.getSelection("Lesson added");
    }
    /**
     * Create a new course
     * @param ArrayList<UUID> courses = The list of courses to which
     * the created course will be added
     */
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
    /**
     * Creates a new module
     * @param ArrayList<Module> modules = The list of modules to which
     * the created module will be added
     */
    public void createModule(ArrayList<Module> modules){
        modules.add(new Module(
            LMSUI.promptString("Enter module title: ", true), 
            LMSUI.promptString("Enter module topic: ", false), 
            new ArrayList<>(), 
            new Quiz(new ArrayList<>(), 0),
            new ArrayList<>()));

        currentMenu.getSelection("Module created. Use Modify Lessons and Modify Quiz to add lessons and a quiz to your module");
    }
    /**
     * Sets a new course title
     * @param Course course = The course who's title will be changed
     */
    public void setCourseTitle(Course course){
        course.setTitle(LMSUI.promptString("Enter a new course title:", true));
    }
    /**
     * Sets a new Course Language
     * @param Course course = The course who's language will be set
     */
    public void setCourseLanguage(Course course){
        course.setLanguage(Language.fromString(LMSUI.promptString("Enter a new course language:", true)));
    }
    /**
     * Sets a new Course description
     * @param Course course = The course who's description will be set
     */
    public void setCourseDescription(Course course){
        course.setDescription(LMSUI.promptString("Enter a new course description:", true));
    }
    /**
     * Enroll in a new course
     * @param Course course = The course to be enrolled in
     */
    public void enrollInCourse(Course course){
        // Doesn't check if they are already enrolled in the course
        User user = userList.getCurrentUser();
        user.addEC(new EnrolledCourse(course.getCourseID()));
        currentMenu.getSelection("Enrolled in: " + course.toString());
    }
    /**
     * @return Whether or not the answer to a question is correct
     * @param Question question = The question that is being graded
     */
    public boolean answerQuestion(Question question) {
        return question.isCorrect(LMSUI.promptInt(question.toString(), true));
    }
    /**
     * Creates a new review of a course
     * @param ArrayList<Review> reviews = The list to which the new 
     * review will be added
     */
    public void createReview(ArrayList<Review> reviews){
        reviews.add(new Review(
            userList.getCurrentUser().getID(), 
            LMSUI.promptFloat("How do you rate this course, 0-5?: ", true), 
            LMSUI.promptString("What do you have to say about this course?", false), 
            new Date()));

        currentMenu.getSelection("Review added");
    }
    /**
     * Changes the passing grade of a quiz
     * @param Quiz quiz = The quiz who's passing grade will be changed
     */
    public void changePassingGrade(Quiz quiz){
        float passingGrade = LMSUI.promptFloat("Enter the passing grade:", true);
        if (passingGrade < 0){
            currentMenu.getSelection("Passing grade cannot be less than 0");
        }
        quiz.setPassingGrade(passingGrade/100);
        currentMenu.getSelection("Passing grade set to "+passingGrade+"%");
    }

    /*
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
    }*/
    /**
     * Method that allows the user to take a quiz
     * @param Quiz quiz = The quiz that the user is taking
     * @param ModuleProgress ModuleProgress = The grade that the user passed/failed the quiz
     * 
     */
    public void takeQuiz(Quiz quiz, ModuleProgress ModuleProgress) {
        if (quiz.getQuestions().size() == 0){
            currentMenu.getSelection("This quiz has not been created yet");
        }
        boolean inProgress = true;
        boolean validResponse = false;
        int numQuestions = quiz.getQuestions().size();
        int currentQuestion = 1;
        ArrayList<Question> questions = quiz.getQuestions();
        String userAnswer = "";
        String questionPrompt = "";
        float score = 0;
        int checkAnswerResult;
        String error = "";
        while (inProgress) {
            while (!validResponse) {
                questionPrompt = "Question " + currentQuestion + ": " + "\n" + questions.get(currentQuestion - 1) + error + "\nEnter your answer choice\n";
                userAnswer = LMSUI.promptString(questionPrompt, true).toLowerCase();
                checkAnswerResult = checkAnswer(userAnswer, questions.get(currentQuestion - 1).getCorrectAnswer());
                switch (checkAnswerResult) {
                    case -1:
                        error = "Invalid answer choice\n";
                        break;
                    case 0:
                        validResponse = true;
                        break;
                    case 1:
                        score++;
                        validResponse = true;
                        break;
                }
            }
            currentQuestion++;
            questionPrompt = "";
            error = "";
            validResponse = false;
            if (currentQuestion > numQuestions) inProgress = false;
        }
        ModuleProgress.setQuizGrade(score/numQuestions);
        if (ModuleProgress.getQuizGrade() >= quiz.getPassingGrade()) {
            ModuleProgress.setHasPassed(true);
            currentMenu.getSelection("You have passed this quiz with a score of " + ModuleProgress.getQuizGrade()*100 + "%");
        }
        else {
            ModuleProgress.setHasPassed(false);
            currentMenu.getSelection("Sorry, did not pass this quiz with a score of  " + ModuleProgress.getQuizGrade()*100 + "%");
        }
        
    }
    /**
     * @return A 1 if the user got it correct
     * and a 0 if the user got it wrong
     * @param String userAnswer = The answer the user entered
     * @param int correctAnswer = The correct answer
     */
    public int checkAnswer(String userAnswer, int correctAnswer) {
        switch (userAnswer) {
            case "a":
                if (correctAnswer == 0) return 1;
                return 0;
            case "b":
                if (correctAnswer == 1) return 1;
                return 0;
            case "c":
                if (correctAnswer == 2) return 1;
                return 0;
            case "d":
                if (correctAnswer == 3) return 1;
                return 0;
            default:
                return -1;
        }
    }
    /**
     * @return The certificate the user earned
     * @param Course course = the course that the user earned a
     * certificate for
     */
    public String getCertificate(Course course){
        User user = userList.getCurrentUser();
        Date date = new Date();

        return user.getFirstName()+" "+user.getLastName()+" completed the course "+course.getTitle()+"\n"+date;
    }
    /**
     * Print messgaes to a file
     * @param String string = The message to be added
     * @param String path = The path of the file that gets the message
     */
    public void outputToFile(String string, String path) {
        try {
            File file = new File(path);
            FileWriter writer = new FileWriter(file);
            writer.write(string);
            writer.close();
          } catch (IOException e) {
            currentMenu.getSelection("An error occurred");
            e.printStackTrace();
          }
    }
    /**
     * @return A list of the user's completed courses
     */
    public ArrayList<Course> getCompletedCourses(){
        User currentUser = userList.getCurrentUser();
        ArrayList<Course> completedCourses = new ArrayList<>();
        ArrayList<EnrolledCourse> enrolledCourses = currentUser.getAllEC(); 

        for (int i = 0; i < enrolledCourses.size(); i++){
            if (enrolledCourses.get(i).getCourseProgress() == 1.0f){
                completedCourses.add(enrolledCourses.get(i).getCourse());
            }
        }
        
        return completedCourses;
    }

}