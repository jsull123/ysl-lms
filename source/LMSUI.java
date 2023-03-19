package source;

import java.util.Scanner;

public class LMSUI {
    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static int getInt(){
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        try{
            i = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){ i = 0; }
        return i;
    }

    public static String getPassword(){
        clearScreen();
        System.out.println("Enter your password:");
        return getString();
    }

    public static String getUsername(){
        clearScreen();
        System.out.println("Enter your username:");
        return getString();
    }

    public static String getString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
