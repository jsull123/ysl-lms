package source;
import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;
    private float passingGrade;

    public Quiz(ArrayList<Question> questions, float passingGrade){
        this.questions = questions;
        this.passingGrade = passingGrade;
    }

    public ArrayList<Question> getQuestions(){
        return questions;
    }

    public float getPassingGrade(){
        return passingGrade;
    }

    public void setPassingGrade(float passingGrade){
        this.passingGrade = passingGrade; 
        if (passingGrade < 0 || passingGrade > 1){
            this.passingGrade = 0;
        }
    }
}
