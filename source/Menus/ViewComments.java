package source.Menus;
import source.Comment;
import source.LMSFacade;

import java.util.ArrayList;

public class ViewComments extends ListMenu {

    /*
     * Two constructors because fail message will be differnet depending on what the user is viewing comments on (course vs another comment)
     */
    public ViewComments(LMSFacade facade, Menu pMenu, ArrayList<Comment> comments, Boolean b){
        super(facade, pMenu, (ArrayList<?>)comments, "This course has no comments.");
  
        this.options = new String[]{"Next", "Previous", "View Replies", "Add Reply", "Back"};
    }

    public ViewComments(LMSFacade facade, Menu pMenu, ArrayList<Comment> comments){
        super(facade, pMenu, (ArrayList<?>)comments, "This comment has no replies.");
  
        this.options = new String[]{"Next", "Previous", "View Replies", "Add Reply", "Back"};  
    }

    protected void updateHeader(){
        Comment comment = (Comment)list.get(index);
        header = "***Viewing comment "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+comment.toString();
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
                facade.setCurrentMenu(new ViewComments(facade, pMenu, ((Comment)list.get(index)).getReplies())).getSelection();
            case 4:
                facade.makeComment((ArrayList<Comment>)list);
                break;
            case 5:
                back();
                break;
        }
    }
}
