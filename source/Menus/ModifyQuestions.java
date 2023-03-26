package source.Menus;

import java.util.ArrayList;
import source.*;

public class ModifyQuestions extends ListMenu<Question>{
   
    public ModifyQuestions(LMSFacade facade, Menu pMenu, ArrayList<Question> questions) {
        super(facade, pMenu, questions);

        if (list.size() == 0){
            options = new String[]{"Create New Question", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Create New Question", "Remove This Question", "Back"};
        }
    }

    public void select(int selection) {
        if (list.size() == 0){
            switch(selection){
                case 1:
                    facade.createQuestion(list);
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
                facade.createQuestion(list);
                break;
            case 4:
                list.remove(get());
                getSelection("Question Removed");
            case 5:
                back();
        }
    }

    protected void updateHeader() {
        if (list.size() == 0){
            header = "This content has no questions. Choose option 1 to add a new question";
        }else{
            header = "***Viewing question "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+get().toString(); 
        }   
    } 
}
