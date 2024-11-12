package attilaprojects.classes.classmanager;

import attilaprojects.classes.Course;
import attilaprojects.classes.CourseList;

import java.util.ArrayList;

public class CourseManager implements CourseManagerInterface {
    @Override
    public boolean createCourse(Course course) {
        ArrayList<Course> courseList = CourseList.getInstance().getCourseList();
        //Check if there is a course with the same name
        for (Course c : courseList){
            if(course.getClassName().equals(c.getClassName())){
                System.err.println("A course with the same name already exists.");
                return false;
            }
        }
        CourseList.getInstance().addCourse(course);
        return true;
    }

    @Override
    public boolean removeCourse(String courseName) {
        ArrayList<Course> courseList = CourseList.getInstance().getCourseList();
        //Check if the course exists
        for (Course c : courseList){
            if(c.getClassName().equals(courseName)){
                CourseList.getInstance().removeCourse(c);
                return true;
            }
        }
        System.err.println("The course doesn't exist.");
        return false;
    }
}
