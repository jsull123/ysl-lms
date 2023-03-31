package source;

import java.util.ArrayList;

public class Question {
    public String question;
    public ArrayList<String> answers;
    public int correctAnswer;
    /**
     * Constructor for a Question object
     * @param String question = The question being worked with
     * @param ArrayList<String> answers = The answer choices to the question
     * @param int correctAnswer = The correct answer to the question
     */
    public Question(String question, ArrayList<String> answers, int correctAnswer){
        this.question = question;
        this.answers= answers;
        this.correctAnswer = correctAnswer;
    }
    /**
     * Set a new question
     * @param String question = The question to be added
     */
    public void setQuestion(String question){
        this.question = question;
    }
    /**
     * Set a new correct answer to question
     * @param int correctAnswer = The correct answer to be set
     */
    public void setCorrectAnswer(int correctAnswer){
        this.correctAnswer = correctAnswer;
    }
    /**
     * @return a String representation of the question
     */
    public String toString(){
        if (answers.size() != 4) 
            return "";

        return question+"\n"+
        "A. "+answers.get(0)+"\n"+
        "B. "+answers.get(1)+"\n"+
        "C. "+answers.get(2)+"\n"+
        "D. "+answers.get(3);
    }
    /**
     * @return The correct answer
     */
    public int getCorrectAnswer(){
        return correctAnswer;
    }
    /**
     * @return A list of all the answers
     */
    public ArrayList<String> getAnswers(){
        return answers;
    }
    /**
     * @return The question
     */
    public String getQuestion(){
        return question;
    }
    /**
     * @return whether or not the answer entered is correct
     */
    public boolean isCorrect(int answer){
        return answer == correctAnswer;
    }
}
