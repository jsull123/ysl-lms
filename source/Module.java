package source;

import java.util.ArrayList;

public class Module {
    public String title;
    public String topic;
    public ArrayList<Content> content;

    public Module(String title, String topic, ArrayList<Content> content){
        this.title = title;
        this.topic = topic;
        this.content = content;
    }

    public int numContent(){
        return content.size();
    }

    public String getTitle(){
        return title;
    } 

    public String getTopic(){
        return topic;
    }
    
    public ArrayList<Content> getAllContent(){
        return content;
    }

    public Content getContent(int index){
        return content.get(index);
    }
}
