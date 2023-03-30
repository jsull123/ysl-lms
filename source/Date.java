package source;

import java.time.LocalDateTime;

// Temporary date class because I couldn't figure out how to use the java date class
public class Date {
    private int m;
    private int d;
    private int y;
    /**
     * Default Constructor for a man-made Date class
     */
    public Date(){
        LocalDateTime now = LocalDateTime.now();
        this.m = now.getMonthValue();
        this.d = now.getDayOfMonth();
        this.y = now.getYear();
    }
    /**
     * Parameterized constructor for a man-made Date class
     * @param int m = Month
     * @param int d = Day
     * @param int y = year
     */
    public Date(int m, int d, int y){
        this.m = m;
        this.y = y;
        this.d = d;
    }
    /**
     * @return a Date object from a string of the month, day, and year
     * @param String s = The string to turn into a date
     */
    public static Date fromString(String s){
        Date date = new Date();
        String[] ints = s.split("/");

        try{
            if (ints.length == 3){
                date = new Date(Integer.parseInt(ints[0]), Integer.parseInt(ints[1]), Integer.parseInt(ints[2]));
                }    
        }catch (Exception e){}
        
        return date;
    }
    /**
     * @return The date as a string
     */
    public String toString(){
        return m+"/"+d+"/"+y;
    }
}
