package attilaprojects.course.courseenrollment.enrollmentmanager;

import attilaprojects.course.courseenrollment.Enrollment;
import attilaprojects.course.courseenrollment.EnrollmentList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnrollmentManagerTest {

    private EnrollmentManager enrollmentManager;
    private EnrollmentList mockEnrollmentList;

    @BeforeEach
    public void setUp() {
        enrollmentManager = new EnrollmentManager();
        mockEnrollmentList = mock(EnrollmentList.class);
    }

    @Test
    public void testAddEnrollment_Success() {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "Math 101");
        ArrayList<Enrollment> emptyEnrollmentList = new ArrayList<>();

        try (MockedStatic<EnrollmentList> mockedStatic = mockStatic(EnrollmentList.class)) {
            mockedStatic.when(EnrollmentList::getInstance).thenReturn(mockEnrollmentList);
            when(mockEnrollmentList.getEnrollmentList()).thenReturn(emptyEnrollmentList);

            // When
            boolean result = enrollmentManager.addEnrollment(enrollment);

            // Then
            assertTrue(result);
            verify(mockEnrollmentList, times(1)).addEnrollment(enrollment);
        }
    }

    @Test
    public void testAddEnrollment_AlreadyExists() {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "Math 101");
        ArrayList<Enrollment> existingEnrollmentList = new ArrayList<>();
        existingEnrollmentList.add(new Enrollment("John Doe", "Math 101"));

        try (MockedStatic<EnrollmentList> mockedStatic = mockStatic(EnrollmentList.class)) {
            mockedStatic.when(EnrollmentList::getInstance).thenReturn(mockEnrollmentList);
            when(mockEnrollmentList.getEnrollmentList()).thenReturn(existingEnrollmentList);

            // When
            boolean result = enrollmentManager.addEnrollment(enrollment);

            // Then
            assertFalse(result);
            verify(mockEnrollmentList, never()).addEnrollment(enrollment);
        }
    }

    @Test
    public void testRemoveEnrollment_Success() {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "Math 101");
        ArrayList<Enrollment> existingEnrollmentList = new ArrayList<>();
        existingEnrollmentList.add(enrollment);

        try (MockedStatic<EnrollmentList> mockedStatic = mockStatic(EnrollmentList.class)) {
            mockedStatic.when(EnrollmentList::getInstance).thenReturn(mockEnrollmentList);
            when(mockEnrollmentList.getEnrollmentList()).thenReturn(existingEnrollmentList);

            // When
            boolean result = enrollmentManager.removeEnrollment(enrollment);

            // Then
            assertTrue(result);
            verify(mockEnrollmentList, times(1)).removeEnrollment(enrollment);
        }
    }

    @Test
    public void testRemoveEnrollment_NotExists() {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "Math 101");
        ArrayList<Enrollment> emptyEnrollmentList = new ArrayList<>();

        try (MockedStatic<EnrollmentList> mockedStatic = mockStatic(EnrollmentList.class)) {
            mockedStatic.when(EnrollmentList::getInstance).thenReturn(mockEnrollmentList);
            when(mockEnrollmentList.getEnrollmentList()).thenReturn(emptyEnrollmentList);

            // When
            boolean result = enrollmentManager.removeEnrollment(enrollment);

            // Then
            assertFalse(result);
            verify(mockEnrollmentList, never()).removeEnrollment(enrollment);
        }
    }
}
