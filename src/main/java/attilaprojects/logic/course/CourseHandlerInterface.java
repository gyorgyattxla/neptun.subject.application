package attilaprojects.logic.course;

public interface CourseHandlerInterface {
    public boolean addCourse(String courseName, String courseTeacher,
                             int creditNumber, String courseStartTime,
                             String courseEndTime);
    public boolean removeCourse(String courseName);
}
