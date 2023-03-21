package source;

public class Main{
    public static void main(String[] args){
        CourseList list = CourseDataProcessor.loadData();
        Course[] courses = list.toArray();

        for (int i = 0; i < courses.length; i++){
            Course course = courses[i];
            int p = 0;
        }
        new LMSFacade();
    }
}