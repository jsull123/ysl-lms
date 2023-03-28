package source;

import java.util.ArrayList;

public class Question {
    public String question;
    public ArrayList<String> answers;
    public int correctAnswer;

    public Question(String question, ArrayList<String> answers, int correctAnswer){
        this.question = question;
        this.answers= answers;
        this.correctAnswer = correctAnswer;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setCorrectAnswer(int correctAnswer){
        this.correctAnswer = correctAnswer;
    }

    public String toString(){
        String ret = ""+question+"\n";
        for(int i = 0; i < answers.size(); i++) {
            ret += ""+i+" "+answers.get(i)+"\n";
        }
        return ret;
    }

    public int getCorrectAnswer(){
        return correctAnswer;
    }

    public ArrayList<String> getAnswers(){
        return answers;
    }

    public String getQuestion(){
        return question;
    }

    public boolean isCorrect(int answer){
        return answer == correctAnswer;
    }
}
