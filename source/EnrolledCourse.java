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

    }

    public UUID getID(){
        return courseID;
    }

    public void setModuleProgress(ArrayList<ArrayList<Boolean>> moduleProgress){
        this.moduleProgress = moduleProgress;
    }

    public float getModuleProgress(int index){
        return 0.0f;
    }

    public float getCourseProgress(){
        return 0.0f;
    }

    public String toString(){
        String ret = courseID+"\n";
        for (int i = 0; i < moduleProgress.size(); i++){
            ret += "Progress "+(i+1)+"\n";
            ArrayList<Boolean> progress = moduleProgress.get(i);
            for (int j = 0; j < progress.size(); j++){
                ret += "\t"+progress.get(j)+"\n";
            }
        }
        return ret;
    }
}
