package source.Menus;
import java.util.ArrayList;

import source.LMSFacade;
import source.Review;

public class ViewReviews extends ListMenu<Review> {
    /**
     * Constructs a ViewReviews object
     * @param: LMSFacade facade = object to use the Facade methods
     * @param: Menu pMenu = object to use the data members and methods of Menu class
     * @param: ArrayList<Review> reviews = List of all the reviews that have been created
     */
    public ViewReviews(LMSFacade facade, Menu pMenu, ArrayList<Review> reviews) {
        super(facade, pMenu, reviews);  
    }
    /**
     * Gives the user options based on if they've created a course yet or not
     */
    private void updateOptions(){
        if (list.size() == 0){
            options = new String[]{"Add Review", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Add Review", "Back"};
        } 
    }
     /**
     * Gives the user options to choose from
     */
    protected void updateHeader(){
        if (list.size() == 0){
            header = "This course has no reviews. Choose option 1 to add a review\n";
        }else{
            header = "***Viewing review "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+get().toString();
        }
        updateOptions();
    }
     /**
     * Gives the user options to move through this screen of the LMS
     * @param: int selection = The choice that the user has made
     */
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