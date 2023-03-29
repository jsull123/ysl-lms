package source.Menus;
import java.util.ArrayList;

import source.LMSFacade;
import source.Review;

public class ViewReviews extends ListMenu<Review> {

    public ViewReviews(LMSFacade facade, Menu pMenu, ArrayList<Review> reviews) {
        super(facade, pMenu, reviews);  
    }

    private void updateOptions(){
        if (list.size() == 0){
            options = new String[]{"Add Review", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Add Review", "Back"};
        } 
    }

    protected void updateHeader(){
        if (list.size() == 0){
            header = "This course has no reviews. Choose option 1 to add a review\n";
        }else{
            header = "***Viewing review "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+get().toString();
        }
        updateOptions();
    }

    public void select(int selection) {
        if (list.size() == 0){
            switch(selection){
                case 1:
                    facade.createReview(list);
                case 2:
                    back();
            }
        }
        switch(selection) {
            case 1:
                next();
                break;
            case 2:
                prev();
                break;
            case 3:
                facade.createReview(list);
                break;
            case 4:
                back();
        }
    }
}