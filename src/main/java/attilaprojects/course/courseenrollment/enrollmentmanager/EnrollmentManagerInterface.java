package attilaprojects.course.courseenrollment.enrollmentmanager;

import attilaprojects.course.courseenrollment.Enrollment;

public interface EnrollmentManagerInterface {
    public boolean addEnrollment(Enrollment enrollment);
    public boolean removeEnrollment(Enrollment enrollment);
}
