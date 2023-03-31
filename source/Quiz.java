package source;
import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;
    private float passingGrade;
    /**
     * Constructor for a Quiz object
     * @param ArrayList<Question> questions = A list of questions
     * on the quiz
     * @param float passingGrade = The lowest allowable grade a user can make
     * and still pass the quiz 
     */
    public Quiz(ArrayList<Question> questions, float passingGrade){
        this.questions = questions;
        this.passingGrade = passingGrade;
    }
    /**
     * @return The list of questions on the quiz
     */
    public ArrayList<Question> getQuestions(){
        return questions;
    }
    /**
     * @return The minimum passing grade
     */
    public float getPassingGrade(){
        return passingGrade;
    }
    /**
     * Sets a new minimum passing grade
     * @param float passingGrade = The new passing grade to be set
     */
    public void setPassingGrade(float passingGrade){
        this.passingGrade = passingGrade; 
        if (passingGrade < 0 || passingGrade > 1){
            this.passingGrade = 0;
        }
    }
}
