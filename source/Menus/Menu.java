package source.Menus;
import source.LMSFacade;
import source.LMSUI;

public abstract class Menu {
    protected LMSFacade facade;
    protected String header;
    protected String[] options;

    public abstract void select(int selection);

    public void getSelection(){
        getSelection("");
    }

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
    
    public String toString(){
        String ret = "";
        if (header != "") ret += header+"\n";
        for (int i = 0; i < options.length; i++){
            ret += (i+1)+". "+options[i]+"\n";
        }
        return ret;
    }
}
