package source.Menus;
import source.LMSUI;
import source.LMSFacade;

public abstract class Menu {
    protected LMSFacade facade;
    protected String header;
    protected String error;
    protected String[] options;
    public abstract void select(int selection);

    public int getSelection(String error){
        this.error = error;
        return getSelection();
    }

    public int getSelection(){
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
               
            System.out.println("Enter your selection: ");
            selection = LMSUI.getInt();
        
        } while (selection <= 0 || selection > options.length);
    
        return selection;  
    }
    
    public String toString(){
        String ret = header+"\n";
        for (int i = 0; i < options.length; i++){
            ret += (i+1)+". "+options[i]+"\n";
        }
        return ret;
    }
}
