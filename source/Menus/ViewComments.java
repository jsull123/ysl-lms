package source.Menus;
import source.Comment;
import source.LMSFacade;

import java.util.ArrayList;

public class ViewComments extends ListMenu<Comment> {

    public ViewComments(LMSFacade facade, Menu pMenu, ArrayList<Comment> comments){
        super(facade, pMenu, comments);
    }

    private void updateOptions(){   
        if (list.size() == 0){
            options = new String[]{"Add Comment", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Add Comment", "View Replies", "Back"};
        }     
    }

    protected void updateHeader(){
        if (list.size() == 0){
            header = "No comments found\n";
        }else{
            header = "***Viewing comment "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+get().toString();
        }
        updateOptions();
    }

    public void select(int selection){
        if (list.size() == 0){
            switch (selection){
                case 1:
                    facade.makeComment(list);
                case 2:
                    back();
            }
        }
        switch (selection){
            case 1:
                next();              
                break;
            case 2:
                prev();        
                break;
            case 3:
                facade.makeComment(list);
            case 4:
                facade.setCurrentMenu(new ViewComments(facade, this, get().getReplies())).getSelection();
            case 5:
                back();
                break;
        }
    }
}
