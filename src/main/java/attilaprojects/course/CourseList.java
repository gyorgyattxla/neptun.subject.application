package attilaprojects.course;

import java.io.*;
import java.util.ArrayList;

public class CourseList {
    private static CourseList instance;
    private ArrayList<Course> courseList;

    public CourseList() {
        this.courseList = new ArrayList<>();
    }

    public static CourseList getInstance() {
        if (instance == null) {
            instance = new CourseList();
        }
        return instance;
    }
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void removeCourse(Course course) {
        courseList.remove(course);
    }

    public boolean saveCourseList(){
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        if (!fileDirectory.exists()){
            boolean mkdirs = fileDirectory.mkdirs();//if the directory does not exist, create it
            if (!mkdirs){
                System.err.println("Failed to create a save of courseList");
                return false;
            }
        }
        File courseFile = new File(fileDirectory + "/courses.txt");
        try (FileWriter fileWriter = new FileWriter(courseFile)){
            for (Course course : courseList){
                fileWriter.write(course.makeString() + "\n");
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loadCourseList() {
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        File courseFile = new File(fileDirectory + "/courses.txt");
        if (!courseFile.exists()) {
            System.err.println("'courses.txt' not found, can't load data.");
            return false;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(courseFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                courseList.add(Course.fromString(line));
            }
            return true;
        } catch (IOException e) {
            System.err.println("Unable to load data.");
            return false;
        }

    }
}
