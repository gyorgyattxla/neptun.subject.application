package attilaprojects.teacher.teachermanager;

import attilaprojects.student.Student;
import attilaprojects.student.StudentList;
import attilaprojects.teacher.Teacher;
import attilaprojects.teacher.TeacherList;

import java.util.ArrayList;

public class TeacherManager implements TeacherManagerInterface{
    @Override
    public boolean addTeacher(Teacher teacher) {
        //Get teacherList
        ArrayList<Teacher> teacherList = TeacherList.getInstance().getTeacherList();
        //Check if the teacher already registered
        for (Teacher t : teacherList) {
            if (t.getTeacherName().equals(teacher.getTeacherName()) && t.getTeacherPassword().equals(teacher.getTeacherPassword())){
                //throw exception?
                return false; //The teacher has already registered;
            }
        }
        //if the teacher has not registered already, add it to the ArrayList.
        TeacherList.getInstance().addTeacher(teacher);
        return true;
    }

    @Override
    public boolean removeTeacher(Teacher teacher) {
        //Get teacherList
        ArrayList<Teacher> teacherList = TeacherList.getInstance().getTeacherList();
        //Check if the teacher exists
        for (Teacher t : teacherList) {
            //if it does, remove it.
            if(t.equals(teacher)){
                TeacherList.getInstance().removeTeacher(teacher);
                return true;
            }
        }
        //If the teacher does not exist, return false
        //throw exception?
        return false;
    }

    @Override
    public boolean doesTeacherExist(String username, String password) {
        //Get teacherList
        ArrayList<Teacher> teacherList = TeacherList.getInstance().getTeacherList();
        for (Teacher t : teacherList) {
            if(t.getTeacherName().equals(username) && t.getTeacherPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
