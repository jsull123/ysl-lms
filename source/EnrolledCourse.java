package source;
import java.util.UUID;
import java.util.ArrayList;

public class EnrolledCourse {
    private UUID courseID;
    private ArrayList<ModuleProgress> moduleProgress;
    /**
     * Constructor for an EnrolledCourse object
     * @param UUID courseID = The ID of the currently enrolled course
     * @param ArrayList<ModuleProgress> moduleProgress = The percentage
     * completion by the current user of the course
     */
    public EnrolledCourse(UUID courseID, ArrayList<ModuleProgress> moduleProgress){
        this.courseID = courseID;
        this.moduleProgress = moduleProgress;
    }
    /**
     * Default constructor for an EnrolledCourse object
     * @param UUID courseID = The ID of the course to be enrolled
     */
    public EnrolledCourse(UUID courseID){
        this.courseID = courseID;
        int numModules = CourseList.getInstance(null).getCourse(courseID).numModules();
        this.moduleProgress = new ArrayList<>(numModules);
    }
    /**
     * @return The progress % the user has completed the course
     * @param int index = The index in the list at which the module progress
     * is stored
     */
    public ModuleProgress getModuleProgress(int index){
        if (index >= moduleProgress.size()){
            for (int i = moduleProgress.size()-1; i <= index; i++){
                moduleProgress.add(new ModuleProgress(0.0f, false));
            }
        }
        return moduleProgress.get(index);
    }
    /**
     * @return The ID of this course
     */
    public UUID getID(){
        return courseID;
    }
    /**
     * @return an object of this Course
     */
    public Course getCourse(){
        return CourseList.getInstance(null).getCourse(courseID);
    }
    /**
     * Sets a new percentage completion for a course
     * @param ArrayList<ModuleProgress> moduleProgress = The new completion for the
     * current user of a course
     */
    public void setModuleProgress(ArrayList<ModuleProgress> moduleProgress){
        this.moduleProgress = moduleProgress;
    }
    /**
     * @return The list of % progresses of a module 
     */
    public ArrayList<ModuleProgress> getModuleProgress(){
        return moduleProgress;
    }
    /**
     * @return The progress that the current user has made in the course
     */
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
    /**
     * @return A string representation of the course list
     */
    public String toString(){
        return CourseList.getInstance(null).getCourse(courseID).getTitle();
    }
}