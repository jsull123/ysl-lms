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
        return 0.0f;
    }

    public float getCourseProgress(){
        return 0.0f;
    }

    public String toString(){
        CourseList list = CourseList.getInstance(null);
        Course course = list.getCourse(courseID);
        return course.getTitle();
    }
}