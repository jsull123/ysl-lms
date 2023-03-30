package source;

import java.io.FileReader;
import java.io.FileWriter;
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

    private static ArrayList<String> loadLessons(JSONArray jLessons) {
        ArrayList<String> lessons = new ArrayList<>();

        for (int i = 0; i < jLessons.size(); i++){
            JSONObject jLesson = (JSONObject)jLessons.get(i);
            lessons.add((String)jLesson.get(DataConstants.LESSON));
        }          
        return lessons;
    }
    
    private static Quiz loadQuiz(JSONObject jQuiz) {
        ArrayList<Question> questions = loadQuestions((JSONArray)jQuiz.get(DataConstants.QUESTIONS));
        float passingGrade = Float.valueOf((String)jQuiz.get(DataConstants.PASSING_GRADE));

        return new Quiz(questions, passingGrade);
    }

    private static ArrayList<Module> loadModules(JSONArray jModules) {
        ArrayList<Module> modules = new ArrayList<>();
    
        for (int m = 0; m < jModules.size(); m++){
            JSONObject moduleObject = (JSONObject)jModules.get(m);

            Module module = new Module(
                (String)moduleObject.get(DataConstants.TITLE),
                (String)moduleObject.get(DataConstants.TOPIC),
                loadLessons((JSONArray)moduleObject.get(DataConstants.LESSONS)),
                loadQuiz((JSONObject)moduleObject.get(DataConstants.QUIZ)),
                loadComments((JSONArray)moduleObject.get(DataConstants.COMMENTS))
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
                    Language.fromString((String)jCourse.get(DataConstants.LANGUAGE)), 
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

            jReview.put(DataConstants.AUTHOR_ID, reviews.get(i).getAuthor().getID().toString());
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
            question.put(DataConstants.CORRECT_ANSWER, Integer.valueOf(questions.get(i).getCorrectAnswer()).toString());

            jQuestions.add(question);
        }

        return jQuestions;
    }

    private static JSONObject saveQuiz(Quiz quiz){
        JSONObject jQuiz = new JSONObject();
    
        jQuiz.put(DataConstants.QUESTIONS, saveQuestions(quiz.getQuestions()));
        jQuiz.put(DataConstants.PASSING_GRADE, Float.valueOf(quiz.getPassingGrade()).toString());

        return jQuiz;
    }

    private static JSONArray saveLessons(ArrayList<String> lessons){
        JSONArray jLessons = new JSONArray();

        for (int i = 0; i < lessons.size(); i++){
            JSONObject jObject = new JSONObject();
            jObject.put(DataConstants.LESSON, lessons.get(i));
            jLessons.add(jObject);
        }

        return jLessons;
    }

    private static JSONArray saveModules(ArrayList<Module> modules){
        JSONArray jModules = new JSONArray();

        for (int i = 0; i < modules.size(); i++){
            JSONObject module = new JSONObject();

            module.put(DataConstants.TITLE, modules.get(i).getTitle());
            module.put(DataConstants.TOPIC, modules.get(i).getTopic());
            module.put(DataConstants.LESSONS, saveLessons(modules.get(i).getLessons()));
            module.put(DataConstants.QUIZ, saveQuiz(modules.get(i).getQuiz()));
            module.put(DataConstants.COMMENTS, saveComments(modules.get(i).getAllComments()));
         
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
            jCourse.put(DataConstants.LANGUAGE, courses[i].getLanguage().toString());
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
