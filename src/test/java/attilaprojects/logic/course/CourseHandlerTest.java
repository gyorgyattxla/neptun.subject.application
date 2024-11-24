package attilaprojects.logic.course;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import attilaprojects.logic.course.CourseHandler;
import attilaprojects.course.Course;
import attilaprojects.course.coursemanager.CourseManager;
import attilaprojects.course.CourseList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;

public class CourseHandlerTest {

    @Mock
    private CourseManager courseManagerMock;

    @InjectMocks
    private CourseHandler courseHandler; // Class under test

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCourse_Success() {
        // Given
        String courseName = "Math101";
        String courseTeacher = "Mr. Smith";
        int creditNumber = 4;
        String courseStartTime = "09:00";
        String courseEndTime = "10:30";

        Course course = new Course(courseName, courseTeacher, creditNumber, courseStartTime, courseEndTime);
        when(courseManagerMock.createCourse(any(Course.class))).thenReturn(true);

        // When
        boolean result = courseHandler.addCourse(courseName, courseTeacher, creditNumber, courseStartTime, courseEndTime);

        // Then
        assertTrue(result);
        verify(courseManagerMock).createCourse(argThat(c ->
                c.getClassName().equals(courseName) &&
                        c.getClassTeacher().equals(courseTeacher) &&
                        c.getCreditNumber() == creditNumber &&
                        c.getClassStartTime().equals(courseStartTime) &&
                        c.getClassEndTime().equals(courseEndTime)
        ));
    }

    @Test
    public void testAddCourse_Failure_AlreadyExists() {
        // Given
        String courseName = "Math101";
        String courseTeacher = "Mr. Smith";
        int creditNumber = 4;
        String courseStartTime = "09:00";
        String courseEndTime = "10:30";

        when(courseManagerMock.createCourse(any(Course.class))).thenReturn(false);

        // When
        boolean result = courseHandler.addCourse(courseName, courseTeacher, creditNumber, courseStartTime, courseEndTime);

        // Then
        assertFalse(result);
        verify(courseManagerMock).createCourse(any(Course.class));
    }

    @Test
    public void testRemoveCourse_Success() {
        // Given
        String courseName = "Math101";
        when(courseManagerMock.removeCourse(courseName)).thenReturn(true);

        // When
        boolean result = courseHandler.removeCourse(courseName);

        // Then
        assertTrue(result);
        verify(courseManagerMock).removeCourse(courseName);
    }

    @Test
    public void testRemoveCourse_Failure_DoesNotExist() {
        // Given
        String courseName = "Math101";
        when(courseManagerMock.removeCourse(courseName)).thenReturn(false);

        // When
        boolean result = courseHandler.removeCourse(courseName);

        // Then
        assertFalse(result);
        verify(courseManagerMock).removeCourse(courseName);
    }

    @Test
    public void testShowTeacherCourses_Success() {
        // Given
        String teacherName = "Mr. Smith";
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Math101", teacherName, 4, "09:00", "10:30"));
        courseList.add(new Course("Physics101", teacherName, 3, "11:00", "12:30"));

        CourseList.getInstance().getCourseList().addAll(courseList);

        // When
        ArrayList<Course> result = courseHandler.showTeacherCourses(teacherName);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(c -> c.getClassTeacher().equals(teacherName)));
    }

    @Test
    public void testShowTeacherCourses_NoCoursesFound() {
        // Given
        String teacherName = "NonExistentTeacher";
        CourseList.getInstance().getCourseList().clear();

        // When
        ArrayList<Course> result = courseHandler.showTeacherCourses(teacherName);

        // Then
        assertNull(result);
    }
}

