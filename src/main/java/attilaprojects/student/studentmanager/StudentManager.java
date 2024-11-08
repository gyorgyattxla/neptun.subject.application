package attilaprojects.student.studentmanager;

import attilaprojects.student.Student;
import attilaprojects.student.StudentList;

import java.util.ArrayList;

public class StudentManager implements StudentManagerInterface{
    @Override
    public boolean addStudent(Student student) {
        //Get studentList
        ArrayList<Student> studentList = StudentList.getInstance().getStudentList();
        //Check if the student already registered
        for (Student s : studentList) {
            if (s.getStudentName().equals(student.getStudentName()) && s.getStudentPassword().equals(student.getStudentPassword())){
                //throw exception?
                return false; //The student has already registered;
            }
        }
        //if the student has not registered already, add it to the ArrayList.
        StudentList.getInstance().addStudent(student);
        return true;
    }

    @Override
    public boolean removeStudent(Student student) {
        //Get studentList
        ArrayList<Student> studentList = StudentList.getInstance().getStudentList();
        //Check if the student exists
        for (Student s : studentList) {
            //if it does, remove it.
            if(s.equals(student)){
                StudentList.getInstance().removeStudent(student);
                return true;
            }
        }
        //If the student does not exist, return false
        //throw exception?
        return false;
    }
}
