package source;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CourseDataProcessor {
    /**
     * @return The json array with the course data saved on it
     */
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
    /**
     * @return The list of comments
     * @param JSONArray comments = The comments that were on the json file
     */
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
    /**
     * @return List of reviews that are save do the JSON file
     * @param JSONArray
     */
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
    /**
     * @return All the lessons that are saved to the JSON file
     * @param JSONArray jLessons = The lessons in the JSON
     */
    private static ArrayList<String> loadLessons(JSONArray jLessons) {
        ArrayList<String> lessons = new ArrayList<>();

        for (int i = 0; i < jLessons.size(); i++){
            JSONObject jLesson = (JSONObject)jLessons.get(i);
            lessons.add((String)jLesson.get(DataConstants.LESSON));
        }          
        return lessons;
    }
    /**
     * @return The Quiz from the json that the user is about to take
     * @param JSONObject jQuiz = The quiz saved to json
     */
    private static Quiz loadQuiz(JSONObject jQuiz) {
        ArrayList<Question> questions = loadQuestions((JSONArray)jQuiz.get(DataConstants.QUESTIONS));
        float passingGrade = Float.valueOf((String)jQuiz.get(DataConstants.PASSING_GRADE));

        return new Quiz(questions, passingGrade);
    }
    /**
     * @return List of the modules saved to the JSON file
     * @param JSONArray jModules = The modules saved to the JSON
     */
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
     /**
     * @return List of the answers to questions saved to the JSON file
     * @param JSONArray jAnswers = The answers/questions saved to the JSON
     */
    private static ArrayList<String> loadAnswers(JSONArray jAnswers){
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < jAnswers.size(); i++){
            answers.add((String)((JSONObject)jAnswers.get(i)).get(DataConstants.ANSWER));
        }

        return answers;
    }
     /**
     * @return List of the questions saved to the JSON file
     * @param JSONArray jQuestions = The questions saved to the JSON
     */
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
    /**
     * @return The data of a course (title, language, etc.)
     */

    public static CourseList loadData() {
        return CourseList.getInstance(loadDataToArrayList());
    }

    public static ArrayList<Course> loadDataToArrayList() {
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
    
        return courses;
    }
     /**
     * @return List of the reviews to be saved to the JSON file
     * @param JSONArray jModules = The reviews that need to be saved to the JSON
     */
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
     /**
     * @return List of the answers entered by the user saved to the JSON file
     * @param JSONArray jModules = The answers to be saved to the JSON
     */
    private static JSONArray saveAnswers(ArrayList<String> answers){
        JSONArray jAnswers = new JSONArray();

        for (int i = 0; i < answers.size(); i++){
            JSONObject answer = new JSONObject();

            answer.put(DataConstants.ANSWER, answers.get(i));

            jAnswers.add(answer);
        }

        return jAnswers;
    }
    /**
     * @return Array of new questions to be saved to the JSON file
     * @param ArrayList<Question> questions = The questions to be saved
     */
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
    /**
     * @return a quiz that has been saved to JSON
     * @param Quiz quiz: The quiz to be saved
     */
    private static JSONObject saveQuiz(Quiz quiz){
        JSONObject jQuiz = new JSONObject();
    
        jQuiz.put(DataConstants.QUESTIONS, saveQuestions(quiz.getQuestions()));
        jQuiz.put(DataConstants.PASSING_GRADE, Float.valueOf(quiz.getPassingGrade()).toString());

        return jQuiz;
    }
    /**
     * @return a JSON array of new lessons
     * @param ArrayList<String> lessons = The lessons to be added
     */
    private static JSONArray saveLessons(ArrayList<String> lessons){
        JSONArray jLessons = new JSONArray();

        for (int i = 0; i < lessons.size(); i++){
            JSONObject jObject = new JSONObject();
            jObject.put(DataConstants.LESSON, lessons.get(i));
            jLessons.add(jObject);
        }

        return jLessons;
    }
    /**
     * @return a JSON array with new modules save in it
     * @param ArrayList<Module> modules = the new module to be saved to JSON
     */
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
    /**
     * Saves all new data set
     * @param CourseList courseList = object for the list we want to add the data to
     */
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
