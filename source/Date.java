package source;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Temporary date class because I couldn't figure out how to use the java date class
public class Date {
    private int m;
    private int d;
    private int y;

    public Date(){
        LocalDateTime now = LocalDateTime.now();
        this.m = now.getMonthValue();
        this.d = now.getDayOfMonth();
        this.y = now.getYear();
    }

    public Date(int m, int d, int y){
        this.m = m;
        this.y = y;
        this.d = d;
    }

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

    public String toString(){
        return m+"/"+d+"/"+y;
    }
}
