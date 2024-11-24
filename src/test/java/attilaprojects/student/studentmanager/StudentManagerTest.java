package attilaprojects.student.studentmanager;

import attilaprojects.student.Student;
import attilaprojects.student.StudentList;
import attilaprojects.student.studentmanager.StudentManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentManagerTest {

    @Mock
    private StudentList studentListMock;  // Mock StudentList

    @InjectMocks
    private StudentManager studentManager;  // Class under test

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStudent_Success() {
        // Given
        String studentName = "JohnDoe";
        String studentPassword = "password123";
        String studentID = "12345";
        Student student = new Student(studentName, studentPassword, studentID);

        // Mock the behavior of getStudentList to return an empty list
        when(studentListMock.getStudentList()).thenReturn(new ArrayList<>());

        // When
        boolean result = studentManager.addStudent(student);

        // Then
        assertTrue(result);
        verify(studentListMock).addStudent(student);  // Ensure addStudent is called
    }

    @Test
    public void testAddStudent_AlreadyExists() {
        // Given
        String studentName = "JohnDoe";
        String studentPassword = "password123";
        String studentID = "12345";
        Student student = new Student(studentName, studentPassword, studentID);

        // Create a list with an existing student
        ArrayList<Student> mockList = new ArrayList<>();
        mockList.add(student);

        // Mock the behavior of getStudentList to return the list with the existing student
        when(studentListMock.getStudentList()).thenReturn(mockList);

        // When
        boolean result = studentManager.addStudent(student);

        // Then
        assertFalse(result);
        verify(studentListMock, never()).addStudent(student);  // Ensure addStudent is not called
    }

    @Test
    public void testRemoveStudent_Success() {
        // Given
        String studentName = "JohnDoe";
        String studentPassword = "password123";
        String studentID = "12345";
        Student student = new Student(studentName, studentPassword, studentID);

        // Create a list with the student to be removed
        ArrayList<Student> mockList = new ArrayList<>();
        mockList.add(student);
        when(studentListMock.getStudentList()).thenReturn(mockList);

        // When
        boolean result = studentManager.removeStudent(student);

        // Then
        assertTrue(result);
        verify(studentListMock).removeStudent(student);  // Ensure removeStudent is called
    }

    @Test
    public void testRemoveStudent_NotFound() {
        // Given
        String studentName = "JohnDoe";
        String studentPassword = "password123";
        String studentID = "12345";
        Student student = new Student(studentName, studentPassword, studentID);

        // Mock the behavior of getStudentList to return an empty list
        when(studentListMock.getStudentList()).thenReturn(new ArrayList<>());

        // When
        boolean result = studentManager.removeStudent(student);

        // Then
        assertFalse(result);
        verify(studentListMock, never()).removeStudent(student);  // Ensure removeStudent is not called
    }

    @Test
    public void testDoesStudentExist_Success() {
        // Given
        String studentName = "JohnDoe";
        String studentPassword = "password123";
        String studentID = "12345";
        Student student = new Student(studentName, studentPassword, studentID);

        // Create a list with the existing student
        ArrayList<Student> mockList = new ArrayList<>();
        mockList.add(student);
        when(studentListMock.getStudentList()).thenReturn(mockList);

        // When
        boolean result = studentManager.doesStudentExist("JohnDoe", "password123");

        // Then
        assertTrue(result);
    }

    @Test
    public void testDoesStudentExist_NotFound() {
        // Given
        when(studentListMock.getStudentList()).thenReturn(new ArrayList<>()); // Empty list

        // When
        boolean result = studentManager.doesStudentExist("JohnDoe", "password123");

        // Then
        assertFalse(result);
    }
}
