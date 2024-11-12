package attilaprojects.student;

import java.util.Objects;

public class Student {
    private final String studentName;
    private String studentPassword;
    private String studentID;

    //FORGOT THIS TRASH private Class[] joinedClasses;
    //This made my life ~32x harder probably, but it's too late to change rn

    public Student(String studentName, String studentPassword, String studentID) {
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String makeString(){
        return studentName + "," + studentPassword + "," + studentID;
    }

    public static Student fromString(String line){
        String[] parts = line.split(",");
        return new Student(parts[0],parts[1],parts[2]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getStudentName(), student.getStudentName()) && Objects.equals(getStudentPassword(), student.getStudentPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentName(), getStudentPassword());
    }
}
