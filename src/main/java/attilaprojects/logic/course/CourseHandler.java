package attilaprojects.logic.course;

import attilaprojects.classes.Course;
import attilaprojects.classes.CourseList;
import attilaprojects.classes.classmanager.CourseManager;

import java.util.ArrayList;

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

    @Override
    public ArrayList<Course> showTeacherCourses(String teacherName) {
        ArrayList<Course> courseList = CourseList.getInstance().getCourseList();
        ArrayList<Course> taughtCoursesList = new ArrayList<>();
        int taughtCourses=0;
        for (Course c : courseList){
            if (c.getClassTeacher().equals(teacherName)){
                taughtCourses++;
                taughtCoursesList.add(c);
            }
        }
        if (taughtCourses==0){
            System.err.println("Teacher doesn't teach any courses");
            return null;
        }
        return taughtCoursesList;
    }
}
