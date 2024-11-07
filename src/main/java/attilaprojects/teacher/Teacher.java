package attilaprojects.teacher;

import java.util.Objects;

public class Teacher {
    private final String teacherName;
    private String teacherPassword;
    private int teacherID;

    public Teacher(String teacherName, String teacherPassword, int teacherID) {
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

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return getTeacherID() == teacher.getTeacherID() && Objects.equals(getTeacherName(), teacher.getTeacherName()) && Objects.equals(getTeacherPassword(), teacher.getTeacherPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeacherName(), getTeacherPassword(), getTeacherID());
    }
}
