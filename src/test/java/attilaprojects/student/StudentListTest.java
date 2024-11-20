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

        try (MockedStatic<System> mockedStatic = mockStatic(System.class)) {
            mockedStatic.when(() -> System.getProperty("user.home")).thenReturn(userHome);

            FileWriter mockWriter = mock(FileWriter.class);
            doNothing().when(mockWriter).write(anyString());
            doNothing().when(mockWriter).close();

            // Mock FileWriter
            try (MockedStatic<FileWriter> mockFileWriterStatic = mockStatic(FileWriter.class)) {
                mockFileWriterStatic.when(() -> new FileWriter(mockFile)).thenReturn(mockWriter);

                // When
                boolean result = studentList.saveStudentList();

                // Then
                assertTrue(result);
                verify(mockWriter, times(1)).write("Alice,password123,S12345\n");
                verify(mockWriter, times(1)).close();
            }
        }
    }

    @Test
    public void testLoadStudentList() throws IOException {
        // Given
        String userHome = System.getProperty("user.home");
        File mockFile = new File(userHome + "/nsas-data/students.txt");
        String mockData = "Alice,password123,S12345\nBob,securepass456,S54321";

        try (MockedStatic<System> mockedStatic = mockStatic(System.class)) {
            mockedStatic.when(() -> System.getProperty("user.home")).thenReturn(userHome);

            BufferedReader mockReader = mock(BufferedReader.class);
            when(mockReader.readLine())
                    .thenReturn("Alice,password123,S12345", "Bob,securepass456,S54321", null);
            doNothing().when(mockReader).close();

            // Mock BufferedReader
            try (MockedStatic<BufferedReader> mockBufferedReaderStatic = mockStatic(BufferedReader.class)) {
                mockBufferedReaderStatic.when(() -> new BufferedReader(new FileReader(mockFile)))
                        .thenReturn(mockReader);

                // When
                boolean result = studentList.loadStudentList();

                // Then
                assertTrue(result);
                assertEquals(2, studentList.getStudentList().size());
                assertEquals("Alice", studentList.getStudentList().get(0).getStudentName());
                assertEquals("password123", studentList.getStudentList().get(0).getStudentPassword());
            }
        }
    }
}
