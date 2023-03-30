package source.Menus;

import source.LMSFacade;
import source.LMSUI;

public abstract class Menu {
    protected LMSFacade facade;
    protected String header;
    protected String[] options;
    protected Menu pMenu;
    /**
     * Constructs a Menu object
     * @param: LMSFacade facade = object to use the Facade methods
     * @param: Menu pMenu = object to use the data members and methods of Menu class
     */
    public Menu(LMSFacade facade, Menu pMenu){
        this.facade = facade;
        this.pMenu = pMenu;
    }
    /**
     * Goes to the previous screen
     */
    protected void back(){
        back("");
    }
    /**
     * returns to a specified screen
     * @param: String s = The screen to return to
     */
    protected void back(String s){
        facade.setCurrentMenu(pMenu).getSelection(s);
    }
    
    protected abstract void select(int selection);

    public void getSelection(){
        getSelection("");
    }
    /**
     * Handles errors that may occur while the user is working with the LMS
     */
    public void getSelection(String error){
        int selection = 1;
        do{
            LMSUI.clearScreen();
            System.out.println(this);  
          
            if (selection <= 0 || selection > options.length){
               error = "Invalid Selection!";
            }

            if (error != ""){
                System.out.println(error);
                error = "";
            }
            
            selection = LMSUI.promptInt("Enter your selection: ", false);
        
        } while (selection <= 0 || selection > options.length);
    
        select(selection); 
    }
    /**
     * Prints the header and options
     * @return: String representation of the user's options
     */
    public String toString(){
        String ret = "";
        if (header != "") ret += header+"\n";
        for (int i = 0; i < options.length; i++){
            ret += (i+1)+". "+options[i]+"\n";
        }
        return ret;
    }
}
