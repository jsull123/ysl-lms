package source.Menus;
import source.*;
import java.util.ArrayList;

public class ViewContent extends ListMenu {
  
    private ArrayList<Boolean> contentCompletion;

    public ViewContent(LMSFacade facade, Menu pMenu, ArrayList<Content> contents, ArrayList<Boolean> contentCompletion){
        super(facade, pMenu, (ArrayList<?>)contents, "This course has no content.");

        this.contentCompletion = contentCompletion;
        this.options = new String[]{"Next", "Previous", "Something else", "Back"};
    }

    protected void updateHeader(){
        Content c = (Content)list.get(index);
        header = "***Viewing content "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+c.toString();
        if (contentCompletion.get(index)){
            header += "\nYou have completed this "+c.getContentType().toString()+"\n";
        }else{
            header += "\nYou have not completed this "+c.getContentType().toString()+"\n";
        }
    }

    public void select(int selection){
        switch (selection){
            case 1:
                next();              
                break;
            case 2:
                prev();        
                break;
            case 3:
                // Temp
                getSelection("Didnt make this yet");
                break;
            case 4:
                back();
                break;
        }
    }
}
