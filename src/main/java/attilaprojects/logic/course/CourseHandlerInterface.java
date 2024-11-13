package attilaprojects.logic.course;

import attilaprojects.classes.Course;
import attilaprojects.classes.CourseList;

import java.util.ArrayList;

public interface CourseHandlerInterface {
    public boolean addCourse(String courseName, String courseTeacher,
                             int creditNumber, String courseStartTime,
                             String courseEndTime);
    public boolean removeCourse(String courseName);

    /*** When a teacher logs in, the taught courses should appear under 'My Courses'.
     *      This method is called upon when the scene is shown and lists the courses. ***/
    public ArrayList<Course> showTeacherCourses(String teachername);
}
