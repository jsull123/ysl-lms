

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
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("Log in success!");
        System.out.println("\033[H\033[2J");
        System.out.flush();
    } else { 
        System.out.println("Log in filed. Try again")
    }
}

public void displaySignInOptions() {
    System.out.println("1. Sign up as student");
    System.out.println("2. Sign up as author");
    System.out.println("3. Sign in");
}