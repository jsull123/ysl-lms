package source;

public class Question {
    public String question;
    public ArrayList<String> answers;
    public int correctAnswer;

    public Question(String question, ArrayList<String> answers, int correctAnswer){
        this.question = question;
        this.answers= answers;
        this.correctAnswer = correctAnswer;
    }

    public String toString(){
        return "";
    }

    public boolean isCorrect(int answer){
        return answer == correctAnswer;
    }
}
