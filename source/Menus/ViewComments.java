package source.Menus;
import source.Comment;
import source.LMSFacade;
import source.CourseDataProcessor;
import source.CourseList;

import java.util.ArrayList;

public class ViewComments extends Menu {
    private int index;
    private ArrayList<Comment> comments;
    private Menu pMenu;

    public ViewComments(LMSFacade facade, Menu pMenu, ArrayList<Comment> comments){
        if (comments == null)
            pMenu.getSelection();
        if (comments.size() == 0) 
            pMenu.getSelection("This course has no comments.");

        index = 0;

        header = "***Viewing comment "+(index+1)+" of "+
        comments.size()+"***"+"\n\n"+comments.get(index).toString();

        this.facade = facade;
        this.pMenu = pMenu;
        this.comments = comments;
        this.options = new String[]{"Next", "Previous", "View Replies", "Add Reply", "Back"};
    }

    private void prev(){
        index--;
        if (index < 0) index = comments.size()-1;

        header = "***Viewing comment "+(index+1)+" of "+
        comments.size()+"***"+"\n\n"+comments.get(index).toString();
    }

    private void next(){
        index++;
        if (index >= comments.size()) index = 0;

        header = "***Viewing comment "+(index+1)+" of "+
        comments.size()+"***"+"\n\n"+comments.get(index).toString();
    }

    public void select(int selection){
        switch (selection){
            case 1:
                next();
                getSelection();
                break;
            case 2:
                prev(); 
                getSelection();
                break;
            case 3:
                if (comments.get(index).getReplies().size() > 0){
                    facade.setCurrentMenu(new ViewComments(
                        facade, this, comments.get(index).getReplies())).getSelection();
                    break;
                }
                getSelection("This comment has no replies");
            case 4:
                comments.get(index).getReplies().add(facade.makeComment());
                CourseDataProcessor.saveData(CourseList.getInstance(null));
                getSelection();
                break;
            case 5:
                facade.setCurrentMenu(pMenu).getSelection();
                break;
        }
    }
}
