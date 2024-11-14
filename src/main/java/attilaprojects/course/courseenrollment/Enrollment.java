package attilaprojects.course.courseenrollment;

import java.util.Objects;

public class Enrollment {
    private String studentName;
    private String courseName;

    public Enrollment(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String makeString(){
        return studentName + "," + courseName;
    }

    public static Enrollment fromString(String line){
        String[] parts = line.split(",");
        return new Enrollment(parts[0],parts[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(getStudentName(), that.getStudentName()) && Objects.equals(getCourseName(), that.getCourseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentName(), getCourseName());
    }
}
