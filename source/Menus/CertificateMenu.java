package source.Menus;
import java.util.ArrayList;

import source.*;

public class CertificateMenu extends ListMenu<Course> {

    public CertificateMenu(LMSFacade facade, Menu pMenu, ArrayList<Course> completedCourses) {
        super(facade, pMenu, completedCourses, "You don't have any certificates. Once you complete a course, you will find your certificate here.");  
        options = new String[]{"Next", "Prev", "Print Certificate", "Back"};
    }

    protected void updateHeader(){
        header = "***Viewing certificate "+(index+1)+" of "+
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
                String fileName = get().getTitle()+"_Certificate.txt";
                facade.outputToFile(facade.getCertificate(get()), fileName);
                getSelection("Successfully created file "+fileName);
                break;
            case 4:
                back();
        }
    }
}