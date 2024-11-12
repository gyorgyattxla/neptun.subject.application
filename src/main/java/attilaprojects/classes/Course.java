package attilaprojects.classes;

public class Course {
    private String className;
    private String classTeacher;
    private int creditNumber;
    private String classStartTime;
    private String classEndTime;
    //private String[] assignedStudents; !NEEDS IMPL. ELSEWHERE!

    public Course(String className, String classTeacher,
                  int creditNumber, String classStartTime,
                  String classEndTime) {
        this.className = className;
        this.classTeacher = classTeacher;
        this.creditNumber = creditNumber;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
    }

    public String getClassName() {
        return className;
    }

    public String makeString(){
        return className + ',' + classTeacher + ',' + creditNumber + ',' +
                classStartTime + ',' + classEndTime;
    }

    public static Course fromString(String line){
        String[] parts = line.split(",");
        return new Course(parts[0],parts[1], Integer.parseInt(parts[2]), parts[3],parts[4]);
    }
}
