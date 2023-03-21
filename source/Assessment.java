public class Assessment {
     private String title;
     private ArrayList<Question> questions;
     private float passingGrade;

     public Assessment(String title, ArrayList<Question> questions, float passingGrade) {
        this.title = title;
        this.questions = questions
        this.passingGrade = passingGrade;
     }

     public boolean take() {
        return true;
     }
}