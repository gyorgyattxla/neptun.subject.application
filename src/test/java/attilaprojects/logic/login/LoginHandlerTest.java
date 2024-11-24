package attilaprojects.logic.login;

import attilaprojects.logic.login.LoginHandler;
import attilaprojects.student.studentmanager.StudentManager;
import attilaprojects.teacher.teachermanager.TeacherManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginHandlerTest {

    @Mock
    private StudentManager studentManagerMock;

    @Mock
    private TeacherManager teacherManagerMock;

    @InjectMocks
    private LoginHandler loginHandler; // Class under test

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginAsStudent_Success() {
        // Given
        String studentName = "JohnDoe";
        String studentPassword = "password123";
        when(studentManagerMock.doesStudentExist(studentName, studentPassword)).thenReturn(true);

        // When
        boolean result = loginHandler.loginAsStudent(studentName, studentPassword);

        // Then
        assertTrue(result);
        verify(studentManagerMock).doesStudentExist(studentName, studentPassword);
    }

    @Test
    public void testLoginAsStudent_Failure() {
        // Given
        String studentName = "JohnDoe";
        String studentPassword = "wrongpassword";
        when(studentManagerMock.doesStudentExist(studentName, studentPassword)).thenReturn(false);

        // When
        boolean result = loginHandler.loginAsStudent(studentName, studentPassword);

        // Then
        assertFalse(result);
        verify(studentManagerMock).doesStudentExist(studentName, studentPassword);
    }

    @Test
    public void testLoginAsTeacher_Success() {
        // Given
        String teacherName = "JaneDoe";
        String teacherPassword = "securepassword";
        when(teacherManagerMock.doesTeacherExist(teacherName, teacherPassword)).thenReturn(true);

        // When
        boolean result = loginHandler.loginAsTeacher(teacherName, teacherPassword);

        // Then
        assertTrue(result);
        verify(teacherManagerMock).doesTeacherExist(teacherName, teacherPassword);
    }

    @Test
    public void testLoginAsTeacher_Failure() {
        // Given
        String teacherName = "JaneDoe";
        String teacherPassword = "wrongpassword";
        when(teacherManagerMock.doesTeacherExist(teacherName, teacherPassword)).thenReturn(false);

        // When
        boolean result = loginHandler.loginAsTeacher(teacherName, teacherPassword);

        // Then
        assertFalse(result);
        verify(teacherManagerMock).doesTeacherExist(teacherName, teacherPassword);
    }
}

