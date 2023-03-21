package source;

public enum ContentType {
    LESSON,
    ASSESMENT;

    public static ContentType fromString(String s){
        if (s == "LESSON"){
            return LESSON;
        }else{
            return ASSESMENT;
        }
    }
}
