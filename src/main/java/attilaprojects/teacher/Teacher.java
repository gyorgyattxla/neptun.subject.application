package attilaprojects.teacher;


import java.util.Objects;

public class Teacher {
    private final String teacherName;
    private String teacherPassword;
    private String teacherID;

    public Teacher(String teacherName, String teacherPassword, String teacherID) {
        this.teacherName = teacherName;
        this.teacherPassword = teacherPassword;
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String makeString(){
        return teacherName + "," + teacherPassword + "," + teacherID;
    }

    public static Teacher fromString(String line){
        String[] parts = line.split(",");
        return new Teacher(parts[0],parts[1],parts[2]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getTeacherID(), teacher.getTeacherID()) && Objects.equals(getTeacherName(), teacher.getTeacherName()) && Objects.equals(getTeacherPassword(), teacher.getTeacherPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeacherName(), getTeacherPassword(), getTeacherID());
    }
}
