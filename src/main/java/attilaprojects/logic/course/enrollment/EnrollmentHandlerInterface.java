package attilaprojects.logic.course.enrollment;

import attilaprojects.course.Course;
import attilaprojects.course.courseenrollment.Enrollment;
import attilaprojects.course.courseenrollment.EnrollmentList;

import java.util.ArrayList;

public interface EnrollmentHandlerInterface {
    public boolean addEnrollment(String studentName, String courseName);
    public boolean removeEnrollment(String studentName, String courseName);
    public ArrayList<Course> showStudentCourses(String studentName);
}
