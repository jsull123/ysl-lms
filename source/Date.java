package source;

// Temporary date class because I couldn't figure out how to use the java date class
public class Date {
    private int m;
    private int d;
    private int y;

    public Date(){
        this.m = 0;
        this.y = 0;
        this.d = 0;
    }

    public Date(int m, int d, int y){
        this.m = m;
        this.y = y;
        this.d = d;
    }

    public static Date fromString(String s){
        Date date = new Date();
        String[] ints = s.split("/");

        if (ints.length == 3){
            date = new Date(Integer.parseInt(ints[0]), Integer.parseInt(ints[1]), Integer.parseInt(ints[2]));
        }
        return date;
    }

    public String toString(){
        return m+"/"+d+"/"+y;
    }
}