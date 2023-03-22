package source;

public enum ContentType {
    LESSON,
    ASSESMENT;

    public static ContentType fromString(String s){
        if (s.equals("LESSON")){
            return LESSON;
        }else{
            return ASSESMENT;
        }
    }
}
