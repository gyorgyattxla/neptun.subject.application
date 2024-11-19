package attilaprojects.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseListTest {

    private CourseList courseList;

    @BeforeEach
    void setUp() {
        // Reset the singleton instance and clear the course list before each test
        courseList = CourseList.getInstance();
        courseList.getCourseList().clear();
    }

    @Test
    void testAddCourse() {
        // Given
        Course course = new Course("Math101", "John Doe", 3, "08:00", "10:00");

        // When
        courseList.addCourse(course);

        // Then
        assertTrue(courseList.getCourseList().contains(course));
    }

    @Test
    void testRemoveCourse() {
        // Given
        Course course = new Course("Math101", "John Doe", 3, "08:00", "10:00");
        courseList.addCourse(course);

        // When
        courseList.removeCourse(course);

        // Then
        assertFalse(courseList.getCourseList().contains(course));
    }

    @Test
    void testSaveCourseList() throws IOException {
        // Given
        Course course1 = new Course("Math101", "John Doe", 3, "08:00", "10:00");
        Course course2 = new Course("CS102", "Jane Smith", 4, "10:00", "12:00");
        courseList.addCourse(course1);
        courseList.addCourse(course2);

        String userHome = System.getProperty("user.home");
        File expectedFile = new File(userHome + "/nsas-data/courses.txt");
        if (expectedFile.exists()) {
            expectedFile.delete(); // Clean up before testing
        }

        // When
        boolean result = courseList.saveCourseList();

        // Then
        assertTrue(result);
        assertTrue(expectedFile.exists());

        // Verify file content
        try (BufferedReader reader = new BufferedReader(new FileReader(expectedFile))) {
            assertEquals("Math101,John Doe,3,08:00,10:00", reader.readLine());
            assertEquals("CS102,Jane Smith,4,10:00,12:00", reader.readLine());
            assertNull(reader.readLine());
        }
    }

    @Test
    void testLoadCourseList() throws IOException {
        // Given
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data");
        if (!fileDirectory.exists()) {
            fileDirectory.mkdirs();
        }
        File courseFile = new File(fileDirectory + "/courses.txt");

        // Write sample data to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(courseFile))) {
            writer.write("Math101,John Doe,3,08:00,10:00\n");
            writer.write("CS102,Jane Smith,4,10:00,12:00\n");
        }

        // When
        boolean result = courseList.loadCourseList();

        // Then
        assertTrue(result);
        ArrayList<Course> loadedCourses = courseList.getCourseList();
        assertEquals(2, loadedCourses.size());

        Course course1 = loadedCourses.get(0);
        assertEquals("Math101", course1.getClassName());
        assertEquals("John Doe", course1.getClassTeacher());
        assertEquals(3, course1.getCreditNumber());
        assertEquals("08:00", course1.getClassStartTime());
        assertEquals("10:00", course1.getClassEndTime());

        Course course2 = loadedCourses.get(1);
        assertEquals("CS102", course2.getClassName());
        assertEquals("Jane Smith", course2.getClassTeacher());
        assertEquals(4, course2.getCreditNumber());
        assertEquals("10:00", course2.getClassStartTime());
        assertEquals("12:00", course2.getClassEndTime());
    }
}