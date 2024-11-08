package attilaprojects.student.studentmanager;

import attilaprojects.student.Student;

public interface StudentManagerInterface {
    /*** registerStudent method will add a Student object into StudentList ***/
    public boolean addStudent(Student student);

    /*** removeStudent method will remove a Student object from StudentList ***/
    public boolean removeStudent(Student student);
}
