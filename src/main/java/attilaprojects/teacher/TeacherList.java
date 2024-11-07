package attilaprojects.teacher;
import java.util.ArrayList;

public class TeacherList {
    public static TeacherList instance;
    private ArrayList<Teacher> teacherList;

    public TeacherList() {
        this.teacherList = teacherList;
    }

    public static TeacherList getInstance() {
        if (instance == null) {
            instance = new TeacherList();
        }
        return instance;
    }

    public ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }

    public void addTeacher(Teacher teacher) {
        teacherList.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teacherList.remove(teacher);
    }
}
