package source;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDataProcessorTest {

    private static ArrayList<Course> courses;
    private static CourseList courseList;
	
    @BeforeClass
	public static void oneTimeSetup() {
        courseList = CourseDataProcessor.loadData();
        courses = new ArrayList<>(Arrays.asList(courseList.toArray()));
	}
	
	@AfterClass
	public static void oneTimeTearDown() {
        CourseList courseList = CourseList.getInstance(null);
        courseList.clear();
        courseList.add(courses);
        CourseDataProcessor.saveData(courseList);
	}
	
	@BeforeEach
	public static void setup() {
        courseList.clear();

        courseList.addCourse(new Course(
            UUID.randomUUID(),
            "Course 1",
            Language.fromString("Java"),
            "Course 1 description",
            UUID.randomUUID(),
            null,
            null,
            null
        ));
        courseList.addCourse(new Course(
            UUID.randomUUID(),
            "Course 2",
            Language.fromString("Python"),
            "Course 2 description",
            UUID.randomUUID(),
            null,
            null,
            null
        ));
        courseList.addCourse(new Course(
            UUID.randomUUID(),
            "Course 3",
            Language.fromString("C"),
            "Course 3 description",
            UUID.randomUUID(),
            null,
            null,
            null
        ));

        CourseDataProcessor.saveData(courseList);
        courseList.clear();
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
            null,
            null,
            null
        )));
        CourseDataProcessor.saveData(courseList);
        courseList.clear();
        courseList.add(CourseDataProcessor.loadDataToArrayList());
		assertEquals(4, courseList.numCourses());
	}

    @Test
	public void testSizeDouble() {
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
            null,
            null,
            null
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
		assertNull(newCourses[0].getTitle());
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
}