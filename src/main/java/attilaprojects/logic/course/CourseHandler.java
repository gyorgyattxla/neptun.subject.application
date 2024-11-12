package attilaprojects.logic.course;

import attilaprojects.classes.Course;
import attilaprojects.classes.classmanager.CourseManager;

public class CourseHandler implements CourseHandlerInterface{
    CourseManager courseManager = new CourseManager();
    @Override
    public boolean addCourse(String courseName, String courseTeacher,
                             int creditNumber, String courseStartTime,
                             String courseEndTime) {
        Course course = new Course(courseName, courseTeacher, creditNumber, courseStartTime, courseEndTime);
        return courseManager.createCourse(course);
    }

    @Override
    public boolean removeCourse(String courseName) {
        return courseManager.removeCourse(courseName);
    }
}
