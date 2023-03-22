package source;

public enum AccountType{
    AUTHOR,
    STUDENT;

    public static AccountType fromString(String s){
        if (s.equals("AUTHOR"))
            return AUTHOR;
        return STUDENT;
    }

    public static AccountType fromInt(int i){
        if (i == 1)
            return AUTHOR;
        return STUDENT;
    }
}