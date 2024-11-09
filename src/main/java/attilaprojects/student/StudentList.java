package attilaprojects.student;

import java.io.*;
import java.util.ArrayList;

public class StudentList {
    private static StudentList instance;
    private ArrayList<Student> studentList;

    public StudentList() {
        this.studentList = new ArrayList<>();
    }

    public static StudentList getInstance() {
        if (instance == null) {
            instance = new StudentList();
        }
        return instance;
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void removeStudent(Student student) {
        studentList.remove(student);
    }

    public boolean saveStudentList(){
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        if (!fileDirectory.exists()){
            boolean mkdirs = fileDirectory.mkdirs();//if the directory does not exist, create it
            if (!mkdirs){
                System.err.println("Failed to create a save of studentList");
                return false;
            }
        }
        File studentFile = new File(fileDirectory + "/students.txt");
        try (FileWriter fileWriter = new FileWriter(studentFile)){
            for (Student student : studentList){
                fileWriter.write(student.makeString() + "\n");
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loadStudentList(){
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        File studentFile = new File(fileDirectory + "/students.txt");
        if (!studentFile.exists()){
            System.err.println("'students.txt' not found, can't load data.");
            return false;
        }
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(studentFile))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                studentList.add(Student.fromString(line));
            }
            return true;
        }
        catch (IOException e){
            System.err.println("Unable to load data.");
            return false;
        }
    }
}
