package attilaprojects.course.courseenrollment;

import java.io.*;
import java.util.ArrayList;

public class EnrollmentList {
    private static EnrollmentList instance;
    private ArrayList<Enrollment> enrollmentList;

    public EnrollmentList() {
        this.enrollmentList = new ArrayList<>();
    }

    public static EnrollmentList getInstance(){
        if (instance == null) {
            instance = new EnrollmentList();
        }
        return instance;
    }

    public ArrayList<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    public void addEnrollment(Enrollment enrollment){
        enrollmentList.add(enrollment);
    }

    public void removeEnrollment(Enrollment enrollment){
        enrollmentList.remove(enrollment);
    }

    public boolean saveEnrollmentList(){
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        if (!fileDirectory.exists()){
            boolean mkdirs = fileDirectory.mkdirs();//if the directory does not exist, create it
            if (!mkdirs){
                System.err.println("Failed to create a save of enrollmentList");
                return false;
            }
        }
        File enrollmentFile = new File(fileDirectory + "/enrollments.txt");
        try (FileWriter fileWriter = new FileWriter(enrollmentFile)){
            for (Enrollment enrollment : enrollmentList){
                fileWriter.write(enrollment.makeString() + "\n");
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loadStudentList(){
        String userHome = System.getProperty("user.home");
        File fileDirectory = new File(userHome + "/nsas-data"); //set directory

        File enrollmentFile = new File(fileDirectory + "/enrollments.txt");
        if (!enrollmentFile.exists()){
            System.err.println("'enrollments.txt' not found, can't load data.");
            return false;
        }
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(enrollmentFile))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                enrollmentList.add(Enrollment.fromString(line));
            }
            return true;
        }
        catch (IOException e){
            System.err.println("Unable to load data.");
            return false;
        }
    }
}
