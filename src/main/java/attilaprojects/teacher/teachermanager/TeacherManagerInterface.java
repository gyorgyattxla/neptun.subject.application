package attilaprojects.teacher.teachermanager;
import attilaprojects.teacher.Teacher;

public interface TeacherManagerInterface {
    /*** registerTeacher method will add a Teacher object into TeacherList ***/
    public boolean addTeacher(Teacher teacher);

    /*** removeTeacher method will remove a Teacher object from TeacherList ***/
    public boolean removeTeacher(Teacher teacher);

    public boolean doesTeacherExist(String username, String password);
}
