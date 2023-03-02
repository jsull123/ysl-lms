package source;
import java.util.UUID;

public class EnrolledCourse {
    private UUID courseID;
    private int[][] moduleProgress;

    public EnrolledCourse(UUID courseID, int[][] moduleProgress){
        this.courseID = courseID;
        this.moduleProgress = moduleProgress;
    }

    public EnrolledCourse(UUID courseID){

    }

    public UUID getID(){
        return courseID;
    }

    public float getModuleProgress(int index){
        return 0.0f;
    }

    public float getCourseProgress(){
        return 0.0f;
    }
}
