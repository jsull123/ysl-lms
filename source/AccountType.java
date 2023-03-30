package source;
/**
 * The only allowable types for accounts
 */
public enum AccountType{
    AUTHOR,
    STUDENT;
    /**
     * Sets the account type from a string
     * @param: String s = The string representation of the account type
     */
    public static AccountType fromString(String s){
        if (s.toUpperCase().equals("AUTHOR"))
            return AUTHOR;
        return STUDENT;
    }
    /**
     * Sets the account type from an int
     * @param: int i = The integer representation of the account type
     */
    public static AccountType fromInt(int i){
        if (i == 1)
            return AUTHOR;
        return STUDENT;
    }
}