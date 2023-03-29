package source.Menus;

import source.*;

public class ModifyQuiz extends ListMenu<Question>{

    private Quiz quiz;
   
    public ModifyQuiz(LMSFacade facade, Menu pMenu, Quiz quiz) {
        super(facade, pMenu, quiz.getQuestions());
        this.quiz = quiz;
    }

    private void updateOptions(){
        if (list.size() == 0){
            options = new String[]{"Create New Question", "Change Passing Grade", "Back"};
        }else{
            options = new String[]{"Next", "Previous", "Create New Question", "Remove This Question", "Change Passing Grade", "Back"};
        }
    }

    protected void updateHeader() {
        header = "Passing grade: "+quiz.getPassingGrade()*100+"%\n";
        if (list.size() == 0){
            header += "This quiz has no questions. Choose option 1 to add a new question\n";
        }else{
            header += "***Viewing question "+(index+1)+" of "+
            list.size()+"***"+"\n\n"+get().toString()+"\n"; 
        }
        updateOptions();
    } 

    public void select(int selection) {
        if (list.size() == 0){
            switch(selection){
                case 1:
                    facade.createQuestion(list);
                case 2:
                    facade.changePassingGrade(quiz);
                case 3:
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
                remove();
            case 5:
                facade.changePassingGrade(quiz);
            case 6:
                back();
        }
    }
}
