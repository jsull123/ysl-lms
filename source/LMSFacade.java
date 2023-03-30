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
            new Quiz(new ArrayList<>(), 0),
            new ArrayList<>()));

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
        // Doesn't check if they are already enrolled in the course
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

    public String getCertificate(Course course){
        User user = userList.getCurrentUser();
        Date date = new Date();

        return user.getFirstName()+" "+user.getLastName()+" completed the course "+course.getTitle()+"\n"+date;
    }

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