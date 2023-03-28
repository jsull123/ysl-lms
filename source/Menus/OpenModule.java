package source.Menus;
import java.util.ArrayList;
import source.EnrolledCourse;
import source.Question;
import source.LMSFacade;
import source.Module;

public class OpenModule extends ListMenu{

    private Module module;
    private ArrayList<Question> quiz;
    public OpenModule(LMSFacade facade, Menu pMenu, Module module, EnrolledCourse course) {
        super(facade, pMenu, module.getLessons());
        quiz = module.getQuiz();
        this.module = module;
    }
    
}
