package source;

import java.util.ArrayList;

public class Module {
    private String title;
    private String topic;
    private ArrayList<String> lessons;
    private Quiz quiz;
    private ArrayList<Comment> comments;
    //public ArrayList<Content> content;
    /**
     * Constructor for a module object
     * @param String title = The title of the module
     * @param String topic = The topic of the module
     * @param ArrayList<String> lessons = The list of lessons
     * in the module
     * @param Quiz quiz = The quiz for the module
     * @param ArrayList<Comment> comments = The comments on the module
     */
    public Module(String title, String topic, ArrayList<String> lessons,
    Quiz quiz, ArrayList<Comment> comments){
        this.title = title;
        this.topic = topic;
        this.lessons = lessons;
        this.quiz = quiz;
        this.comments = comments;
        //this.content = content;
    }
    /**
     * @return The list of all comments on a module
     */
    public ArrayList<Comment> getAllComments(){
        return comments;
    }
    /**
     * @return This Quiz
     */
    public Quiz getQuiz(){
        return quiz;
    }
    /**
     * @return The list of lessons in the module
     */
    public ArrayList<String> getLessons(){
        return lessons;
    }
    /**
     * Removes a lesson from the list
     * @param int index = The index from which the remove the lesson
     */
    public void removeLesson(int index){
        lessons.remove(index);
    }
    /**
     * Adds a lesson to the list
     * @param String lesson = The lesson to be added
     */
    public void addLesson(String lesson){
        lessons.add(lesson);
    }

    /*
    public int numContent(){
        return content.size();
    }*/
    /**
     * @return The title
     */
    public String getTitle(){
        return title;
    } 
    /**
     * @return the topic
     */
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
    /**
     * Sets a new title
     * @param String title = What the title will be changed to
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Sets a new topic
     * @param String topic = What the topic will be changed to
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }
    /**
     * @return a string representation of the title and topic
     * of the Module
     */
    public String toString(){
        return "Title: "+title+"\nTopic: "+topic+"\n";
    }
}
