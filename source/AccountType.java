package source;

public enum AccountType{
    AUTHOR,
    STUDENT;

    public static AccountType fromString(String s){
        if (s == "AUTHOR"){
            return AUTHOR;
        }else{
            return STUDENT;
        }
    }
}