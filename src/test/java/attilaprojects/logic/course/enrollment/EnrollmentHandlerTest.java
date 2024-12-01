package attilaprojects.logic.course.enrollment;

import attilaprojects.course.Course;
import attilaprojects.course.CourseList;
import attilaprojects.course.courseenrollment.Enrollment;
import attilaprojects.course.courseenrollment.EnrollmentList;
import attilaprojects.course.courseenrollment.enrollmentmanager.EnrollmentManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnrollmentHandlerTest {

    private EnrollmentHandler enrollmentHandler;
    private EnrollmentManager mockEnrollmentManager;
    private EnrollmentList mockEnrollmentList;
    private CourseList mockCourseList;

    @BeforeEach
    public void setUp() {
        mockEnrollmentManager = mock(EnrollmentManager.class);
        enrollmentHandler = new EnrollmentHandler();
        enrollmentHandler.enrollmentManager = mockEnrollmentManager;

        mockEnrollmentList = mock(EnrollmentList.class);
        mockCourseList = mock(CourseList.class);
    }

    @Test
    public void testAddEnrollment_Success() {
        // Given
        String studentName = "John Doe";
        String courseName = "Math 101";
        Enrollment enrollment = new Enrollment(studentName, courseName);

        when(mockEnrollmentManager.addEnrollment(enrollment)).thenReturn(true);

        // When
        boolean result = enrollmentHandler.addEnrollment(studentName, courseName);

        // Then
        assertTrue(result);
        verify(mockEnrollmentManager, times(1)).addEnrollment(enrollment);
    }

    @Test
    public void testAddEnrollment_Failure() {
        // Given
        String studentName = "John Doe";
        String courseName = "Math 101";
        Enrollment enrollment = new Enrollment(studentName, courseName);

        when(mockEnrollmentManager.addEnrollment(enrollment)).thenReturn(false);

        // When
        boolean result = enrollmentHandler.addEnrollment(studentName, courseName);

        // Then
        assertFalse(result);
        verify(mockEnrollmentManager, times(1)).addEnrollment(enrollment);
    }

    @Test
    public void testRemoveEnrollment_StudentNotEnrolled() {
        // Given
        String studentName = "John Doe";
        String courseName = "Math 101";
        ArrayList<Enrollment> enrollmentList = new ArrayList<>();

        try (MockedStatic<EnrollmentList> mockedStatic = mockStatic(EnrollmentList.class)) {
            mockedStatic.when(EnrollmentList::getInstance).thenReturn(mockEnrollmentList);
            when(mockEnrollmentList.getEnrollmentList()).thenReturn(enrollmentList);

            // When
            boolean result = enrollmentHandler.removeEnrollment(studentName, courseName);

            // Then
            assertFalse(result);
            verify(mockEnrollmentList, never()).removeEnrollment(any(Enrollment.class));
        }
    }

    @Test
    public void testShowStudentCourses_StudentEnrolled() {
        // Given
        String studentName = "John Doe";
        Course course = new Course("Math 101","Johhny Cage",6,"11:00","13:00");
        Enrollment enrollment = new Enrollment(studentName, "Math 101");

        ArrayList<Enrollment> enrollmentList = new ArrayList<>();
        enrollmentList.add(enrollment);

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course);

        try (MockedStatic<EnrollmentList> enrollmentStatic = mockStatic(EnrollmentList.class);
             MockedStatic<CourseList> courseStatic = mockStatic(CourseList.class)) {

            enrollmentStatic.when(EnrollmentList::getInstance).thenReturn(mockEnrollmentList);
            when(mockEnrollmentList.getEnrollmentList()).thenReturn(enrollmentList);

            courseStatic.when(CourseList::getInstance).thenReturn(mockCourseList);
            when(mockCourseList.getCourseList()).thenReturn(courseList);

            // When
            ArrayList<Course> result = enrollmentHandler.showStudentCourses(studentName);

            // Then
            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(course, result.get(0));
        }
    }

    @Test
    public void testShowStudentCourses_StudentNotEnrolled() {
        // Given
        String studentName = "John Doe";
        ArrayList<Enrollment> enrollmentList = new ArrayList<>();
        ArrayList<Course> courseList = new ArrayList<>();

        try (MockedStatic<EnrollmentList> enrollmentStatic = mockStatic(EnrollmentList.class);
             MockedStatic<CourseList> courseStatic = mockStatic(CourseList.class)) {

            enrollmentStatic.when(EnrollmentList::getInstance).thenReturn(mockEnrollmentList);
            when(mockEnrollmentList.getEnrollmentList()).thenReturn(enrollmentList);

            courseStatic.when(CourseList::getInstance).thenReturn(mockCourseList);
            when(mockCourseList.getCourseList()).thenReturn(courseList);

            // When
            ArrayList<Course> result = enrollmentHandler.showStudentCourses(studentName);

            // Then
            assertNull(result);
        }
    }
}
