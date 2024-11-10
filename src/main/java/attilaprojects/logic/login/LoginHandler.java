package attilaprojects.logic.login;

import attilaprojects.student.studentmanager.StudentManager;
import attilaprojects.teacher.teachermanager.TeacherManager;

public class LoginHandler implements LoginHandlerInterface{

    StudentManager studentManager = new StudentManager();
    TeacherManager teacherManager = new TeacherManager();
    @Override
    public boolean loginAsStudent(String studentName, String studentPassword) {
        boolean studentExist = studentManager.doesStudentExist(studentName, studentPassword);
        return studentExist;
    }

    @Override
    public boolean loginAsTeacher(String teacherName, String teacherPassword) {
        boolean teacherExist = teacherManager.doesTeacherExist(teacherName, teacherPassword);
        return teacherExist;
    }
}
