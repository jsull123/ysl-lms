package source;

public enum AccountType{
    AUTHOR,
    STUDENT;

    public static AccountType fromString(String s){
        if (s == "Author"){
            return AUTHOR;
        }else{
            return STUDENT;
        }
    }
}