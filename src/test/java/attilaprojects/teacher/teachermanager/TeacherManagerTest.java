package attilaprojects.teacher.teachermanager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import attilaprojects.teacher.Teacher;
import attilaprojects.teacher.TeacherList;
import attilaprojects.teacher.teachermanager.TeacherManager;

import java.util.ArrayList;

public class TeacherManagerTest {

    @Mock
    private TeacherList teacherListMock; // Mock TeacherList to simulate storage

    @InjectMocks
    private TeacherManager teacherManager; // Class under test

    private ArrayList<Teacher> teacherList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare a mocked teacher list
        teacherList = new ArrayList<>();
        when(teacherListMock.getTeacherList()).thenReturn(teacherList);

        // Simulate TeacherList.getInstance returning the mock
        TeacherList.instance = teacherListMock; // Replace singleton instance for testing
    }

    @Test
    public void testAddTeacher_Success() {
        // Given
        Teacher teacher = new Teacher("Mr. Smith", "password123", "T001");

        // When
        boolean result = teacherManager.addTeacher(teacher);

        // Then
        assertTrue(result); // Teacher should be added
        verify(teacherListMock).addTeacher(teacher); // Ensure addTeacher was called
    }

    @Test
    public void testAddTeacher_AlreadyRegistered() {
        // Given
        Teacher teacher = new Teacher("Mr. Smith", "password123", "T001");
        teacherList.add(teacher); // Simulate teacher already in the list

        // When
        boolean result = teacherManager.addTeacher(teacher);

        // Then
        assertFalse(result); // Teacher should not be added
        verify(teacherListMock, never()).addTeacher(teacher); // Ensure addTeacher was not called
    }

    @Test
    public void testRemoveTeacher_Success() {
        // Given
        Teacher teacher = new Teacher("Mr. Smith", "password123", "T001");
        teacherList.add(teacher); // Add teacher to list

        // When
        boolean result = teacherManager.removeTeacher(teacher);

        // Then
        assertTrue(result); // Teacher should be removed
        verify(teacherListMock).removeTeacher(teacher); // Ensure removeTeacher was called
    }

    @Test
    public void testRemoveTeacher_NotExists() {
        // Given
        Teacher teacher = new Teacher("Mr. Smith", "password123", "T001");

        // When
        boolean result = teacherManager.removeTeacher(teacher);

        // Then
        assertFalse(result); // Teacher should not be removed
        verify(teacherListMock, never()).removeTeacher(teacher); // Ensure removeTeacher was not called
    }

    @Test
    public void testDoesTeacherExist_Exists() {
        // Given
        Teacher teacher = new Teacher("Mr. Smith", "password123", "T001");
        teacherList.add(teacher); // Add teacher to the list

        // When
        boolean result = teacherManager.doesTeacherExist("Mr. Smith", "password123");

        // Then
        assertTrue(result); // Teacher should exist
        verify(teacherListMock).getTeacherList(); // Ensure getTeacherList was called
    }

    @Test
    public void testDoesTeacherExist_NotExists() {
        // Given
        Teacher teacher = new Teacher("Mr. Smith", "password123", "T001");

        // When
        boolean result = teacherManager.doesTeacherExist("NonExistent", "wrongPassword");

        // Then
        assertFalse(result); // Teacher should not exist
        verify(teacherListMock).getTeacherList(); // Ensure getTeacherList was called
    }
}
