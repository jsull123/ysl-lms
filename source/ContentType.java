package source;

/*
 * Delete
 */

public enum ContentType {
    LESSON,
    ASSESSMENT;

    public static ContentType fromString(String s){
        if (s.equals("LESSON")){
            return LESSON;
        }else{
            return ASSESSMENT;
        }
    }  

    @Override
    public String toString(){
        if (this == LESSON){
            return "Lesson";
        }
        return "Assessment";
    }
}
