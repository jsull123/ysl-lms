package source.Menus;

import java.util.ArrayList;
import source.*;

public class ModifyQuestions extends ListMenu<Question>{
   
    public ModifyQuestions(LMSFacade facade, Menu pMenu, ArrayList<Question> questions) {
        super(facade, pMenu, questions, "There are no questions");

        options = new String[]{"Next", "Previous", "Change Question", "Change Correct Answer", "Modify Answers", "Back"};
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
                get().setQuestion(LMSUI.promptString("Enter a new question:", true));
                getSelection();
                break;
            case 4:
                // Change correct answer, must be 1-4
            case 5:
                // ModifyAnswers menu
            case 6:
                back();
                break;
        }
    }

    protected void updateHeader() {
        header = "***Viewing question "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString(); 
    }
 
}
