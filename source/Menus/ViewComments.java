package source.Menus;
import source.Comment;
import source.LMSFacade;

import java.util.ArrayList;

public class ViewComments extends ListMenu<Comment> {

    public ViewComments(LMSFacade facade, Menu pMenu, ArrayList<Comment> comments){
        super(facade, pMenu, comments, "No comments found");

        this.options = new String[]{"Next", "Previous", "View Replies", "Add Reply", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing comment "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString();
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
                facade.setCurrentMenu(new ViewComments(facade, this, get().getReplies())).getSelection();
            case 4:
                facade.makeComment(list);
                break;
            case 5:
                back();
                break;
        }
    }
}
