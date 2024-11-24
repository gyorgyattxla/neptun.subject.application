package attilaprojects.logic.register;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import attilaprojects.logic.register.RegisterHandler;
import attilaprojects.student.Student;
import attilaprojects.student.studentmanager.StudentManager;
import attilaprojects.teacher.Teacher;
import attilaprojects.teacher.teachermanager.TeacherManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class RegisterHandlerTest {

    @Mock
    private StudentManager studentManagerMock;

    @Mock
    private TeacherManager teacherManagerMock;

    @InjectMocks
    private RegisterHandler registerHandler; // Class under test

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterStudent_Success() {
        // Given
        String username = "testStudent";
        String password = "testPassword";
        Student student = new Student(username, password, "0001A");

        when(studentManagerMock.addStudent(any(Student.class))).thenReturn(true);

        // When
        boolean result = registerHandler.registerStudent(username, password);

        // Then
        assertTrue(result);
        verify(studentManagerMock).addStudent(argThat(s ->
                s.getStudentName().equals(username) &&
                        s.getStudentPassword().equals(password) &&
                        s.getStudentID().equals("0001A")
        ));
    }

    @Test
    public void testRegisterStudent_Failure_AlreadyExists() {
        // Given
        String username = "testStudent";
        String password = "testPassword";

        when(studentManagerMock.addStudent(any(Student.class))).thenReturn(false);

        // When
        boolean result = registerHandler.registerStudent(username, password);

        // Then
        assertFalse(result);
        verify(studentManagerMock).addStudent(any(Student.class));
    }

    @Test
    public void testRegisterTeacher_Success() {
        // Given
        String username = "testTeacher";
        String password = "testPassword";
        Teacher teacher = new Teacher(username, password, "0001A");

        when(teacherManagerMock.addTeacher(any(Teacher.class))).thenReturn(true);

        // When
        boolean result = registerHandler.registerTeacher(username, password);

        // Then
        assertTrue(result);
        verify(teacherManagerMock).addTeacher(argThat(t ->
                t.getTeacherName().equals(username) &&
                        t.getTeacherPassword().equals(password) &&
                        t.getTeacherID().equals("0001A")
        ));
    }

    @Test
    public void testRegisterTeacher_Failure_AlreadyExists() {
        // Given
        String username = "testTeacher";
        String password = "testPassword";

        when(teacherManagerMock.addTeacher(any(Teacher.class))).thenReturn(false);

        // When
        boolean result = registerHandler.registerTeacher(username, password);

        // Then
        assertFalse(result);
        verify(teacherManagerMock).addTeacher(any(Teacher.class));
    }

    @Test
    public void testRegisterStudent_Failure_IDIncrementFail() {
        // Given
        String username = "testStudent";
        String password = "testPassword";
        Student student = new Student(username, password, "0001A");

        when(studentManagerMock.addStudent(any(Student.class))).thenReturn(true);
        doAnswer(invocation -> {
            // Simulate ID incrementer failure after adding student
            registerHandler.idIncrementer();
            return false;
        }).when(studentManagerMock).removeStudent(student);

        // When
        boolean result = registerHandler.registerStudent(username, password);

        // Then
        assertFalse(result);
        verify(studentManagerMock).addStudent(any(Student.class));
        verify(studentManagerMock).removeStudent(any(Student.class));
    }
}

