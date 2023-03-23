package source;
import java.util.UUID;
import java.util.ArrayList;

public class EnrolledCourse {
    private UUID courseID;
    private ArrayList<ArrayList<Boolean>> moduleProgress;

    public EnrolledCourse(UUID courseID, ArrayList<ArrayList<Boolean>> moduleProgress){
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

    public void setModuleProgress(ArrayList<ArrayList<Boolean>> moduleProgress){
        this.moduleProgress = moduleProgress;
    }

    public ArrayList<ArrayList<Boolean>> getModuleProgress(){
        return moduleProgress;
    }

    public float getModuleProgress(int index){
        float ret = 0;
        for (int i = 0; i < moduleProgress.get(index).size(); i++){
            if(moduleProgress.get(index).get(i) == true){
                ret++;
            }
        }
        return ret/moduleProgress.get(index).size();
    }

    public float getCourseProgress(){
        float ret = 0;
        for (int i = 0; i < moduleProgress.size(); i++){
            ret+=getModuleProgress(i);
        }
        return ret/moduleProgress.size();
    }

    public String toString(){
        CourseList list = CourseList.getInstance(null);
        Course course = list.getCourse(courseID);
        return course.getTitle();
    }
}