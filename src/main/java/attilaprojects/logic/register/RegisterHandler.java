package attilaprojects.logic.register;

import attilaprojects.student.Student;
import attilaprojects.student.studentmanager.StudentManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RegisterHandler implements RegisterHandlerInterface{
    private String assignedID = "0001A";

    StudentManager studentManager = new StudentManager();

    public RegisterHandler(){};

    public boolean idIncrementer(){
        String numbers = assignedID.substring(0,4);
        String lastCharAsString = assignedID.substring(4);
        char[] letter = lastCharAsString.toCharArray();
        if (letter[0]=='Z') {
            letter[0]='A';
        }
        else{
            letter[0]+=1;
            assignedID = numbers + letter[0];
            return true;
        }
        int newNumbers = Integer.parseInt(numbers);
        if(newNumbers<9999){
            newNumbers+=1;
            assignedID = String.format("%04d", newNumbers + letter[0]);
            return true;
        }
        else{
            System.err.println("Exceeding the available user space");
            return false;
        }
    }

    public boolean loadLastAssignedStudentID(){
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        File studentFile = new File(fileDirectory + "/students.txt");
        if (!studentFile.exists()){
            System.err.println("'students.txt' not found, can't load data.");
            return true;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            int lengthToRead = Math.min(5, sb.length());
            if(lengthToRead==0) return true;
            assignedID = sb.substring(sb.length() - lengthToRead);
            idIncrementer();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean registerStudent(String username, String password) {
        if (!loadLastAssignedStudentID()) return false;

        Student student = new Student(username,password,assignedID);
        boolean studentAdded = studentManager.addStudent(student);
        if(studentAdded){
            boolean idIncremented = idIncrementer();
            if (idIncremented) return true;
            else {
                studentManager.removeStudent(student);
                return false;
            }
        }
        else return false;
    }
}
