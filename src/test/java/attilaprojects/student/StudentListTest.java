package attilaprojects.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentListTest {

    private StudentList studentList;

    @BeforeEach
    public void setUp() {
        studentList = StudentList.getInstance();
        studentList.getStudentList().clear(); // Clear singleton state before each test
    }

    @Test
    public void testAddStudent() {
        // Given
        Student student = new Student("Alice", "password123", "S12345");

        // When
        studentList.addStudent(student);

        // Then
        assertTrue(studentList.getStudentList().contains(student));
    }

    @Test
    public void testRemoveStudent() {
        // Given
        Student student = new Student("Alice", "password123", "S12345");
        studentList.addStudent(student);

        // When
        studentList.removeStudent(student);

        // Then
        assertFalse(studentList.getStudentList().contains(student));
    }

    @Test
    public void testSaveStudentList() throws IOException {
        // Given
        Student student = new Student("Alice", "password123", "S12345");
        studentList.addStudent(student);
        String userHome = System.getProperty("user.home");
        File mockFile = new File(userHome + "/nsas-data/students.txt");



    }

    @Test
    public void testLoadStudentList() throws IOException {
        // Given
        String userHome = System.getProperty("user.home");
        File mockFile = new File(userHome + "/nsas-data/students.txt");
        String mockData = "Alice,password123,S12345\nBob,securepass456,S54321";


    }
}
