package source;

import java.util.Scanner;

public class LMSUI {
    private static Scanner scanner = new Scanner(System.in);
    
     // Prompt user for integer
    public static int promptInt(String prompt, boolean cls){
        if (cls) clearScreen();
        System.out.println(prompt);
        int i = 0;
        try{
            i = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){ i = 0; }
        return i;
    }

    // Prompt user for String
    public static String promptString(String prompt, boolean cls){
        if (cls) clearScreen();
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

}
