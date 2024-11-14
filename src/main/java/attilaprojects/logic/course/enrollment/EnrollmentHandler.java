package attilaprojects.logic.course.enrollment;

import attilaprojects.course.Course;
import attilaprojects.course.CourseList;
import attilaprojects.course.courseenrollment.Enrollment;
import attilaprojects.course.courseenrollment.EnrollmentList;
import attilaprojects.course.courseenrollment.enrollmentmanager.EnrollmentManager;

import java.util.ArrayList;

public class EnrollmentHandler implements EnrollmentHandlerInterface {
    EnrollmentManager enrollmentManager = new EnrollmentManager();
    @Override
    public boolean addEnrollment(String studentName, String courseName) {
        Enrollment enrollment = new Enrollment(studentName, courseName);
        return enrollmentManager.addEnrollment(enrollment);
    }

    @Override
    public boolean removeEnrollment(String studentName, String courseName) {
        ArrayList<Enrollment> enrollmentList = EnrollmentList.getInstance().getEnrollmentList();
        for (Enrollment e : enrollmentList){
            if(e.getStudentName().equals(studentName) && e.getCourseName().equals(courseName)){
                enrollmentList.remove(e);
            }
        }
        return false;
    }

    @Override
    public ArrayList<Course> showStudentCourses(String studentName) {
        ArrayList<Course> courseList = CourseList.getInstance().getCourseList();
        ArrayList<Enrollment> enrollmentList = EnrollmentList.getInstance().getEnrollmentList();
        ArrayList<Course> enrolledCoursesList = new ArrayList<>();
        int enrolledCourses=0;
        for (Enrollment e : enrollmentList){
            if (e.getStudentName().equals(studentName)){
                enrolledCourses++;
                for (Course c : courseList){
                    if(c.getClassName().equals(e.getCourseName())){
                        enrolledCoursesList.add(c);
                    }
                }
            }
        }
        if (enrolledCourses==0){
            System.err.println("Student hasn't enrolled any courses.");
            return null;
        }
        return enrolledCoursesList;
    }
}
