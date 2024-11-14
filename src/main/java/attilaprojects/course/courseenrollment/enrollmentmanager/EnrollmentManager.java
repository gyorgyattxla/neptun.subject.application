package attilaprojects.course.courseenrollment.enrollmentmanager;

import attilaprojects.course.courseenrollment.Enrollment;
import attilaprojects.course.courseenrollment.EnrollmentList;

import java.util.ArrayList;

public class EnrollmentManager implements EnrollmentManagerInterface{
    @Override
    public boolean addEnrollment(Enrollment enrollment){
        //Get enrollmentList
        ArrayList<Enrollment> enrollmentList = EnrollmentList.getInstance().getEnrollmentList();
        //Check if the student already enrolled
        for (Enrollment e : enrollmentList) {
            if (e.getStudentName().equals(enrollment.getStudentName()) && e.getCourseName().equals(enrollment.getCourseName())){
                //throw exception?
                return false; //The student has already enrolled;
            }
        }
        //if the student has not registered already, add it to the ArrayList.
        EnrollmentList.getInstance().addEnrollment(enrollment);
        return true;
    }

    @Override
    public boolean removeEnrollment(Enrollment enrollment) {
        //Get enrollmentList
        ArrayList<Enrollment> enrollmentList = EnrollmentList.getInstance().getEnrollmentList();
        //Check if the enrollment exists
        for (Enrollment e : enrollmentList) {
            //if it does, remove it.
            if(e.equals(enrollment)){
                EnrollmentList.getInstance().removeEnrollment(enrollment);
                return true;
            }
        }
        //If the enrollment does not exist, return false
        //throw exception?
        return false;
    }
}
