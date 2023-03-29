package source.Menus;
import source.*;
import java.util.ArrayList;

/*
 * Delete
*/
public class ViewContent extends ListMenu<Content> {
  
    private ArrayList<Boolean> contentCompletion;

    public ViewContent(LMSFacade facade, Menu pMenu, ArrayList<Content> contents, ArrayList<Boolean> contentCompletion){
        super(facade, pMenu, contents, "This course has no content.");

        this.contentCompletion = contentCompletion;
        this.options = new String[]{"Next", "Previous", "Take Content", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing content "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString();
        header = "***Viewing content "+(index+1)+" of "+
        list.size()+"***"+"\n\n"
        + "Content Type and Title: " + get().toString() + "\n"
        + "Lesson: " + get().getLesson() + "\n"
        + "Passing Grade: " + get().getPassingGrade() + "\n";

        if (contentCompletion.get(index)){
            header += "\nYou have completed this "+get().getContentType().toString()+"\n";
      
        }else{
            header += "\nYou have not completed this "+get().getContentType().toString()+"\n";
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
