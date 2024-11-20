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

        try (MockedStatic<System> mockedStatic = mockStatic(System.class)) {
            mockedStatic.when(() -> System.getProperty("user.home")).thenReturn(userHome);

            FileWriter mockWriter = mock(FileWriter.class);
            doNothing().when(mockWriter).write(anyString());
            doNothing().when(mockWriter).close();

            // Mock FileWriter
            try (MockedStatic<FileWriter> mockFileWriterStatic = mockStatic(FileWriter.class)) {
                mockFileWriterStatic.when(() -> new FileWriter(mockFile)).thenReturn(mockWriter);

                // When
                boolean result = teacherList.saveTeacherList();

                // Then
                assertTrue(result);
                verify(mockWriter, times(1)).write("John Doe,password123,T12345\n");
                verify(mockWriter, times(1)).close();
            }
        }
    }

    @Test
    public void testLoadTeacherList() throws IOException {
        // Given
        String userHome = System.getProperty("user.home");
        File mockFile = new File(userHome + "/nsas-data/teachers.txt");

        try (MockedStatic<System> mockedStatic = mockStatic(System.class)) {
            mockedStatic.when(() -> System.getProperty("user.home")).thenReturn(userHome);

            BufferedReader mockReader = mock(BufferedReader.class);
            when(mockReader.readLine())
                    .thenReturn("John Doe,password123,T12345", "Jane Smith,secure456,T54321", null);
            doNothing().when(mockReader).close();

            // Mock BufferedReader
            try (MockedStatic<BufferedReader> mockBufferedReaderStatic = mockStatic(BufferedReader.class)) {
                mockBufferedReaderStatic.when(() -> new BufferedReader(new FileReader(mockFile)))
                        .thenReturn(mockReader);

                // When
                boolean result = teacherList.loadTeacherList();

                // Then
                assertTrue(result);
                assertEquals(2, teacherList.getTeacherList().size());
                assertEquals("John Doe", teacherList.getTeacherList().get(0).getTeacherName());
                assertEquals("password123", teacherList.getTeacherList().get(0).getTeacherPassword());
            }
        }
    }
}
