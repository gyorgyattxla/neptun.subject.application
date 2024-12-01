package attilaprojects.course.coursemanager;

import attilaprojects.course.Course;
import attilaprojects.course.CourseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseManagerTest {

    private CourseManager courseManager;
    private CourseList mockCourseList;

    @BeforeEach
    public void setUp() {
        courseManager = new CourseManager();
        mockCourseList = mock(CourseList.class);
    }

    @Test
    public void testCreateCourse_Success() {
        // Given
        Course newCourse = new Course("Math 101","John Doe",6,"11:00","13:00");
        ArrayList<Course> emptyCourseList = new ArrayList<>();

        try (MockedStatic<CourseList> mockedStatic = mockStatic(CourseList.class)) {
            mockedStatic.when(CourseList::getInstance).thenReturn(mockCourseList);
            when(mockCourseList.getCourseList()).thenReturn(emptyCourseList);

            // When
            boolean result = courseManager.createCourse(newCourse);

            // Then
            assertTrue(result);
            verify(mockCourseList, times(1)).addCourse(newCourse);
        }
    }

    @Test
    public void testCreateCourse_DuplicateCourse() {
        // Given
        Course existingCourse = new Course("Math 101","John Doe",6,"11:00","13:00");
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(existingCourse);

        try (MockedStatic<CourseList> mockedStatic = mockStatic(CourseList.class)) {
            mockedStatic.when(CourseList::getInstance).thenReturn(mockCourseList);
            when(mockCourseList.getCourseList()).thenReturn(courseList);

            // When
            boolean result = courseManager.createCourse(existingCourse);

            // Then
            assertFalse(result);
            verify(mockCourseList, never()).addCourse(existingCourse);
        }
    }

    @Test
    public void testRemoveCourse_Success() {
        // Given
        Course existingCourse = new Course("Math 101","John Doe",6,"11:00","13:00");
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(existingCourse);

        try (MockedStatic<CourseList> mockedStatic = mockStatic(CourseList.class)) {
            mockedStatic.when(CourseList::getInstance).thenReturn(mockCourseList);
            when(mockCourseList.getCourseList()).thenReturn(courseList);

            // When
            boolean result = courseManager.removeCourse("Math 101");

            // Then
            assertTrue(result);
            verify(mockCourseList, times(1)).removeCourse(existingCourse);
        }
    }

    @Test
    public void testRemoveCourse_CourseDoesNotExist() {
        // Given
        ArrayList<Course> emptyCourseList = new ArrayList<>();

        try (MockedStatic<CourseList> mockedStatic = mockStatic(CourseList.class)) {
            mockedStatic.when(CourseList::getInstance).thenReturn(mockCourseList);
            when(mockCourseList.getCourseList()).thenReturn(emptyCourseList);

            // When
            boolean result = courseManager.removeCourse("Math 101");

            // Then
            assertFalse(result);
            verify(mockCourseList, never()).removeCourse(any(Course.class));
        }
    }
}
