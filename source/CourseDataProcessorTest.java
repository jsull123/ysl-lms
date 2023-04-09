package source;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDataProcessorTest {

    private static ArrayList<Course> courses;
    private static CourseList courseList;

    @BeforeAll
    public static void oneTimeSetup() {
        courseList = CourseDataProcessor.loadData();
        courses = new ArrayList<>(Arrays.asList(courseList.toArray()));
    }

	@BeforeEach
	public void setup() { 
        courseList.clear();

        courseList.addCourse(new Course(
            UUID.randomUUID(),
            "Course 1",
            Language.fromString("Java"),
            "Course 1 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        courseList.addCourse(new Course(
            UUID.randomUUID(),
            "Course 2",
            Language.fromString("Python"),
            "Course 2 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        courseList.addCourse(new Course(
            UUID.randomUUID(),
            "Course 3",
            Language.fromString("C"),
            "Course 3 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        ));

        CourseDataProcessor.saveData(courseList);
        courseList.clear();
	}

    /*
    @AfterEach
    public void tearDown(){
        courseList.clear();
        courseList.add(courses);
        CourseDataProcessor.saveData(courseList);
    }*/

    @AfterAll
    public static void oneTimeTearDown(){
        courseList.clear();
        courseList.add(courses);
        CourseDataProcessor.saveData(courseList);
    }
	
	//assertEquals(val1,val2)
	//assertFalse(val)
	//assertTrue(val)
	//assertSame(val1,val2)
	//assertNotSame(val1,val2)
	//assertNull(val)
	//assertNotNull(val)
	
	@Test
	public void testSize() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(3, courseList.numCourses());
	}

    @Test
	public void testSizeZero() {
        CourseDataProcessor.saveData(courseList);
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(0, courseList.numCourses());
	}

    @Test
	public void testSizeAddOne() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(4, courseList.numCourses());
	}

    @Test
	public void testAddingCoursesWithSameID() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse(new Course(
            UUID.fromString("d7746cd1-79d7-4450-823b-49cf050a04fd"),
            "title",
            Language.fromString("C"),
            "description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        courseList.addCourse(new Course(
            UUID.fromString("d7746cd1-79d7-4450-823b-49cf050a04fd"),
            "title",
            Language.fromString("C"),
            "description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        ));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(5, courseList.numCourses());
	}

    @Test
	public void testAddCoursesTwice() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(6, courseList.numCourses());
	}

    @Test
	public void testNumberOfJava() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> javaCourses = courseList.getAllByLanguage("Java");
		assertEquals(1, javaCourses.size());
	}
   
    @Test
	public void testNumberOfJavaZero() {
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> javaCourses = courseList.getAllByLanguage("Java");
		assertEquals(0, javaCourses.size());
	}

    @Test
	public void testNumberOfJavaAddOne() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("Java"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> javaCourses = courseList.getAllByLanguage("Java");
		assertEquals(2, javaCourses.size());
	}

    @Test
    public void testNumberOfC() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> cCourses = courseList.getAllByLanguage("C");
		assertEquals(1, cCourses.size());
	}

    @Test
    public void testChangeTitle() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        Course[] newCourses = courseList.toArray();
        newCourses[0].setTitle("New title");
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        newCourses = courseList.toArray();
		assertEquals(newCourses[0].getTitle(), "New title");
	}

    @Test
    public void testChangeTitleNull() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        Course[] newCourses = courseList.toArray();
        newCourses[0].setTitle(null);
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        newCourses = courseList.toArray();
		assertNotNull(newCourses[0].getTitle());
	}

    @Test
    public void testNonExistentLanguage() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        Course[] newCourses = courseList.toArray();
        newCourses[0].setLanguage(Language.fromString(""));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        newCourses = courseList.toArray();
		assertEquals(newCourses[0].getLanguage(), Language.C);
	}

    @Test
    public void testNullSave() {
        CourseDataProcessor.saveData(null);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(3, courseList.numCourses());
	}

    @Test
	public void testNullReviews() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            null,
            new ArrayList<>(),
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(4, courseList.numCourses());
	}

    @Test
	public void testNullComments() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            null,
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(4, courseList.numCourses());
	}

    @Test
	public void testNullModules() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            null
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(4, courseList.numCourses());
	}

    @Test
	public void testNullCourseID() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            null,
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(4, courseList.numCourses());
	}

    @Test
	public void testNullLanguage() {
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            null,
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(4, courseList.numCourses());
	}

    @Test
	public void testCommentWithNullReplies() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(UUID.randomUUID(), "comment", new Date(), null));

        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            comments,
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> c = new ArrayList<>(Arrays.asList(courseList.toArray()));
		assertNotNull(c.get(3).getAllComments().get(0).getReplies());
	}

    @Test
	public void testCommentWithNullAuthorID() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(null, "comment", new Date(), new ArrayList<>()));

        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            comments,
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> c = new ArrayList<>(Arrays.asList(courseList.toArray()));
		assertNotNull(c.get(3).getAllComments().get(0).getAuthorID());
	}

    @Test
	public void testCommentWithNullComment() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(UUID.randomUUID(), null, new Date(), new ArrayList<>()));
        UUID id = UUID.randomUUID();

        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            id,
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            comments,
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        Course c = courseList.getCourse(id);
		assertNotNull(c.getAllComments().get(0).getComment());
	}

    @Test
	public void testCommentWithNullDate() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(UUID.randomUUID(), "comment", null, new ArrayList<>()));

        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            comments,
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> c = new ArrayList<>(Arrays.asList(courseList.toArray()));
		assertNotNull(c.get(3).getAllComments().get(0).getDateAdded());
	}

    @Test
	public void testCommentWithItselfAsReply() {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment(UUID.randomUUID(), "comment 1", new Date(), new ArrayList<>());
        comment.addReply(comment);
        comments.add(comment);

        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            comments,
            new ArrayList<>()
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> c = new ArrayList<>(Arrays.asList(courseList.toArray()));
        assertEquals(0, c.get(3).getAllComments().get(0).getReplies().size());
	}

    @Test
	public void testModuleWithNullLessons() {
        Quiz q = new Quiz(new ArrayList<>(), 0.0f);
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module("title", "topic", null, q, new ArrayList<>()));

        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            modules
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> c = new ArrayList<>(Arrays.asList(courseList.toArray()));
		assertEquals(0, c.get(3).getAllModules().get(0).getLessons().size());
	}

    @Test
	public void testModuleWithNullTitle() {
        Quiz q = new Quiz(new ArrayList<>(), 0.0f);
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module(null, "topic", new ArrayList<>(), q, new ArrayList<>()));

        UUID id = UUID.randomUUID();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            id,
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            modules
        )));

        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertNotNull(courseList.getCourse(id).getAllModules().get(0).getTitle());
	}

    @Test
	public void testModuleWithNullTopic() {
        Quiz q = new Quiz(new ArrayList<>(), 0.0f);
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module("title", null, new ArrayList<>(), q, new ArrayList<>()));

        UUID id = UUID.randomUUID();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            id,
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            modules
        )));

        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertNotNull(courseList.getCourse(id).getAllModules().get(0).getTopic());
	}

    @Test
	public void testModuleWithNullQuiz() {
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module("title", "topic", new ArrayList<>(), null, new ArrayList<>()));

        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            modules
        )));

        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> c = new ArrayList<>(Arrays.asList(courseList.toArray()));
		assertNotNull(c.get(3).getAllModules().get(0).getQuiz());
	}

    @Test
	public void testModuleQuizNullQuestions() {
        Quiz q = new Quiz(null, 0.0f);
        ArrayList<Module> modules = new ArrayList<>();
        modules.add(new Module("title", "topic", new ArrayList<>(), q, new ArrayList<>()));

        courseList.add(CourseDataProcessor.loadDataToArrayList());
        courseList.addCourse((new Course(
            UUID.randomUUID(),
            "Course 4",
            Language.fromString("C"),
            "Course 4 description",
            UUID.randomUUID(),
            new ArrayList<>(),
            new ArrayList<>(),
            modules
        )));

        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
        ArrayList<Course> c = new ArrayList<>(Arrays.asList(courseList.toArray()));
		assertNotNull(c.get(3).getAllModules().get(0).getQuiz().getQuestions());
	}
}