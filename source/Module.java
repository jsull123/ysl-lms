package source;

import java.util.ArrayList;

public class Module {
    private String title;
    private String topic;
    private ArrayList<String> lessons;
    private Quiz quiz;
    //public ArrayList<Content> content;

    public Module(String title, String topic, ArrayList<String> lessons, Quiz quiz){
        this.title = title;
        this.topic = topic;
        this.lessons = lessons;
        this.quiz = quiz;
        //this.content = content;
    }
    
    public Quiz getQuiz(){
        return quiz;
    }

    public ArrayList<String> getLessons(){
        return lessons;
    }

    public void removeLesson(int index){
        lessons.remove(index);
    }

    public void addLesson(String lesson){
        lessons.add(lesson);
    }

    /*
    public int numContent(){
        return content.size();
    }*/

    public String getTitle(){
        return title;
    } 

    public String getTopic(){
        return topic;
    }
    
    /*
    public ArrayList<Content> getAllContent(){
        return content;
    }

    public Content getContent(int index){
        return content.get(index);
    }
    */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public String toString(){
        return "Title: "+title+"\nTopic: "+topic+"\n";
    }
}
