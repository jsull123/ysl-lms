package source;
import java.util.UUID;
import java.util.ArrayList;

public class EnrolledCourse {
    private UUID courseID;
    private ArrayList<Float> moduleProgress;

    public EnrolledCourse(UUID courseID, ArrayList<Float> moduleProgress){
        this.courseID = courseID;
        this.moduleProgress = moduleProgress;
    }

    public EnrolledCourse(UUID courseID){
        this.courseID = courseID;
        this.moduleProgress = new ArrayList<>();
    }

    public UUID getID(){
        return courseID;
    }

    public Course getCourse(){
        return CourseList.getInstance(null).getCourse(courseID);
    }

    public void setModuleProgress(ArrayList<Float> moduleProgress){
        this.moduleProgress = moduleProgress;
    }

    public ArrayList<Float> getModuleProgress(){
        return moduleProgress;
    }

    public float getCourseProgress(){
        float ret = 0;
        for (int i = 0; i < moduleProgress.size(); i++){
            ret+=moduleProgress.get(i);
        }
        return ret/moduleProgress.size();
    }

    public String toString(){
        return CourseList.getInstance(null).getCourse(courseID).getTitle();
    }
}