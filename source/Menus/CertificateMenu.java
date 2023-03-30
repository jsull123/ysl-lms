package source.Menus;
import java.util.ArrayList;

import source.*;

public class CertificateMenu extends ListMenu<Course> {
    /**
     * Constructs a CertificateMenu object
     * @param LMSFacade facade = object to use facade method
     * @param Menu pMenu = object to use Menu methods and data members
     * @param completedCourses = List of courses completed by the current user
     */
    public CertificateMenu(LMSFacade facade, Menu pMenu, ArrayList<Course> completedCourses) {
        super(facade, pMenu, completedCourses, "You don't have any certificates. Once you complete a course, you will find your certificate here.");  
        options = new String[]{"Next", "Prev", "Print Certificate", "Back"};
    }
    /**
     * Prints the new header
     */
    protected void updateHeader(){
        header = "***Viewing certificate "+(index+1)+" of "+
        list.size()+"***"+"\n\n"+get().toString()+"\n";
    }
    /**
     * Gives the user options to move through this screen of the LMS
     * @param int selection = The choice the user makes
     */
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