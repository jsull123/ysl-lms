package source.Menus;
import source.*;
import java.util.ArrayList;

public class ViewContent extends Menu{
    private int index;
    private ArrayList<Content> contents;
    private Menu pMenu;
    private ArrayList<Boolean> contentCompletion;

    public ViewContent(LMSFacade facade, Menu pMenu, ArrayList<Content> contents, ArrayList<Boolean> contentCompletion){
        if (contents == null)
            pMenu.getSelection();
        if (contents.size() == 0) 
            pMenu.getSelection("This course has no content.");

        index = 0;

        this.contentCompletion = contentCompletion;

        updateHeader();

        this.facade = facade;
        this.pMenu = pMenu;
        this.contents = contents;
        this.options = new String[]{"Next", "Previous", "Something else", "Back"};
    }

    private void updateHeader(){
        header = "***Viewing content "+(index+1)+" of "+
        contents.size()+"***"+"\n\n"+contents.get(index).toString()+
        "Completed: "+contentCompletion.get(index);
    }

    private void prev(){
        index--;
        if (index < 0) index = contents.size()-1;

        updateHeader();
        getSelection(); 
    }

    private void next(){
        index++;
        if (index >= contents.size()) index = 0;

        updateHeader();
        getSelection();
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
                facade.setCurrentMenu(pMenu).getSelection();
                break;
        }
    }
}
