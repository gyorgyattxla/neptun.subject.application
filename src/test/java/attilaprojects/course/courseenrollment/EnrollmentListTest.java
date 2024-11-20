package attilaprojects.course.courseenrollment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnrollmentListTest {

    private EnrollmentList enrollmentList;

    @BeforeEach
    public void setUp() {
        enrollmentList = EnrollmentList.getInstance();
        enrollmentList.getEnrollmentList().clear(); // Clear the singleton state before each test
    }

    @Test
    public void testAddEnrollment() {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "Math 101");

        // When
        enrollmentList.addEnrollment(enrollment);

        // Then
        assertTrue(enrollmentList.getEnrollmentList().contains(enrollment));
    }

    @Test
    public void testRemoveEnrollment() {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "Math 101");
        enrollmentList.addEnrollment(enrollment);

        // When
        enrollmentList.removeEnrollment(enrollment);

        // Then
        assertFalse(enrollmentList.getEnrollmentList().contains(enrollment));
    }

    @Test
    public void testSaveEnrollmentList() throws IOException {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "Math 101");
        enrollmentList.addEnrollment(enrollment);
        String userHome = System.getProperty("user.home");
        File mockFile = new File(userHome + "/nsas-data/enrollments.txt");

        try (MockedStatic<System> mockedStatic = mockStatic(System.class)) {
            mockedStatic.when(() -> System.getProperty("user.home")).thenReturn(userHome);

            FileWriter mockWriter = mock(FileWriter.class);
            doNothing().when(mockWriter).write(anyString());
            doNothing().when(mockWriter).close();

            // Mock the FileWriter
            try (MockedStatic<FileWriter> mockFileWriterStatic = mockStatic(FileWriter.class)) {
                mockFileWriterStatic.when(() -> new FileWriter(mockFile)).thenReturn(mockWriter);

                // When
                boolean result = enrollmentList.saveEnrollmentList();

                // Then
                assertTrue(result);
                verify(mockWriter, times(1)).write("John Doe,Math 101\n");
                verify(mockWriter, times(1)).close();
            }
        }
    }

    @Test
    public void testLoadStudentList() throws IOException {
        // Given
        String userHome = System.getProperty("user.home");
        File mockFile = new File(userHome + "/nsas-data/enrollments.txt");
        String mockData = "John Doe,Math 101\nJane Smith,History 202";

        try (MockedStatic<System> mockedStatic = mockStatic(System.class)) {
            mockedStatic.when(() -> System.getProperty("user.home")).thenReturn(userHome);

            BufferedReader mockReader = mock(BufferedReader.class);
            when(mockReader.readLine())
                    .thenReturn("John Doe,Math 101", "Jane Smith,History 202", null);
            doNothing().when(mockReader).close();

            // Mock the BufferedReader
            try (MockedStatic<BufferedReader> mockBufferedReaderStatic = mockStatic(BufferedReader.class)) {
                mockBufferedReaderStatic.when(() -> new BufferedReader(new FileReader(mockFile)))
                        .thenReturn(mockReader);

                // When
                boolean result = enrollmentList.loadStudentList();

                // Then
                assertTrue(result);
                assertEquals(2, enrollmentList.getEnrollmentList().size());
                assertEquals("John Doe", enrollmentList.getEnrollmentList().get(0).getStudentName());
                assertEquals("Math 101", enrollmentList.getEnrollmentList().get(0).getCourseName());
            }
        }
    }
}
