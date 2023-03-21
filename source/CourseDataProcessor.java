package source;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CourseDataProcessor {

    private static JSONArray saveComments(ArrayList<Comment> comments){
        JSONArray ret = new JSONArray();

        for (int i = 0; i < comments.size(); i++){
            Comment comment = comments.get(i);
            JSONObject jComment = new JSONObject();
            jComment.put(DataConstants.AUTHOR_ID, comment.getAuthorID().toString());
            jComment.put(DataConstants.COMMENT, comment.getComment());
            jComment.put(DataConstants.DATE_ADDED, comment.getDateAdded().toString());
            jComment.put(DataConstants.REPLIES, saveComments(comment.getReplies()));

            ret.add(jComment);
        }
        return ret;
    }

    private static ArrayList<Comment> loadComments(JSONArray comments){
        ArrayList<Comment> ret = new ArrayList<>();
        try{
            for (int i = 0; i < comments.size(); i++){
                JSONObject jComment = (JSONObject)comments.get(i);
                
                SimpleDateFormat format = new SimpleDateFormat("MM/DD/YYYY");
                ret.add(new Comment(
                    UUID.fromString(((String)jComment.get(DataConstants.AUTHOR_ID))),
                    (String)jComment.get(DataConstants.COMMENT),
                    format.parse((String)jComment.get(DataConstants.DATE_ADDED)),
                    loadComments((JSONArray)jComment.get(DataConstants.REPLIES))
                ));
            }
        }catch(Exception e){};
        return ret;
    }
    
    private static ArrayList<Review> loadReviews(JSONObject course) {
        ArrayList<Review> reviews = new ArrayList<>();
        try{
                JSONArray jReviews = (JSONArray)course.get(DataConstants.REVIEWS);
                SimpleDateFormat format = new SimpleDateFormat("MM/DD/YYYY");
                for (int r = 0; r < jReviews.size(); r++){
                    JSONObject reviewObject = (JSONObject)jReviews.get(r);
                    Review review = new Review(UUID.fromString((String)course.get(DataConstants.AUTHOR_ID)), 
                    (float)reviewObject.get(DataConstants.RATING), 
                    (String)reviewObject.get(DataConstants.REVIEW), 
                    format.parse((String)reviewObject.get(DataConstants.DATE_ADDED)));
                    reviews.add(review);
                }          
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return reviews;
    }

    private static ArrayList<Module> loadModules(JSONObject course) {
        ArrayList<Module> modules = new ArrayList<>();
        try{         
                JSONArray jModules = (JSONArray)course.get(DataConstants.MODULES);
                for (int m = 0; m < jModules.size(); m++){
                    JSONObject moduleObject = (JSONObject)jModules.get(m);
                    Module module = new Module((String)course.get(DataConstants.TITLE),
                    (String)course.get(DataConstants.TOPIC), 
                    loadContent((JSONArray)moduleObject.get(DataConstants.CONTENT)));
                    modules.add(module);
                }

            
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return modules;
    }

    private static ArrayList<String> loadAnswers(JSONArray jAnswers){
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < jAnswers.size(); i++){
            answers.add((String)((JSONObject)jAnswers.get(i)).get(DataConstants.ANSWER));
        }

        return answers;
    }

    private static ArrayList<Question> loadQuestions(JSONArray jQuestions){
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < jQuestions.size(); i++){
            JSONObject jQuestion = (JSONObject)jQuestions.get(i);
            questions.add(new Question(
                (String)jQuestion.get(DataConstants.QUESTION),
                loadAnswers((JSONArray)jQuestion.get(DataConstants.ANSWERS)),
                (int)jQuestion.get(DataConstants.CORRECT_ANSWER)
            ));    
        }
        return questions;
    }

    private static ArrayList<Content> loadContent(JSONArray contentArray) {
        ArrayList<Content> content = new ArrayList<>();
        for (int c = 0; c < contentArray.size(); c++){
            JSONObject contentObject = (JSONObject)contentArray.get(c);
            content.add(new Content(
                (String)contentObject.get(DataConstants.TITLE),
                (String)contentObject.get(DataConstants.LESSON),
                (float)contentObject.get(DataConstants.PASSING_GRADE),
                ContentType.fromString((String)contentObject.get(DataConstants.CONTENT_TYPE)),
                loadQuestions((JSONArray)contentObject.get(DataConstants.QUESTIONS))
            ));
        }     

        return content;
    }

    public static CourseList loadData() {
        ArrayList<Course> courses = new ArrayList<>();
        try{
            FileReader reader = new FileReader(DataConstants.COURSES_FILE_NAME);
            JSONArray jCourses = (JSONArray)new JSONParser().parse(reader);

            for (int c = 0; c < jCourses.size(); c++) {
                JSONObject jCourse = (JSONObject)jCourses.get(c);
                ArrayList<Review> reviews = loadReviews(jCourse);
                ArrayList<Module> modules = loadModules(jCourse);
                ArrayList<Comment> comments = loadComments((JSONArray)jCourse.get(DataConstants.COMMENTS));
                courses.add(new Course(
                    UUID.fromString(((String)jCourse.get(DataConstants.COURSE_ID))), 
                    (String)jCourse.get(DataConstants.TITLE), 
                    (String)jCourse.get(DataConstants.LANGUAGE), 
                    (Float)jCourse.get(DataConstants.RATING), 
                    (String)jCourse.get(DataConstants.DESCRIPTION),  
                    UUID.fromString(((String)jCourse.get(DataConstants.AUTHOR_ID))), 
                    reviews,
                    comments,
                    modules)); 
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    
        return CourseList.getInstance(courses);
    }

    public static void saveData(CourseList courseList){

    }

}
