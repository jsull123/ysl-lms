package source;
import java.util.UUID;
import java.util.ArrayList;

public class EnrolledCourse {
    private UUID courseID;
    private ArrayList<ModuleProgress> moduleProgress;

    public EnrolledCourse(UUID courseID, ArrayList<ModuleProgress> moduleProgress){
        this.courseID = courseID;
        this.moduleProgress = moduleProgress;
    }

    public EnrolledCourse(UUID courseID){
        this.courseID = courseID;
        int numModules = CourseList.getInstance(null).getCourse(courseID).numModules();
        this.moduleProgress = new ArrayList<>(numModules);
    }

    public ModuleProgress getModuleProgress(int index){
        if (index >= moduleProgress.size()){
            for (int i = moduleProgress.size()-1; i <= index; i++){
                moduleProgress.add(new ModuleProgress(0.0f, false));
            }
        }
        return moduleProgress.get(index);
    }

    public UUID getID(){
        return courseID;
    }

    public Course getCourse(){
        return CourseList.getInstance(null).getCourse(courseID);
    }

    public void setModuleProgress(ArrayList<ModuleProgress> moduleProgress){
        this.moduleProgress = moduleProgress;
    }

    public ArrayList<ModuleProgress> getModuleProgress(){
        return moduleProgress;
    }

    public float getCourseProgress(){
        if (moduleProgress.size() == 0) return 0;

        float ret = 0;
        for (int i = 0; i < moduleProgress.size(); i++){
            if (moduleProgress.get(i).getHasPassed()){
                ret+=1;
            }
        }
        return ret/moduleProgress.size();
    }

    public String toString(){
        return CourseList.getInstance(null).getCourse(courseID).getTitle();
    }
}