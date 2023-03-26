package source.Menus;
import java.util.ArrayList;

import source.LMSFacade;
import source.Review;

public class ViewReviews extends ListMenu<Review> {

    public ViewReviews(LMSFacade facade, Menu pMenu, ArrayList<Review> reviews) {
        super(facade, pMenu, reviews, "This course has no reviews");

        options = new String[]{"Next", "Previous", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing review "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString();
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                next();
                break;
            case 2:
                prev();
                break;
            case 3:
                back();
                break;
        }
    }

}