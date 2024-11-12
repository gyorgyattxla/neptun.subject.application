package attilaprojects.classes.classmanager;

import attilaprojects.classes.Course;

public interface CourseManagerInterface {
    /*** When a Teacher logs in, a 'Create Course' button is shown.
     *      When the button is clicked, the 'create Course' method is called,
     *      where the course's properties can be entered.
     *      The inputted properties will be added into the method,
     *      that adds the Course obj. to the courseList ***/
    public boolean createCourse(Course course);

    public boolean removeCourse(String courseName);
}
