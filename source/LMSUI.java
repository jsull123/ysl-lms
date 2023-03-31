package source;

import java.util.Scanner;

public class LMSUI {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * @return the integer that the user enters
     * @param String prompt = The question asked of the user
     * @param boolean cls = Whether or not to move on to the next screen
     */
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
    /**
     * @return the float that the user enters
     * @param String prompt = The question asked of the user
     * @param boolean cls = Whether or not to move on to the next screen
     */
    public static float promptFloat(String prompt, boolean cls){
        if (cls) clearScreen();
        System.out.println(prompt);
        float f = 0;
        try{
            f = scanner.nextFloat();
        } catch (Exception e){ f = 0; }
        scanner.nextLine();
        return f;
    }
    /**
     * @return the String that the user enters
     * @param String prompt = The question asked of the user
     * @param boolean cls = Whether or not to move on to the next screen
     */
    // Prompt user for String
    public static String promptString(String prompt, boolean cls){
        if (cls) clearScreen();
        System.out.println(prompt);
        return scanner.nextLine();
    }
    /**
     * Clears the screen
     */
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

}
