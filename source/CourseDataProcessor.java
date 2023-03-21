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

                ret.add(new Comment(
                    UUID.fromString(((String)jComment.get(DataConstants.AUTHOR_ID))),
                    (String)jComment.get(DataConstants.COMMENT),
                    Date.fromString((String)jComment.get(DataConstants.DATE_ADDED)),
                    loadComments((JSONArray)jComment.get(DataConstants.REPLIES))
                ));
            }
        }catch(Exception e){};
        return ret;
    }
    
    private static ArrayList<Review> loadReviews(JSONArray jReviews) {
        ArrayList<Review> reviews = new ArrayList<>();

        for (int r = 0; r < jReviews.size(); r++){
            JSONObject reviewObject = (JSONObject)jReviews.get(r);

            Review review = new Review(
            UUID.fromString((String)reviewObject.get(DataConstants.AUTHOR_ID)), 
            Float.valueOf((String)reviewObject.get(DataConstants.RATING)), 
            (String)reviewObject.get(DataConstants.REVIEW), 
            Date.fromString((String)reviewObject.get(DataConstants.DATE_ADDED)));
            reviews.add(review);
        }          
        return reviews;
    }

    private static ArrayList<Module> loadModules(JSONArray jModules) {
        ArrayList<Module> modules = new ArrayList<>();
    
        for (int m = 0; m < jModules.size(); m++){
            JSONObject moduleObject = (JSONObject)jModules.get(m);

            Module module = new Module(
                (String)moduleObject.get(DataConstants.TITLE),
                (String)moduleObject.get(DataConstants.TOPIC),
                loadContent((JSONArray)moduleObject.get(DataConstants.CONTENT))
            );

            modules.add(module);
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
                Integer.valueOf((String)jQuestion.get(DataConstants.CORRECT_ANSWER))
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
                Float.valueOf((String)contentObject.get(DataConstants.PASSING_GRADE)),
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
                courses.add(new Course(
                    UUID.fromString(((String)jCourse.get(DataConstants.COURSE_ID))), 
                    (String)jCourse.get(DataConstants.TITLE), 
                    (String)jCourse.get(DataConstants.LANGUAGE), 
                    (String)jCourse.get(DataConstants.DESCRIPTION),  
                    UUID.fromString(((String)jCourse.get(DataConstants.AUTHOR_ID))), 
                    loadReviews((JSONArray)jCourse.get(DataConstants.REVIEWS)),
                    loadComments((JSONArray)jCourse.get(DataConstants.COMMENTS)),
                    loadModules((JSONArray)jCourse.get(DataConstants.MODULES)))); 
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    
        return CourseList.getInstance(courses);
    }

    private static JSONArray saveReviews(ArrayList<Review> reviews){
        JSONArray jReviews = new JSONArray();

        for (int i = 0; i < reviews.size(); i++){
            JSONObject jReview = new JSONObject();

            jReview.put(DataConstants.AUTHOR_ID, reviews.get(i).getAuthorID().toString());
            jReview.put(DataConstants.RATING, Float.valueOf(reviews.get(i).getRating()).toString());
            jReview.put(DataConstants.REVIEW, reviews.get(i).getReview());
            jReview.put(DataConstants.DATE_ADDED, reviews.get(i).getDateAdded().toString());

            jReviews.add(jReview);
        }

        return jReviews;
    }


    private static JSONArray saveAnswers(ArrayList<String> answers){
        JSONArray jAnswers = new JSONArray();

        for (int i = 0; i < answers.size(); i++){
            JSONObject answer = new JSONObject();

            answer.put(DataConstants.ANSWER, answers.get(i));

            jAnswers.add(answer);
        }

        return jAnswers;
    }

    private static JSONArray saveQuestions(ArrayList<Question> questions){
        JSONArray jQuestions = new JSONArray();

        for (int i = 0; i < questions.size(); i++){
            JSONObject question = new JSONObject();

            question.put(DataConstants.QUESTION, questions.get(i).getQuestion());
            question.put(DataConstants.ANSWERS, saveAnswers(questions.get(i).getAnswers()));
            question.put(DataConstants.CORRECT_ANSWER, questions.get(i).getCorrectAnswer());

            jQuestions.add(question);
        }

        return jQuestions;
    }


    private static JSONArray saveContent(ArrayList<Content> contents){
        JSONArray jContents = new JSONArray();

        for (int i = 0; i < contents.size(); i++){
            JSONObject content = new JSONObject();

            content.put(DataConstants.TITLE, contents.get(i).getTitle());
            content.put(DataConstants.ANSWERS, contents.get(i).getLesson());
            content.put(DataConstants.PASSING_GRADE, Float.valueOf(contents.get(i).getPassingGrade()).toString());
            content.put(DataConstants.CONTENT_TYPE, contents.get(i).getContentType().toString());
            content.put(DataConstants.QUESTIONS, saveQuestions(contents.get(i).getQuestions()));

            jContents.add(content);
        }

        return jContents;
    }
 
    private static JSONArray saveModules(ArrayList<Module> modules){
        JSONArray jModules = new JSONArray();

        for (int i = 0; i < modules.size(); i++){
            JSONObject module = new JSONObject();

            module.put(DataConstants.TITLE, modules.get(i).getTitle());
            module.put(DataConstants.TOPIC, modules.get(i).getTopic());
            module.put(DataConstants.CONTENT, saveContent(modules.get(i).getAllContent()));
         
            jModules.add(module);
        }

        return jModules;
    
    }

    public static void saveData(CourseList courseList){
        JSONArray jCourses = new JSONArray();
        Course[] courses = courseList.toArray();

        for (int i = 0; i < courses.length; i++){
            JSONObject jCourse = new JSONObject();

            jCourse.put(DataConstants.COURSE_ID, courses[i].getCourseID().toString());
            jCourse.put(DataConstants.TITLE, courses[i].getTitle());
            jCourse.put(DataConstants.LANGUAGE, courses[i].getLanguage());
            jCourse.put(DataConstants.DESCRIPTION, courses[i].getDescription());
            jCourse.put(DataConstants.AUTHOR_ID, courses[i].getAuthorID().toString());
            jCourse.put(DataConstants.REVIEWS, saveReviews(courses[i].getAllReviews()));
            jCourse.put(DataConstants.COMMENTS, saveComments(courses[i].getAllComments()));
            jCourse.put(DataConstants.MODULES, saveModules(courses[i].getAllModules()));

            jCourses.add(jCourse);

        }

          // Write JSONArray to file
        try{
            FileWriter file = new FileWriter(DataConstants.COURSES_FILE_NAME);
            file.write(jCourses.toJSONString());
            file.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
