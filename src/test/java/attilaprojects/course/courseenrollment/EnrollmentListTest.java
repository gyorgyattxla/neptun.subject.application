package attilaprojects.course.courseenrollment;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.*;
import java.util.ArrayList;

public class EnrollmentListTest {

    @Mock
    private ArrayList<Enrollment> enrollmentListMock; // Mock for the internal list

    @InjectMocks
    private EnrollmentList enrollmentList; // Class under test

    private final String userHome = System.getProperty("user.home");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        enrollmentList = new EnrollmentList(); // New instance for each test
    }

    @Test
    public void testAddEnrollment_Success() {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "Math");

        // When
        enrollmentList.addEnrollment(enrollment);

        // Then
        assertTrue(enrollmentList.getEnrollmentList().contains(enrollment));
    }

    @Test
    public void testRemoveEnrollment_Success() {
        // Given
        Enrollment enrollment = new Enrollment("Jane Doe", "Science");
        enrollmentList.getEnrollmentList().add(enrollment);

        // When
        enrollmentList.removeEnrollment(enrollment);

        // Then
        assertFalse(enrollmentList.getEnrollmentList().contains(enrollment));
    }

    @Test
    public void testSaveEnrollmentList_Success() throws IOException {
        // Given
        Enrollment enrollment = new Enrollment("John Doe", "History");
        enrollmentList.getEnrollmentList().add(enrollment);

        String directoryPath = userHome + "/nsas-data";
        String filePath = directoryPath + "/enrollments.txt";

        File directory = new File(directoryPath);
        File file = new File(filePath);
        directory.mkdirs(); // Ensure directory exists

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(""); // Clear existing content
        }

        // When
        boolean result = enrollmentList.saveEnrollmentList();

        // Then
        assertTrue(result);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    public void testLoadEnrollmentList_FileExists() throws IOException {
        // Given
        String directoryPath = userHome + "/nsas-data";
        String filePath = directoryPath + "/enrollments.txt";

        File directory = new File(directoryPath);
        File file = new File(filePath);
        directory.mkdirs(); // Ensure directory exists

        // Create a mock file with test data
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("John Doe,Math\nJane Doe,Science\n");
        }

        // When
        boolean result = enrollmentList.loadStudentList();

        // Then
        assertTrue(result);
        assertEquals(2, enrollmentList.getEnrollmentList().size());
    }

    @Test
    public void testLoadEnrollmentList_FileNotExists() {
        // Given
        String directoryPath = userHome + "/nsas-data";
        String filePath = directoryPath + "/enrollments.txt";

        File file = new File(filePath);
        file.delete(); // Ensure file does not exist

        // When
        boolean result = enrollmentList.loadStudentList();

        // Then
        assertFalse(result);
    }
}
