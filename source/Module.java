package source;

import java.util.ArrayList;

public class Module {
    public String title;
    public String topic;
    public ArrayList<String> lessons;
    public ArrayList<Question> quiz;
    //public ArrayList<Content> content;

    public Module(String title, String topic, ArrayList<Content> content){
        this.title = title;
        this.topic = topic;
        //this.content = content;
    }
    
    public ArrayList<Question> getQuiz(){
        return quiz;
    }

    public ArrayList<String> getLessons(){
        return lessons;
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
