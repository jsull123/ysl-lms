package source;

import java.util.ArrayList;

public class Content {
    private String title;
    private String lesson;
    private float passingGrade;
    private ContentType contentType;
    private ArrayList<Question> questions;

    public Content(String title, String lesson, float passingGrade, ContentType contentType, ArrayList<Question> questions){
        this.lesson = lesson;
        this.title = title;
        this.passingGrade = passingGrade;
        this.contentType = contentType;
        this.questions = questions;
    }

    public String getTitle(){
        return title;
    }

    public String getLesson(){
        return lesson;
    }

    public ContentType getContentType(){
        return contentType;
    }

    public ArrayList<Question> getQuestions(){
        return questions;
    }

    public float getPassingGrade(){
        return passingGrade;
    }

    public String toString(){
        return contentType.toString()+" on "+title;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPassingGrade(float num) {
        this.passingGrade = num;
    }
}
