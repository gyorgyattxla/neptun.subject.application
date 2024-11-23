package attilaprojects.teacher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeacherListTest {

    private TeacherList teacherList;

    @BeforeEach
    public void setUp() {
        teacherList = TeacherList.getInstance();
        teacherList.getTeacherList().clear(); // Clear singleton state before each test
    }

    @Test
    public void testAddTeacher() {
        // Given
        Teacher teacher = new Teacher("John Doe", "password123", "T12345");

        // When
        teacherList.addTeacher(teacher);

        // Then
        assertTrue(teacherList.getTeacherList().contains(teacher));
    }

    @Test
    public void testRemoveTeacher() {
        // Given
        Teacher teacher = new Teacher("John Doe", "password123", "T12345");
        teacherList.addTeacher(teacher);

        // When
        teacherList.removeTeacher(teacher);

        // Then
        assertFalse(teacherList.getTeacherList().contains(teacher));
    }

    @Test
    public void testSaveTeacherList() throws IOException {
        // Given
        Teacher teacher = new Teacher("John Doe", "password123", "T12345");
        teacherList.addTeacher(teacher);
        String userHome = System.getProperty("user.home");
        File mockFile = new File(userHome + "/nsas-data/teachers.txt");


    }

    @Test
    public void testLoadTeacherList() throws IOException {
        // Given
        String userHome = System.getProperty("user.home");
        File mockFile = new File(userHome + "/nsas-data/teachers.txt");


    }
}
