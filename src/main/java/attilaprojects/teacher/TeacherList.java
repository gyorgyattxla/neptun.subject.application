package attilaprojects.teacher;
import attilaprojects.student.Student;

import java.io.*;
import java.util.ArrayList;

public class TeacherList {
    public static TeacherList instance;
    private ArrayList<Teacher> teacherList;

    public TeacherList() {
        this.teacherList = new ArrayList<>();
    }

    public static TeacherList getInstance() {
        if (instance == null) {
            instance = new TeacherList();
        }
        return instance;
    }

    public ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }

    public void addTeacher(Teacher teacher) {
        teacherList.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teacherList.remove(teacher);
    }

    public boolean saveTeacherList(){
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        if (!fileDirectory.exists()){
            boolean mkdirs = fileDirectory.mkdirs();//if the directory does not exist, create it
            if (!mkdirs){
                System.err.println("Failed to create a save of teacherList");
                return false;
            }
        }
        File teacherFile = new File(fileDirectory + "/teachers.txt");
        try (FileWriter fileWriter = new FileWriter(teacherFile)){
            for (Teacher teacher : teacherList){
                fileWriter.write(teacher.makeString() + "\n");
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loadTeacherList(){
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        File teacherFile = new File(fileDirectory + "/teachers.txt");
        if (!teacherFile.exists()){
            System.err.println("'teachers.txt' not found, can't load data.");
            return false;
        }
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(teacherFile))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                teacherList.add(Teacher.fromString(line));
            }
            return true;
        }
        catch (IOException e){
            System.err.println("Unable to load data.");
            return false;
        }
    }
}
